package ananas.waymq.droid;

import android.app.Service;
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
		if (uri != null)
			if (uri.getScheme().equals("sms")) {
				String from = uri.getQueryParameter("from");
				String to = uri.getQueryParameter("to");
				String text = uri.getQueryParameter("text");
				System.out.println(this);
				System.out.println("    from:" + from);
				System.out.println("      to:" + to);
				System.out.println("    text:" + text);
			}

		return super.onStartCommand(intent, flags, startId);
	}

}
