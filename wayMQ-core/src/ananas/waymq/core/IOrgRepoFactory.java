package ananas.waymq.core;

import ananas.lib.io.vfs.VFile;

public interface IOrgRepoFactory {

	IOrgRepo newRepo(VFile repoDir);

}
