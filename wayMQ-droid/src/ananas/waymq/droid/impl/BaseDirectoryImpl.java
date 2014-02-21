package ananas.waymq.droid.impl;

import java.io.File;

import ananas.waymq.droid.api.IBaseDirectory;

public class BaseDirectoryImpl implements IBaseDirectory {

	private final File _base;

	public BaseDirectoryImpl(File base) {
		this._base = base;
	}

	@Override
	public File getPath() {
		return _base;
	}

	@Override
	public File getMembersDirectory() {
		return new File(_base, "members");
	}

}
