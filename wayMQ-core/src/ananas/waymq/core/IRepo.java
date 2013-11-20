package ananas.waymq.core;

import ananas.lib.io.vfs.VFile;
import ananas.objectbox.IBox;

public interface IRepo {

	VFile getRepoDirectory();

	IBox getBox();

	boolean exists();

	boolean init();

	boolean check();

}
