package ananas.waymq.droid.api;

import java.util.List;

import ananas.waymq.droid.impl.DefaultCore;
import android.app.Activity;
import android.os.Bundle;

public class CoreAgent {

	private final Activity _act;

	public CoreAgent(Activity act) {
		this._act = act;
	}

	private static ICoreApi _core;

	public ICoreApi getCore() {
		ICoreApi core = _core;
		if (core == null) {
			core = DefaultCore.getInstance();
			_core = core;
		}
		return core;
	}

	public void onCreate(Bundle savedInstanceState) {
	}

	public void findMembers(String keyword, FindMembersHandler h) {
		ICoreApi core = this.getCore();
		IMemberManager mm = core.getMemberManager();
		List<IMember> rlt = mm.findMembers(keyword);
		h.onFindMembers(keyword, rlt);
	}

	public void onPause() {
		// TODO Auto-generated method stub
		
	}

	public void onResume() {
		// TODO Auto-generated method stub
		
	}

}
