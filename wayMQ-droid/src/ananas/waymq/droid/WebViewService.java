package ananas.waymq.droid;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
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
			} else if (scheme.equals("config")) {
				String name = "key";
				String value = "val";
				intent.putExtra(name, value);
			}
		}
		this.showNotify("hello, world", "...");

		return super.onStartCommand(intent, flags, startId);
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

}
