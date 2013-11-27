package ananas.waymq.core.wrapper;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import ananas.lib.io.vfs.VFile;
import ananas.lib.io.vfs.VFileInputStream;
import ananas.lib.io.vfs.VFileOutputStream;
import ananas.objectbox.IObject;
import ananas.waymq.api.IDocument;
import ananas.waymq.api.IElement;
import ananas.waymq.core.HeadMap;
import ananas.waymq.core.IObjectCore;
import ananas.waymq.core.IWrapper;
import ananas.xgit.repo.ObjectId;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class BaseWrapper implements IElement, IWrapper {

	private IObjectCore _obj_core;

	public BaseWrapper() {
	}

	@Override
	public IDocument getDocument() {
		return this._obj_core.getDocument();
	}

	@Override
	public ObjectId getId() {
		return this._obj_core.getId();
	}

	@Override
	public IObjectCore getObjectCore() {
		return this._obj_core;
	}

	@Override
	public void setObjectCore(IObjectCore core) {
		this._obj_core = core;
	}

	@Override
	public final void onSave(IObjectCore core) {
		IObject obj = core.getObject();
		VFile file = obj.getBodyFile();
		if (!file.exists()) {
			file.getParentFile().mkdirs();
		}
		JSONObject body = new JSONObject();
		this.onSave(body);

		try {
			byte[] ba = JSON.toJSONBytes(body);
			OutputStream out = new VFileOutputStream(file);
			out.write(ba);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public final void onLoad(IObjectCore core) {

		IObject obj = core.getObject();
		VFile file = obj.getBodyFile();
		Map<String, String> head = new HeadMap( obj  );
		JSONObject body = null;
		if (file.exists()) {
			try {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buf = new byte[1024];
				InputStream in = new VFileInputStream(file);
				for (;;) {
					int cb = in.read(buf);
					if (cb < 0)
						break;
					baos.write(buf, 0, cb);
				}
				in.close();
				body = (JSONObject) JSON.parse(baos.toByteArray());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (body == null) {
			body = new JSONObject();
		}
		this.onLoad(head, body);
	}

	@Override
	public void onLoad(Map<String, String> head, JSONObject body) {
	}

	@Override
	public void onSave(JSONObject body) {
	}

}
