package ananas.waymq.ht5.litegroup;

import java.io.File;

import com.alibaba.fastjson.JSONObject;

public class DaoForGroup {

	private final File _path;

	public DaoForGroup(File path) {
		this._path = path;
	}

	public File getInfoFile() {
		return new File(_path, "info.json");
	}

	public JSONObject loadInfo() {
		File file = this.getInfoFile();
		return Util.loadJsonFromFile(file);
	}
}
