package ananas.waymq.core;

import waymq.model.Member;
import ananas.lib.io.vfs.VFile;
import ananas.lib.io.vfs.VFileSystem;
import ananas.objectbox.DefaultBox;
import ananas.objectbox.IBox;
import ananas.waymq.api.IMember;

public class DefaultWayMQRepo implements WayMQRepo {

	private final VFile _repo_dir;
	private final IBox _obj_box;
	private IMember _root;

	public DefaultWayMQRepo(VFile file) {
		this._repo_dir = file;
		VFileSystem vfs = file.getVFS();
		VFile boxdir = vfs.newFile(file, "objects");
		this._obj_box = new DefaultBox(boxdir);

		if (!file.getName().equals(".wayMQ")) {
			throw new RuntimeException("the repo name must be '.wayMQ'");
		}
	}

	@Override
	public VFile getRepoDirectory() {
		return this._repo_dir;
	}

	@Override
	public IBox getObjectBox() {
		return this._obj_box;
	}

	@Override
	public boolean exists() {
		return this._repo_dir.exists();
	}

	@Override
	public boolean create() {
		if (this._repo_dir.exists()) {
			return false;
		}
		boolean rlt = this._obj_box.getBaseDirectory().mkdirs();
		return rlt;
	}

	@Override
	public boolean open() {
		return this._obj_box.getBaseDirectory().exists();
	}

	@Override
	public IMember getRoot() {
		IMember root = this._root;
		if (root != null)
			return root;

		Member obj = (Member) this._obj_box.newObject(Member.class, null);
		root = obj;
		this._root = root;
		return root;
	}
}
