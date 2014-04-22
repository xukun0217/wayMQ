package ananas.waymq.web.controller;

import java.util.Collection;

import ananas.sf4lib.server.RequestContext;
import ananas.sf4lib.server.jrp.JRPController;
import ananas.sf4lib.server.store.StoreTransaction;
import ananas.waymq.web.model.Event;
import ananas.waymq.web.model.JoinList;
import ananas.waymq.web.model.JoinList.Item;
import ananas.xgit4.HashID;

import com.alibaba.fastjson.JSONObject;

public class EventController implements JRPController {

	interface JoinItemKey {

		String join = "join";
		String last_modified = "time_mod";
		String first_modified = "time_create";
		String nickname = "nickname";
		String count = "count";
		String phone = "phone_id";

	}

	interface Key {

		String res_event_id = "event_id";
		String res_owner_group = "owner_group";
		String res_title = "title";
		String res_detail = "detail";
		String res_time_open = "time_open";
		String res_time_close = "time_close";
		String res_time_create = "time_create";

		String req_join_phone = "phone";
		String req_join_nickname = "nickname";
		String req_join_count = "count";
		String res_join_table = "join_table";

	}

	@Override
	public void response(RequestContext context) {
		boolean unsupported = false;
		String do_ = context.getRequestMethod();
		if (do_ == null) {
			do_ = "null";
			unsupported = true;

		} else if (do_.equals("getJoinTable")) {
			this.getJoinTable(context);

		} else if (do_.equals("getInfo")) {
			this.getInfo(context);

		} else if (do_.equals("exit")) {
			this.exit(context);

		} else if (do_.equals("join")) {
			this.join(context);

		} else if (do_.equals("todo")) {
			this.todo(context);

		} else {
			unsupported = true;
		}
		if (unsupported) {
			context.setError("unsupported method : " + do_);
		}
	}

	private void getJoinTable(RequestContext context) {

		StoreTransaction tran = context.openStoreTransaction();
		String eid = context.getRequestThis();
		Event event = (Event) tran.get(Event.class, eid);
		if (event == null) {
			context.setError("no this event : " + eid);
			return;
		}
		JoinList jlist = (JoinList) tran.get(JoinList.class, event.m_join_list);
		if (jlist == null) {
			context.setError("no this joinlist : " + event.m_join_list);
			return;
		}
		JSONObject jtable = new JSONObject();
		Collection<Item> items = jlist.m_map.values();
		for (Item item : items) {
			String key = item.phone;
			JSONObject value = Helper.toJSONObject(item);
			jtable.put(key, value);
		}
		context.getResultJSON().put(Key.res_join_table, jtable);
	}

	private void exit(RequestContext context) {

		String eid = context.getRequestThis();
		StoreTransaction tran = context.openStoreTransaction();
		Event event = (Event) tran.get(Event.class, eid);
		if (event == null) {
			context.setError("cannot find the event: " + eid);
			return;
		}
		JoinList jlist = (JoinList) tran.get(JoinList.class, event.m_join_list);
		if (jlist == null) {
			context.setError("cannot find the joinlist : " + event.m_join_list);
			return;
		}

		String phone = context.getParameter(Key.req_join_phone);
		phone = Util.normalizePhoneId(phone);

		// make item
		Item item = jlist.m_map.get(phone);
		if (item == null) {
			context.setError("the phone has not joined : " + phone);
			return;
		}
		item.last_modified = System.currentTimeMillis();
		item.join = false;

		// commit
		tran.update(jlist);
		tran.commit();
	}

	private void join(RequestContext context) {

		String eid = context.getRequestThis();
		StoreTransaction tran = context.openStoreTransaction();
		Event event = (Event) tran.get(Event.class, eid);
		if (event == null) {
			context.setError("cannot find the event: " + eid);
			return;
		}
		JoinList jlist = (JoinList) tran.get(JoinList.class, event.m_join_list);
		if (jlist == null) {
			context.setError("cannot find the joinlist : " + event.m_join_list);
			return;
		}

		int count = Integer.parseInt(context.getParameter(Key.req_join_count));
		String nickname = context.getParameter(Key.req_join_nickname);
		String phone = context.getParameter(Key.req_join_phone);
		phone = Util.normalizePhoneId(phone);

		// make item
		final long now = System.currentTimeMillis();
		Item item = jlist.m_map.get(phone);
		if (item == null) {
			item = new JoinList.Item();
			item.phone = phone;
			item.first_modified = now;
			jlist.m_map.put(phone, item);
		}
		item.count = count;
		item.nickname = nickname;
		item.last_modified = now;
		item.join = true;

		// commit
		tran.update(jlist);
		tran.commit();
	}

	private void getInfo(RequestContext context) {

		StoreTransaction tran = context.openStoreTransaction();
		String id = context.getRequestThis();
		Event event = (Event) tran.get(Event.class, id);
		if (event == null) {
			context.setError("cannot find event with id: " + id);
			return;
		}

		JSONObject json = context.getResultJSON();
		json.put(Key.res_event_id, Helper.idToString(event.id));
		json.put(Key.res_owner_group, Helper.idToString(event.s_owner_group));
		json.put(Key.res_title, event.m_title);
		json.put(Key.res_detail, event.m_content);
		json.put(Key.res_time_close, event.m_time_close);
		json.put(Key.res_time_create, event.m_time_create);
		json.put(Key.res_time_open, event.m_time_open);

	}

	private void todo(RequestContext context) {
		// TODO Auto-generated method stub

	}

	static class Helper {

		public static String idToString(HashID id) {
			if (id == null)
				return null;
			return id.toString();
		}

		public static JSONObject toJSONObject(Item item) {
			JSONObject json = new JSONObject();
			json.put(JoinItemKey.join, item.join);
			json.put(JoinItemKey.phone, item.phone);
			json.put(JoinItemKey.count, item.count);
			json.put(JoinItemKey.nickname, item.nickname);
			json.put(JoinItemKey.first_modified, item.first_modified);
			json.put(JoinItemKey.last_modified, item.last_modified);
			return json;
		}
	}
}
