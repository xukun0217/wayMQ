package ananas.waymq.api;

import ananas.lib.io.vfs.VFile;

public interface IDocumentFactory {

	IDocument create(VFile file);

	IDocument open(VFile file);

}
