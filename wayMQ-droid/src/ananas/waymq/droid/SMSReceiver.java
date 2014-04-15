package ananas.waymq.droid;

import java.io.UnsupportedEncodingException;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SMSReceiver extends BroadcastReceiver {

	public static final String SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED";

	@Override
	public void onReceive(Context context, Intent intent) {

		final String action1 = intent.getAction();
		final String action2 = SMS_RECEIVED_ACTION;

		if (action2.equals(action1)) {
			Bundle bundle = intent.getExtras();
			if (bundle != null) {
				Object[] pdus = (Object[]) bundle.get("pdus");
				for (Object pdu : pdus) {
					SmsMessage message = SmsMessage.createFromPdu((byte[]) pdu);
					String from = message.getOriginatingAddress();
					String text = message.getDisplayMessageBody();
					// abortBroadcast();
					this.sendToService(context, from, text);
				}
			}
		}
	}

	private void sendToService(Context context, String from, String text) {
		System.out.println("from " + from + ":" + text);
		String chset = "UTF-8";
		Intent service = new Intent(context, WebViewService.class);
		try {
			from = java.net.URLEncoder.encode(from, chset);
			text = java.net.URLEncoder.encode(text, chset);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String uri = WebViewService.createSMSRxURI(from, text);
		// "sms://localhost/?from="
		// + from +
		// "&text=" +
		// text;
		service.setData(Uri.parse(uri));
		context.startService(service);
	}

}
