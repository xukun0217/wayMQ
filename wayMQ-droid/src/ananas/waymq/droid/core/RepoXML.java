package ananas.waymq.droid.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;

public class RepoXML {

	private final File _file;
	private Document _dom;

	public RepoXML(File file) {
		this._file = file;
	}

	public void load() {
		try {
			this._dom = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().parse(_file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void save() {
		try {
			Document dom = this._dom;
			if (dom == null)
				return;
			DOMImplementationLS ls = (DOMImplementationLS) dom
					.getImplementation().getFeature("LS", "3.0");
			LSSerializer ser = ls.createLSSerializer();
			String str = ser.writeToString(dom);
			OutputStream out = new FileOutputStream(this._file);
			out.write(str.getBytes("UTF-8"));
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public IMemberList getMemberList() {

		Document dom = this._dom;
		dom.getFirstChild();
		return null;

	}

}
