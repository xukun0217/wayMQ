package ananas.waymq.web.model;

import ananas.sf4lib.server.store.hashobject.AbstractDao4HOString;
import ananas.sf4lib.server.store.hashobject.Dao4HOString;
import ananas.xgit4.HashID;

import com.alibaba.fastjson.JSONObject;

public class GroupNameHODAO extends AbstractDao4HOString<GroupName> {

	interface Key {
		String mut_group_id = "group_id";
	}

	public GroupNameHODAO() {
		super(new MyDeletate());
	}

	static class MyDeletate implements Dao4HOString<GroupName> {

		@Override
		public Class<GroupName> modelClass() {
			return GroupName.class;
		}

		@Override
		public HashID getId(GroupName obj) {
			return obj.id;
		}

		@Override
		public String getStatic(GroupName obj) {
			return obj.name;
		}

		@Override
		public JSONObject getMutable(GroupName obj) {
			JSONObject json = new JSONObject();
			String val = Helper.idToString(obj.group_id);
			json.put(Key.mut_group_id, val);
			return json;
		}

		@Override
		public void setModel(GroupName obj, HashID id) {
			obj.id = id;
		}

		@Override
		public void setModel(GroupName obj, HashID id, String static_,
				JSONObject mutable) {
			obj.id = id;
			obj.name = static_;
			obj.group_id = Helper.getHashID(mutable, Key.mut_group_id);
		}
	}

	static class Helper {

		public static HashID getHashID(JSONObject json, String key) {
			if (json == null)
				return null;
			String str = json.getString(key);
			if (str == null)
				return null;
			return HashID.Factory.create(str);
		}

		public static String idToString(HashID id) {
			if (id == null)
				return null;
			return id.toString();
		}
	}
}
