package ananas.waymq.ht5.litegroup;

import java.io.File;

public interface LG2Repo {

	class Factory {

		private static LG2Repo inst;

		public static LG2Repo getInstance() {
			if (inst == null) {
				inst = LG2RepoImpl.getInst();
			}
			return inst;
		}

	}

	File getPath();

}
