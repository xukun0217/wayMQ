package ananas.waymq.web.model;

import ananas.sf4lib.server.store.hashobject.AbstractDao4HOJson;
import ananas.sf4lib.server.store.hashobject.Dao4HOJson;
import ananas.xgit4.HashID;

import com.alibaba.fastjson.JSONObject;

public class GroupHODAO extends AbstractDao4HOJson<Group> {

	interface Key {

		// static
		String group_name = "group_name";

		// mutable
		String description = "description";
		String title = "title";
		String current_event = "current_event";
	}

	public GroupHODAO() {
		super(new MyDeletate());
	}

	private static class MyDeletate implements Dao4HOJson<Group> {

		@Override
		public Class<Group> modelClass() {
			return Group.class;
		}

		@Override
		public HashID getId(Group obj) {
			return obj.id;
		}

		@Override
		public JSONObject getStatic(Group obj) {
			JSONObject json = new JSONObject();
			json.put(Key.group_name, Helper.idToString(obj.s_name));
			return json;
		}

		@Override
		public JSONObject getMutable(Group obj) {
			JSONObject json = new JSONObject();
			json.put(Key.description, obj.m_description);
			json.put(Key.title, obj.m_title);
			json.put(Key.current_event, Helper.idToString(obj.m_event_current));
			return json;
		}

		@Override
		public void setModel(Group obj, HashID id, JSONObject static_,
				JSONObject mutable) {

			obj.id = id;
			if (static_ != null) {
				obj.s_name = Helper.getId(static_, Key.group_name);
			}
			if (mutable != null) {
				obj.m_description = mutable.getString(Key.description);
				obj.m_title = mutable.getString(Key.title);
				obj.m_event_current = Helper.getId(mutable, Key.current_event);
			}
		}

		@Override
		public void setModel(Group obj, HashID id) {
			obj.id = id;
		}
	}

	static class Helper {

		public static HashID getId(JSONObject json, String key) {
			if (json == null)
				return null;
			String val = json.getString(key);
			if (val == null)
				return null;
			if (val.length() < 1)
				return null;
			return HashID.Factory.create(val);
		}

		public static String idToString(HashID id) {
			if (id == null)
				return null;
			return id.toString();
		}

	}

}
