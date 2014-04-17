package ananas.waymq.droid.offline;

import android.app.Activity;

public abstract class OfflineCore implements IOfflineCore {

	public static IOfflineCore newInstance(Activity activity) {
		return new OfflineCoreImpl(activity);
	}

}
