package ananas.waymq.droid.impl;

import java.io.File;

import ananas.waymq.droid.api.IBaseDirectory;
import ananas.waymq.droid.api.ICoreApi;
import ananas.waymq.droid.api.IMemberManager;
import android.os.Environment;

public class DefaultCore implements ICoreApi {

	public static ICoreApi getInstance() {
		return new DefaultCore();
	}

	private IBaseDirectory _base_dir;
	private IMemberManager _member_man;

	@Override
	public IBaseDirectory getBaseDirectory() {
		IBaseDirectory baseDir = this._base_dir;
		if (baseDir == null) {
			File path = Environment.getExternalStorageDirectory();
			path = new File(path, "ananas/wayMQ");
			baseDir = new BaseDirectoryImpl(path);
			this._base_dir = baseDir;
		}
		return baseDir;
	}

	@Override
	public IMemberManager getMemberManager() {
		IMemberManager mm = this._member_man;
		if (mm == null) {
			mm = new MemberManagerImpl();
			this._member_man = mm;
		}
		return mm;
	}

	@Override
	public void save() {
		IBaseDirectory dir = this.getBaseDirectory();
		IMemberManager mm = this.getMemberManager();
		mm.save(dir.getMembersDirectory());
	}

	@Override
	public void load() {
		IBaseDirectory dir = this.getBaseDirectory();
		IMemberManager mm = this.getMemberManager();
		mm.load(dir.getMembersDirectory());
	}

}
