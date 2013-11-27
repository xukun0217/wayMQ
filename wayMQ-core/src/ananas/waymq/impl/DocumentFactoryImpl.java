package ananas.waymq.impl;

import ananas.lib.io.vfs.VFile;
import ananas.lib.io.vfs.VFileSystem;
import ananas.waymq.api.IDocument;
import ananas.waymq.api.IDocumentFactory;
import ananas.waymq.core.IDocumentCore;
import ananas.waymq.core.wrapper.WrapDocument;

public class DocumentFactoryImpl implements IDocumentFactory {

	interface Const {

		String repo_dir_name = ".wayMQ";

	}

	private DocumentFactoryImpl() {
	}

	@Override
	public IDocument create(VFile file) {
		if (!file.getName().equals(Const.repo_dir_name)) {
			VFileSystem vfs = file.getVFS();
			file = vfs.newFile(file, Const.repo_dir_name);
		}

		IDocumentCore dc = new DocumentCoreImpl(file);
		IDocument doc = new WrapDocument(dc);
		doc.check();

		if (doc.exists()) {
			throw new RuntimeException("the repo has exists : " + doc.getFile());
		}
		doc.init();
		return doc;
	}

	public static IDocumentFactory getInstance() {
		return new DocumentFactoryImpl();
	}

	@Override
	public IDocument open(VFile file) {
		VFileSystem vfs = file.getVFS();
		VFile p = file;
		for (; p != null; p = p.getParentFile()) {
			VFile file2 = vfs.newFile(p, Const.repo_dir_name);
			if (file2.exists()) {
				file = file2;
				break;
			}
		}
		if (p == null) {
			throw new RuntimeException("cannot find .wayMQ repo at the path : "
					+ file);
		}
		IDocumentCore dc = new DocumentCoreImpl(file);
		IDocument doc = new WrapDocument(dc);
		doc.check();
		return doc;
	}

}
