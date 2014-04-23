package ananas.waymq.web.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import ananas.sf4lib.server.store.hashobject.AbstractDao4HOJson;
import ananas.sf4lib.server.store.hashobject.Dao4HOJson;
import ananas.waymq.web.model.JoinList.Item;
import ananas.xgit4.HashID;

import com.alibaba.fastjson.JSONObject;

public class JoinListHODAO extends AbstractDao4HOJson<JoinList> {

	interface Key {

		String s_owner_event = "owner_event";
		String m_table = "table";

	}

	interface KeyJoin {

		String join = "join";
		String nickname = "nickname";
		String phone = "phone_id";
		String last_modified = "last_modified";
		String first_modified = "first_modified";
		String count = "count";

	}

	public JoinListHODAO() {
		super(new MyDeletate());
	}

	private static class MyDeletate implements Dao4HOJson<JoinList> {

		@Override
		public Class<JoinList> modelClass() {
			return JoinList.class;
		}

		@Override
		public HashID getId(JoinList obj) {
			return obj.id;
		}

		@Override
		public JSONObject getStatic(JoinList obj) {
			JSONObject json = new JSONObject();
			json.put(Key.s_owner_event, Helper.toString(obj.s_owner_event));
			return json;
		}

		@Override
		public JSONObject getMutable(JoinList obj) {
			JSONObject json = new JSONObject();
			JSONObject table = Helper.saveTable(obj);
			json.put(Key.m_table, table);
			return json;
		}

		@Override
		public void setModel(JoinList obj, HashID id, JSONObject static_,
				JSONObject mutable) {

			// id
			obj.id = id;
			// static
			if (static_ != null) {
				obj.s_owner_event = Helper.getId(static_, Key.s_owner_event);
			}
			// mutable
			if (mutable != null) {
				JSONObject table = mutable.getJSONObject(Key.m_table);
				Helper.loadTable(obj, table);
			}
		}

		@Override
		public void setModel(JoinList obj, HashID id) {
			obj.id = id;
		}

	}

	static class Helper {

		public static String toString(HashID id) {
			if (id == null)
				return null;
			return id.toString();
		}

		public static void loadTable(JoinList obj, JSONObject table) {
			Map<String, Item> map = new HashMap<String, Item>();
			Collection<Object> vals = table.values();
			for (Object val : vals) {
				if (val instanceof JSONObject) {
					JSONObject jit = (JSONObject) val;
					Item item = new Item();
					item.phone = jit.getString(KeyJoin.phone);
					item.nickname = jit.getString(KeyJoin.nickname);
					item.join = jit.getBooleanValue(KeyJoin.join);
					item.count = jit.getIntValue(KeyJoin.count);
					item.first_modified = jit
							.getLongValue(KeyJoin.first_modified);
					item.last_modified = jit
							.getLongValue(KeyJoin.last_modified);
					map.put(item.phone + "", item);
				}
			}
			obj.m_map.clear();
			obj.m_map.putAll(map);
		}

		public static JSONObject saveTable(JoinList obj) {
			Map<String, Item> map = obj.m_map;
			JSONObject json = new JSONObject();
			Set<String> keys = map.keySet();
			for (String key : keys) {
				Item item = map.get(key);
				JSONObject jit = new JSONObject();
				jit.put(KeyJoin.join, item.join);
				jit.put(KeyJoin.nickname, item.nickname);
				jit.put(KeyJoin.phone, item.phone);
				jit.put(KeyJoin.last_modified, item.last_modified);
				jit.put(KeyJoin.first_modified, item.first_modified);
				jit.put(KeyJoin.count, item.count);
				json.put(key, jit);
			}
			return json;
		}

		public static HashID getId(JSONObject json, String key) {
			if (json == null)
				return null;
			String val = json.getString(key);
			if (val == null)
				return null;
			return HashID.Factory.create(val);
		}
	}
}
