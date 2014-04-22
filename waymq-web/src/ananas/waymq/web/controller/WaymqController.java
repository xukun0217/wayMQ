package ananas.waymq.web.controller;

import ananas.sf4lib.server.RequestContext;
import ananas.sf4lib.server.jrp.JRPController;
import ananas.sf4lib.server.store.StoreTransaction;
import ananas.waymq.web.model.Event;
import ananas.waymq.web.model.Group;
import ananas.waymq.web.model.GroupName;
import ananas.waymq.web.model.JoinList;
import ananas.xgit4.HashID;

import com.alibaba.fastjson.JSONObject;

public class WaymqController implements JRPController {

	interface Key {

		String req_name = "name";
		String req_group = "group";
		String req_title = "title";
		String req_content = "content";
		String req_time_open = "time_open";

		String res_group = "group";
		String res_event = "event";
	}

	@Override
	public void response(RequestContext context) {
		boolean unsupported = false;
		String do_ = context.getRequestMethod();
		if (do_ == null) {
			do_ = "null";
			unsupported = true;

		} else if (do_.equals("newGroup")) {
			this.newGroup(context);

		} else if (do_.equals("newEvent")) {
			this.newEvent(context);

		} else if (do_.equals("todo")) {
			this.todo(context);

		} else {
			unsupported = true;
		}
		if (unsupported) {
			context.setError("unsupported method : " + do_);
		}
	}

	private void newGroup(RequestContext context) {

		String name = context.getParameter(Key.req_name);
		StoreTransaction tran = context.openStoreTransaction();
		// create name
		GroupName gname = new GroupName();
		gname.name = name;
		gname = (GroupName) tran.insert(gname);
		// create group
		if (gname.group_id != null) {
			context.setError("the group has exists: " + name);
			return;
		}
		Group group = new Group();
		group.s_name = gname.id;
		group = (Group) tran.insert(group);

		// finish
		gname.group_id = group.id;

		tran.commit();
	}

	private void newEvent(RequestContext context) {

		String group_id = context.getParameter(Key.req_group);
		String title = context.getParameter(Key.req_title);
		String content = context.getParameter(Key.req_content);
		String t_open = context.getParameter(Key.req_time_open);

		StoreTransaction tran = context.openStoreTransaction();

		// get group
		Group group = (Group) tran.get(Group.class, group_id);
		if (group == null) {
			context.setError("no this group: " + group_id);
			return;
		}

		// new event
		Event event = new Event();
		event.s_owner_group = group.id;
		event.s_prev_event = group.m_event_current;
		event = (Event) tran.insert(event);

		// new join list
		JoinList jlist = new JoinList();
		jlist.s_owner_event = event.id;
		jlist = (JoinList) tran.insert(jlist);

		// mutable
		event.m_title = title;
		event.m_content = content;
		event.m_time_open = Long.parseLong(t_open);
		event.m_time_create = System.currentTimeMillis();
		event.m_join_list = jlist.id;
		group.m_event_current = event.id;

		// result
		JSONObject res = context.getResultJSON();
		res.put(Key.res_event, Helper.idToString(event.id));

		// commit
		tran.update(event);
		tran.update(group);
		tran.update(jlist);
		tran.commit();
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

	}

}
