package ananas.waymq.tools.counter;

import java.io.File;
import java.util.Arrays;

public class HtmlFinder {

	public interface Callback {

		void onDir(File dir);

		void onFile(File file);

	}

	public void findHTML(File dir, Callback callback) {
		this.findHTML(dir, callback, 10);
	}

	private void findHTML(File dir, Callback callback, int depthLimit) {

		if (depthLimit < 0)
			throw new RuntimeException("too deep.");

		if (!dir.isDirectory())
			return;

		String[] list = dir.list();
		Arrays.sort(list);
		for (String name : list) {
			File ch = new File(dir, name);
			if (ch.isDirectory()) {
				callback.onDir(ch);
				this.findHTML(ch, callback, depthLimit - 1);
			} else {
				String s2 = name.toLowerCase();
				if (s2.endsWith(".html"))
					callback.onFile(ch);
			}
		}
	}

}
