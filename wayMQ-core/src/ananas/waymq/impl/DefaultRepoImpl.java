package ananas.waymq.impl;

import ananas.lib.io.vfs.VFile;
import ananas.lib.io.vfs.VFileSystem;
import ananas.objectbox.DefaultBox;
import ananas.objectbox.IBox;
import ananas.waymq.core.IRepo;

public class DefaultRepoImpl implements IRepo {

	private final VFile _dir;
	private final IBox _box;

	public DefaultRepoImpl(VFile repodir) {
		this._dir = repodir;
		VFileSystem vfs = repodir.getVFS();
		VFile boxdir = vfs.newFile(repodir, "objects");
		this._box = new DefaultBox(boxdir);
		String reg_name = ".wayMQ";
		if (!this._dir.getName().equals(reg_name)) {
			System.err.println("the repo dir name is not " + reg_name);
		}
	}

	@Override
	public VFile getRepoDirectory() {
		return this._dir;
	}

	@Override
	public IBox getBox() {
		return this._box;
	}

	@Override
	public boolean exists() {
		return this._box.getBaseDirectory().exists();
	}

	@Override
	public boolean init() {
		return this._box.getBaseDirectory().mkdirs();
	}

	@Override
	public boolean check() {
		return this.exists();
	}

}
