package test.waymq.core;

import java.net.URI;
import java.net.URL;

import org.junit.Test;

import ananas.lib.io.vfs.VFS;
import ananas.lib.io.vfs.VFile;
import ananas.lib.io.vfs.VFileSystem;

public class TestWayMQCore {

	@Test
	public void test() {

		VFile file = this.__get_project_dir();
		System.out.println("project-dir : " + file);

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
