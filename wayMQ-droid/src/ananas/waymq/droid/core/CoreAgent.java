package ananas.waymq.droid.core;

import ananas.waymq.droid.api.ICoreApi;
import android.app.Activity;

public class CoreAgent {

	public CoreAgent(Activity act) {

	}

	private static ICoreApi _core;

	public ICoreApi getCore() {
		ICoreApi core = _core;
		if (core == null) {
			
			core=   DefaultCore. getInstance   ()    ;
			_core = core;
		}
		return core;
	}

}
