package ananas.waymq.core;

import ananas.lib.io.vfs.VFile;

public interface IOrgRepoFinder {

	/**
	 * from child to parent
	 * */
	VFile lookUp(VFile file);

	/**
	 * from parent to child
	 * */

	VFile lookDown(VFile file, int depthLimit);

}
