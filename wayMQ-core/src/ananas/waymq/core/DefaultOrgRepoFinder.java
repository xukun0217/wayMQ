package ananas.waymq.core;

import ananas.lib.io.vfs.VFile;
import ananas.lib.io.vfs.VFileSystem;

public class DefaultOrgRepoFinder implements IOrgRepoFinder {

	interface Const {
		String repo_dir_name = ".wayMQ";
	}

	@Override
	public VFile lookUp(VFile path) {
		VFileSystem vfs = path.getVFS();
		for (; path != null; path = path.getParentFile()) {
			VFile dir = vfs.newFile(path, Const.repo_dir_name);
			if (dir.exists()) {
				if (dir.isDirectory()) {
					return dir;
				}
			}
		}
		return null;
	}

	@Override
	public VFile lookDown(VFile file, int depthLimit) {

		if (depthLimit < 0)
			return null;
		if (file == null)
			return null;
		if (!file.exists())
			return null;

		if (file.isDirectory()) {
			if (Const.repo_dir_name.equals(file.getName())) {
				return file;
			}
			VFile[] list = file.listFiles();
			for (VFile ch : list) {
				VFile rlt = this.lookDown(ch, depthLimit - 1);
				if (rlt != null) {
					return rlt;
				}
			}
		}
		return null;
	}

}
