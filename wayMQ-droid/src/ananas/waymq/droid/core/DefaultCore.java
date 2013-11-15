package ananas.waymq.droid.core;

public class DefaultCore implements ICoreApi {

	public static ICoreApi getInstance() {
		return new DefaultCore();
	}

	private IBaseDirectory _base_dir;

	@Override
	public IMember[] findMembers(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IBaseDirectory getBaseDirectory() {
		IBaseDirectory baseDir = this._base_dir;
		if (baseDir == null) {

			this._base_dir = baseDir;
		}
		return baseDir;
	}

}
