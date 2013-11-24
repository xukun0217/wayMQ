package test.waymq.terminal;

import java.util.Map;

import ananas.blueprint4.terminal.ExecuteContext;
import ananas.waymq.api.IDocument;
import ananas.waymq.impl2.NewDocument;

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
			doc = new NewDocument(null);
			map.put(key, doc);
		}
		return doc;
	}

}
