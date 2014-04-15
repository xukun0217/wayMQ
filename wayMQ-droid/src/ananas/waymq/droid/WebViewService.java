package ananas.waymq.droid;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import ananas.waymq.droid.gap.GapConst;
import ananas.waymq.droid.gap.GapCore;
import ananas.waymq.droid.gap.News;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.IBinder;

public class WebViewService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;

	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		Uri uri = intent.getData();
		if (uri != null) {
			String scheme = uri.getScheme();
			if (scheme == null) {
			} else if (scheme.equals("sms")) {
				String from = uri.getQueryParameter("from");
				String to = uri.getQueryParameter("to");
				String text = uri.getQueryParameter("text");
				System.out.println(this);
				System.out.println("    from:" + from);
				System.out.println("      to:" + to);
				System.out.println("    text:" + text);
				this.onRxSMS(from, to, text);
			} else if (scheme.equals("config")) {
				String name = "key";
				String value = "val";
				intent.putExtra(name, value);
			}
		}
		// this.showNotify("hello, world", "...");
		this.startNewsCheckoutLoop();

		return super.onStartCommand(intent, flags, startId);
	}

	private void onRxSMS(String from, String to, String text) {
		GapCore gc = new GapCore(this);
		SharedPreferences sp = gc.getSharedPreferences();
		Editor edit = sp.edit();
		edit.putString(GapConst.Key.sms_from, from);
		edit.putString(GapConst.Key.sms_to, to);
		edit.putString(GapConst.Key.sms_text, text);
		edit.commit();
	}

	private Runnable _newsCheckoutLoop;

	private synchronized void startNewsCheckoutLoop() {
		class Runn implements Runnable {

			@Override
			public void run() {
				try {
					// int sleep = 15*1000 ;
					int sleep = 1 * 1000;
					for (;;) {
						Thread.sleep(sleep);
						WebViewService.this.checkoutNews();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				WebViewService.this._newsCheckoutLoop = null;
			}
		}
		if (_newsCheckoutLoop == null) {
			Runn runn = new Runn();
			_newsCheckoutLoop = runn;
			(new Thread(runn)).start();
		}
	}

	private void checkoutNews() {
		try {
			GapCore gc = new GapCore(this);
			News news = gc.checkoutNews(3600 * 1000);
			if (news != null)
				if (news.hasUpdated) {
					String title = news.title;
					String text = news.text;
					this.showNotify(title, text);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	private void showNotify(String title, String text) {

		NotificationManager manager = (NotificationManager) this
				.getSystemService(Context.NOTIFICATION_SERVICE);
		// 创建一个Notification
		Notification notification = new Notification();
		// 设置显示在手机最上边的状态栏的图标
		notification.icon = R.drawable.ic_launcher;
		// 当当前的notification被放到状态栏上的时候，提示内容
		notification.tickerText = title;

		/***
		 * notification.contentIntent:一个PendingIntent对象，当用户点击了状态栏上的图标时，
		 * 该Intent会被触发 notification.contentView:我们可以不在状态栏放图标而是放一个view
		 * notification.deleteIntent 当当前notification被移除时执行的intent
		 * notification.vibrate 当手机震动时，震动周期设置
		 */
		// 添加声音提示
		notification.defaults = Notification.DEFAULT_SOUND;
		// audioStreamType的值必须AudioManager中的值，代表着响铃的模式
		notification.audioStreamType = android.media.AudioManager.ADJUST_LOWER;

		// 下边的两个方式可以添加音乐
		// notification.sound =
		// Uri.parse("file:///sdcard/notification/ringer.mp3");
		// notification.sound =
		// Uri.withAppendedPath(Audio.Media.INTERNAL_CONTENT_URI, "6");
		Intent intent = new Intent(this, WebViewActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
				intent, PendingIntent.FLAG_ONE_SHOT);
		// 点击状态栏的图标出现的提示信息设置
		notification.setLatestEventInfo(this, title, text, pendingIntent);
		manager.notify(1, notification);
	}

	public static String createSMSRxURI(String from, String text) {
		try {
			String charset = "UTF-8";
			StringBuilder sb = new StringBuilder();
			sb.append("sms://localhost/?ct=sms");
			sb.append("&from=");
			sb.append(URLEncoder.encode(from, charset));
			sb.append("&to=");
			sb.append(URLEncoder.encode("", charset));
			sb.append("&text=");
			sb.append(URLEncoder.encode(text, charset));
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
