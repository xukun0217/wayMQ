package ananas.waymq.core;

import ananas.lib.io.vfs.VFile;
import ananas.waymq.api.IDocument;
import ananas.waymq.api.IDocumentFactory;
import ananas.waymq.impl.DocumentFactoryImpl;

public class DefaultDocumentFactory implements IDocumentFactory {

	private final IDocumentFactory impl;

	public DefaultDocumentFactory() {
		this.impl = DocumentFactoryImpl.getInstance();
	}

	@Override
	public IDocument create(VFile file) {
		return impl.create(file);
	}

	@Override
	public IDocument open(VFile file) {
		return impl.open(file);
	}

}
