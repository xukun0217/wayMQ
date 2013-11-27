package test.waymq.terminal;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

import ananas.blueprint4.terminal.ExecuteContext;
import ananas.lib.io.vfs.VFS;
import ananas.lib.io.vfs.VFile;
import ananas.lib.io.vfs.VFileSystem;
import ananas.waymq.api.IDocument;
import ananas.waymq.core.DefaultDocumentFactory;

public class MyGlobal {

	private ExecuteContext _context;

	public MyGlobal(ExecuteContext context) {
		this._context = context;
	}

	public IDocument getDocument() {
		String key = IDocument.class.getName();
		Map<String, Object> map = this._context.getTerminal().getGlobal();
		IDocument doc = (IDocument) map.get(key);
		if (doc == null) {
			VFile file = this.__get_file();
			if (file.exists())
				doc = (new DefaultDocumentFactory()).open(file);
			else
				doc = (new DefaultDocumentFactory()).create(file);
			map.put(key, doc);
		}
		System.out.println("new doc : " + doc.getFile());
		return doc;
	}

	private VFile __get_file() {
		URL url = this.getClass().getProtectionDomain().getCodeSource()
				.getLocation();
		VFileSystem vfs = VFS.getDefaultFactory().defaultFileSystem();
		VFile file = null;
		try {
			file = vfs.newFile(url.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		file = vfs.newFile(file.getParentFile(), "test/repo");
		return file;

	}

}
