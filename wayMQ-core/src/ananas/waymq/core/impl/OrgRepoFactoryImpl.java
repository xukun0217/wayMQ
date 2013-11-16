package ananas.waymq.core.impl;

import ananas.lib.io.vfs.VFile;
import ananas.waymq.core.IOrgRepo;
import ananas.waymq.core.IOrgRepoFactory;

public class OrgRepoFactoryImpl implements IOrgRepoFactory {

	@Override
	public IOrgRepo newRepo(VFile repoDir) {
		final String qn = ".wayMQ";
		String name = repoDir.getName();
		if (!qn.equals(name)) {
			throw new RuntimeException("the repo dir name must be '" + qn + "'");
		}
		return new RepoImpl(repoDir);
	}

}
