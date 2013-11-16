package test.waymq.core;

import java.net.URI;
import java.net.URL;

import org.junit.Test;

import ananas.lib.io.vfs.VFS;
import ananas.lib.io.vfs.VFile;
import ananas.lib.io.vfs.VFileSystem;
import ananas.waymq.core.DefaultEvent;
import ananas.waymq.core.DefaultMember;
import ananas.waymq.core.DefaultOrgRepoFactory;
import ananas.waymq.core.DefaultOrgRepoFinder;
import ananas.waymq.core.IOrgRepo;
import ananas.waymq.core.IOrgRepoFactory;
import ananas.waymq.core.IOrgRepoFinder;
import ananas.waymq.core.PhoneNum;

public class TestWayMQCore {

	@Test
	public void test() {

		VFile file = this.__get_project_dir();
		System.out.println("project-dir : " + file);

		IOrgRepoFinder finder = new DefaultOrgRepoFinder();
		file = finder.lookDown(file, 5);
		System.out.println("look down : " + file);

		VFileSystem vfs = file.getVFS();
		file = vfs.newFile(file.getParentFile(), "test/a/b");
		file.mkdirs();
		System.out.println("cd to : " + file);

		file = finder.lookUp(file);
		System.out.println("look up : " + file);

		IOrgRepoFactory repoFact = new DefaultOrgRepoFactory();
		IOrgRepo repo = repoFact.newRepo(file);
		if (!repo.exists()) {
			repo.init();
		}
		repo.check();

		DefaultEvent event = new DefaultEvent(null);
		repo.addEvent(event);

		DefaultMember member = new DefaultMember(new PhoneNum("13012345678"));

		repo.addMember(member);

		repo.join(member, event);

	}

	private VFile __get_project_dir() {
		try {
			URL url = this.getClass().getProtectionDomain().getCodeSource()
					.getLocation();
			URI uri = url.toURI();
			VFileSystem vfs = VFS.getDefaultFactory().defaultFileSystem();
			VFile file = vfs.newFile(uri);
			for (; file != null; file = file.getParentFile()) {
				VFile prj = vfs.newFile(file, ".project");
				if (prj.exists()) {
					return file;
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	public static void main(String[] arg) {

		(new TestWayMQCore()).test();

	}

}
