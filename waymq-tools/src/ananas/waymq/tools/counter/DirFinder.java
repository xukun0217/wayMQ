package ananas.waymq.tools.counter;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

public class DirFinder {

	interface PropertiesAcceptor {
		boolean accept(Properties properties);
	}

	public void find() {

		File base = this.getBase();
		System.out.println("find from base " + base);
		File root = this.findUp(".waymq", base, new PropertiesAcceptor() {

			@Override
			public boolean accept(Properties properties) {
				return true;
			}
		});
		File events = this.findDown(".waymq", root.getParentFile(),
				new PropertiesAcceptor() {

					@Override
					public boolean accept(Properties properties) {
						// TODO Auto-generated method stub
						return false;
					}
				});

	}

	private File findDown(String target, File dir, PropertiesAcceptor acc,
			int depthLimit) {
		if (depthLimit < 0)
			return null;
		if (!dir.isDirectory())
			return null;
		File file = new File(dir, target);
		if (file.exists()) {
			Properties pro = this.loadProperties(file);
			if (acc.accept(pro))
				return file;
		}
		// find into dir
		File[] list = dir.listFiles();
		for (File ch : list) {
			if (ch.isDirectory()) {
				File rlt = this.findDown(target, ch, acc, depthLimit - 1);
				if (rlt != null)
					return rlt;
			}
		}
		return null;
	}

	private File findDown(String target, File root, PropertiesAcceptor acc) {
		return this.findDown(target, root, acc, 10);
	}

	private File findUp(String target, File dir, PropertiesAcceptor acc) {
		for (; dir != null; dir = dir.getParentFile()) {
			File file = new File(dir, target);
			if (file.exists())
				if (file.isFile()) {
					Properties pro = this.loadProperties(file);
					if (acc.accept(pro)) {
						return file;
					}
				}
		}
		return null;
	}

	private Properties loadProperties(File file) {

		System.out.println("load properties " + file);

		// TODO Auto-generated method stub
		return null;
	}

	private File getBase() {
		try {
			URI uri = this.getClass().getProtectionDomain().getCodeSource()
					.getLocation().toURI();
			File file = new File(uri);
			return file;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	public File getResultDir() {
		// TODO Auto-generated method stub
		return null;
	}

}
