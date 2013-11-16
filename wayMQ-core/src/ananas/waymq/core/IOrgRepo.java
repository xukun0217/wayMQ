package ananas.waymq.core;

import ananas.lib.io.vfs.VFile;

public interface IOrgRepo extends LogMaker, ISnapshot {

	boolean exists();

	void init();

	void check();

	VFile getRepoDirectory();

	void setModified(boolean isMod);

	boolean isModified();

	void save();

	void load();

}
