package test.waymq.core;

import java.net.URI;
import java.net.URL;

import org.junit.Test;

import ananas.lib.io.vfs.VFS;
import ananas.lib.io.vfs.VFile;
import ananas.lib.io.vfs.VFileSystem;
import ananas.lib.util.PropertiesLoader;
import ananas.waymq.api.IDocument;
import ananas.waymq.api.IEvent;
import ananas.waymq.api.IGroup;
import ananas.waymq.api.IMember;
import ananas.waymq.api.IMemberPhone;
import ananas.waymq.core.DocumentFactory;

public class TestWayMQ2 {

	@Test
	public void test() {

		VFile file = this.init();

		// repo
		IDocument doc = DocumentFactory.load(file);

		IMember root = doc.getRoot();
		IGroup group = root.createGroup("wayMQ");
		IMemberPhone phone = doc.newMemberPhone("12345678901");
		IMember user1 = doc.newMember("tester", phone);
		user1.join(group);
		IEvent event = user1.createEvent("", group);
		user1.join(event);

		doc.save();
	}

	private VFile init() {
		String fn = "sys.properties";
		PropertiesLoader.Util.loadPropertiesToSystem(this, fn);

		VFile file = this.__get_project_dir();
		VFileSystem vfs = file.getVFS();
		VFile rdir = vfs.newFile(file, "test/repo/.wayMQ");
		System.out.println("project-dir : " + file);

		VFile mf = vfs.newFile(rdir.getParentFile(), "main.json");
		return mf;

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

		(new TestWayMQ2()).test();

	}

}
