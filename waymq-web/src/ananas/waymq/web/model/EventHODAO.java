package ananas.waymq.web.model;

import ananas.sf4lib.server.store.hashobject.AbstractDao4HOJson;
import ananas.sf4lib.server.store.hashobject.Dao4HOJson;
import ananas.xgit4.HashID;

import com.alibaba.fastjson.JSONObject;

public class EventHODAO extends AbstractDao4HOJson<Event> {

	interface Key {

		String s_owner_group = "owner_group";
		String s_prev_event = "prev_event";

		String m_content = "content";
		String m_title = "title";
		String m_time_open = "time_open";
		String m_time_close = "time_close";
		String m_time_create = "time_create";
		String m_join_list = "join_list";
	}

	public EventHODAO() {
		super(new MyDelegate());
	}

	static class MyDelegate implements Dao4HOJson<Event> {

		@Override
		public Class<Event> modelClass() {
			return Event.class;
		}

		@Override
		public HashID getId(Event obj) {
			return obj.id;
		}

		@Override
		public JSONObject getStatic(Event obj) {
			JSONObject json = new JSONObject();
			json.put(Key.s_owner_group, Helper.idToString(obj.s_owner_group));
			json.put(Key.s_prev_event, Helper.idToString(obj.s_prev_event));
			return json;
		}

		@Override
		public JSONObject getMutable(Event obj) {
			JSONObject json = new JSONObject();
			json.put(Key.m_title, obj.m_title);
			json.put(Key.m_content, obj.m_content);
			json.put(Key.m_time_close, obj.m_time_close);
			json.put(Key.m_time_open, obj.m_time_open);
			json.put(Key.m_time_create, obj.m_time_create);
			json.put(Key.m_join_list, Helper.idToString(obj.m_join_list));
			return json;
		}

		@Override
		public void setModel(Event obj, HashID id, JSONObject static_,
				JSONObject mutable) {
			// id
			obj.id = id;
			// static
			if (static_ != null) {
				obj.s_owner_group = Helper.getId(static_, Key.s_owner_group);
				obj.s_prev_event = Helper.getId(static_, Key.s_prev_event);
			}
			// mutable
			if (mutable != null) {
				obj.m_content = mutable.getString(Key.m_content);
				obj.m_title = mutable.getString(Key.m_title);
				obj.m_time_create = mutable.getLongValue(Key.m_time_create);
				obj.m_time_open = mutable.getLongValue(Key.m_time_open);
				obj.m_time_close = mutable.getLongValue(Key.m_time_close);
				obj.m_join_list = Helper.getId(mutable, Key.m_join_list);
			}
		}

		@Override
		public void setModel(Event obj, HashID id) {
			obj.id = id;
		}
	}

	static class Helper {

		public static HashID getId(JSONObject json, String key) {
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
