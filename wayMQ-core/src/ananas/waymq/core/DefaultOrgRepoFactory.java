package ananas.waymq.core;

import ananas.lib.io.vfs.VFile;
import ananas.waymq.core.impl.OrgRepoFactoryImpl;

public class DefaultOrgRepoFactory implements IOrgRepoFactory {

	private final IOrgRepoFactory impl = new OrgRepoFactoryImpl();

	@Override
	public IOrgRepo newRepo(VFile repoDir) {
		return impl.newRepo(repoDir);
	}

}
