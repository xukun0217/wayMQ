package ananas.waymq.droid.core;

import ananas.waymq.droid.api.IBaseDirectory;
import ananas.waymq.droid.api.ICoreApi;

public class DefaultCore implements ICoreApi {

	public static ICoreApi getInstance() {
		return new DefaultCore();
	}

	private IBaseDirectory _base_dir;

	@Override
	public IBaseDirectory getBaseDirectory() {
		IBaseDirectory baseDir = this._base_dir;
		if (baseDir == null) {

			this._base_dir = baseDir;
		}
		return baseDir;
	}

	@Override
	public IMember[] getMembers() {
		// TODO Auto-generated method stub
		return null;
	}

}
