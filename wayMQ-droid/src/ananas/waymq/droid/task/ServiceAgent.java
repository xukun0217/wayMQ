package ananas.waymq.droid.task;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public interface ServiceAgent {

	void runInBackground(BackgroundTask task);

	JSONObject requestJSON(String httpMethod, Map<String, String> param);

	void onPause();

	void onResume();

	void onStop();

	void onStart();

}
