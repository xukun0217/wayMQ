package ananas.waymq.core;

import ananas.lib.io.vfs.VFile;
import ananas.objectbox.IBox;
import ananas.waymq.impl.DefaultRepoImpl;

public class DefaultRepo implements IRepo {

	private final IRepo impl;

	public DefaultRepo(VFile repo_dir) {
		this.impl = new DefaultRepoImpl(repo_dir);
	}

	@Override
	public VFile getRepoDirectory() {
		return impl.getRepoDirectory();
	}

	@Override
	public IBox getBox() {
		return impl.getBox();
	}

	@Override
	public boolean exists() {
		return impl.exists();
	}

	@Override
	public boolean init() {
		return impl.init();
	}

	@Override
	public boolean check() {
		return impl.check();
	}

}
