package test.waymq.core;

import java.net.URI;
import java.net.URL;

import org.junit.Test;

import ananas.lib.io.vfs.VFS;
import ananas.lib.io.vfs.VFile;
import ananas.lib.io.vfs.VFileSystem;
import ananas.waymq.api.IGroup;
import ananas.waymq.api.IJoinGroup;
import ananas.waymq.api.IMember;
import ananas.waymq.core.DefaultWayMQRepo;
import ananas.waymq.core.WayMQRepo;

public class TestWayMQCore {

	@Test
	public void test() {

		VFile file = this.__get_project_dir();
		VFileSystem vfs = file.getVFS();
		VFile rdir = vfs.newFile(file, "test/repo/.wayMQ");
		System.out.println("project-dir : " + file);

		WayMQRepo repo = new DefaultWayMQRepo(rdir);
		if (!repo.exists()) {
			repo.create();
		}

		repo.open();
		IMember root = repo.getRoot();
		IJoinGroup[] groups = root.listGroups();
		if (groups.length == 0) {
			IGroup group = root.createGroup("abc");
			groups = root.listGroups();
		}
		for (IJoinGroup g : groups) {
		}

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
