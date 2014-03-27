package ananas.waymq.droid.task;

import android.app.Activity;

class BackgroundTaskContext implements Runnable, BackgroundContext,
		ForegroundContext {

	public BackgroundTask task;
	public Activity activity;
	public ServiceAgent agent;

	public void start() {
		this.doStartFore();
		Thread thread = new Thread(this);
		thread.start();
	}

	private void doStartFore() {
		try {
			this.task.onStart(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void doProcBack() {
		try {
			this.task.onProcess(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void doFinishBack() {
		try {
			this.activity.runOnUiThread(new Runnable() {

				@Override
				public void run() {
					doFinishFore();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void doFinishFore() {
		try {
			this.task.onFinish(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		this.doProcBack();
		this.doFinishBack();
	}

	@Override
	public ServiceAgent getServiceAgent() {
		return this.agent;
	}

}
