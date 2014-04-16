package ananas.waymq.web.model;

import ananas.sf4lib.server.store.hashobject.AbstractDao4HOJson;
import ananas.sf4lib.server.store.hashobject.Dao4HOJson;
import ananas.xgit4.HashID;

import com.alibaba.fastjson.JSONObject;

public class GroupHODAO extends AbstractDao4HOJson<Group> {

	public GroupHODAO() {
		super(new MyDeletate());
	}

	static class MyDeletate implements Dao4HOJson<Group> {

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
			// TODO Auto-generated method stub
			return json;
		}

		@Override
		public JSONObject getMutable(Group obj) {
			JSONObject json = new JSONObject();
			// TODO Auto-generated method stub
			return json;
		}

		@Override
		public void setModel(Group obj, HashID id, JSONObject static_,
				JSONObject mutable) {
			// TODO Auto-generated method stub

			obj.id = id;

		}

		@Override
		public void setModel(Group obj, HashID id) {
			// TODO Auto-generated method stub
			obj.id = id;

		}
	}

}
