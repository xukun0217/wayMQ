package ananas.waymq.droid.gap;

import android.app.Activity;

public class Event {

	public static void dispatch(Activity activity, Event event,
			EventListener listener) {

		activity.runOnUiThread(new UiRunnable(event, listener));

	}

	private static class UiRunnable implements Runnable {

		private final Event _event;
		private final EventListener _list;

		public UiRunnable(Event event, EventListener listener) {
			this._event = event;
			this._list = listener;
		}

		@Override
		public void run() {
			this._list.onEvent(_event);
		}
	}

}
