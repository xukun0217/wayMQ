package ananas.waymq.web.model;

import ananas.sf4lib.server.store.hashobject.AbstractDao4HOJson;
import ananas.sf4lib.server.store.hashobject.Dao4HOJson;
import ananas.xgit4.HashID;

import com.alibaba.fastjson.JSONObject;

public class EventHODAO extends AbstractDao4HOJson<Event> {

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
			// TODO Auto-generated method stub
			return json;
		}

		@Override
		public JSONObject getMutable(Event obj) {
			JSONObject json = new JSONObject();
			// TODO Auto-generated method stub
			return json;
		}

		@Override
		public void setModel(Event obj, HashID id, JSONObject static_,
				JSONObject mutable) {
			// TODO Auto-generated method stub

		}

		@Override
		public void setModel(Event obj, HashID id) {
			obj.id = id;
		}
	}

}
