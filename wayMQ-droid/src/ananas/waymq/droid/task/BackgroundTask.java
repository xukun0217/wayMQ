package ananas.waymq.droid.task;

public interface BackgroundTask {

	void onStart(ForegroundContext fc);

	void onProcess(BackgroundContext bc);

	void onFinish(ForegroundContext fc);

}
