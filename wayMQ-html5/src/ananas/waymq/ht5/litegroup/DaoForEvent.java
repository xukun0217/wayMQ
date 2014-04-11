package ananas.waymq.ht5.litegroup;

import java.io.File;
import java.io.FilenameFilter;

import com.alibaba.fastjson.JSONObject;

public class DaoForEvent {

	private File _path;

	public DaoForEvent(LG2Repo repo, String offset) {
		File path = new File(repo.getPath(), offset);
		this._path = path;
	}

	public File getPath() {
		return this._path;
	}

	public JSONObject loadJoinShip(String phone) {
		File file = this.getJoinFile(phone);
		return Util.loadJsonFromFile(file);
	}

	public void saveJoinShip(String phone, JSONObject json) {
		File file = this.getJoinFile(phone);
		Util.saveJsonToFile(json, file);
	}

	public void saveInfo(JSONObject info) {
		File file = this.getInfoFile();
		if (!file.exists()) {
			File parent = file.getParentFile();
			if (!parent.exists()) {
				parent.mkdirs();
			}
		}
		Util.saveJsonToFile(info, file);
	}

	public void clearOutput() {
		this.getOutputFile().delete();
	}

	public JSONObject loadOutput() {
		File file = this.getOutputFile();
		return Util.loadJsonFromFile(file);
	}

	public JSONObject loadInfo() {
		File file = this.getInfoFile();
		return Util.loadJsonFromFile(file);
	}

	public File getOutputFile() {
		return new File(this._path, "output.json");
	}

	public File getInfoFile() {
		return new File(this._path, "info.json");
	}

	public File getJoinFile(String phone) {
		phone = Util.normalizePhoneNumber(phone);
		String str = phone;
		return new File(this._path, str + ".join.json");
	}

	public File[] listJoinFile() {
		FilenameFilter filter = new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith("join.json");
			}
		};
		return this._path.listFiles(filter);
	}

	public JSONObject loadJoinShip(File file) {
		return Util.loadJsonFromFile(file);
	}

	public void saveOutput(JSONObject json) {
		File file = this.getOutputFile();
		Util.saveJsonToFile(json, file);
	}

	public DaoForGroup getGroupDao() {
		File path = this._path.getParentFile();
		return new DaoForGroup(path);
	}
}
