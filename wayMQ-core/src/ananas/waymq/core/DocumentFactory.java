package ananas.waymq.core;

import ananas.lib.io.vfs.VFile;
import ananas.waymq.api.IDocument;
import ananas.waymq.impl2.NewDocument;

public class DocumentFactory {

	public static IDocument load(VFile main) {
		NewDocument doc = new NewDocument(main);
		doc.load();
		return doc;
	}

}
