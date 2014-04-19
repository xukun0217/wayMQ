package ananas.waymq.web.controller;

import ananas.sf4lib.server.RequestContext;
import ananas.sf4lib.server.jrp.JRPController;
import ananas.sf4lib.server.store.StoreTransaction;
import ananas.waymq.web.model.Group;
import ananas.waymq.web.model.GroupName;
import ananas.xgit4.HashID;

import com.alibaba.fastjson.JSONObject;

public class GroupController implements JRPController {

	interface Key {

		String req_name = "name";
		String req_title = "title";
		String req_description = "description";

		String res_group_id = "group_id";
		String res_current_event = "current_event";
		String res_name = "name";
		String res_title = "title";
		String res_description = "description";

	}

	@Override
	public void response(RequestContext context) {
		boolean unsupported = false;
		String do_ = context.getRequestMethod();
		if (do_ == null) {
			do_ = "null";
			unsupported = true;

		} else if (do_.equals("getIdByName")) {
			this.getIdByName(context);

		} else if (do_.equals("setInfo")) {
			this.setInfo(context);

		} else if (do_.equals("getInfo")) {
			this.getInfo(context);

		} else if (do_.equals("exists")) {
			this.exists(context);

		} else if (do_.equals("todo")) {
			this.todo(context);

		} else {
			unsupported = true;
		}
		if (unsupported) {
			context.setError("unsupported method : " + do_);
		}
	}

	private void exists(RequestContext context) {

		String gid = context.getRequestThis();
		StoreTransaction tran = context.openStoreTransaction();
		Group group = (Group) tran.get(Group.class, gid);
		JSONObject json = context.getResultJSON();
		json.put("exists", (group != null));

	}

	private void setInfo(RequestContext context) {

		String id = context.getRequestThis();
		StoreTransaction tran = context.openStoreTransaction();
		Group group = (Group) tran.get(Group.class, id);
		if (group == null) {
			context.setError("no this group: " + id);
			return;
		}

		group.m_title = context.getParameter(Key.req_title, group.m_title);
		group.m_description = context.getParameter(Key.req_description,
				group.m_description);

		tran.update(group);
		tran.commit();

	}

	private void getInfo(RequestContext context) {
		String id = context.getRequestThis();
		StoreTransaction tran = context.openStoreTransaction();
		Group group = (Group) tran.get(Group.class, id);
		if (group == null) {
			context.setError("no this group: " + id);
			return;
		}
		GroupName gname = (GroupName) tran.get(GroupName.class, group.s_name);
		if (gname == null) {
			context.setError("no this group: " + id);
			return;
		}
		JSONObject res = context.getResultJSON();
		res.put(Key.res_group_id, Helper.idToString(group.id));
		res.put(Key.res_title, group.m_title);
		res.put(Key.res_description, group.m_description);
		res.put(Key.res_name, gname.name);

		res.put(Key.res_current_event, Helper.idToString(group.m_event_current));

	}

	private void getIdByName(RequestContext context) {
		String name = context.getParameter(Key.req_name);
		StoreTransaction tran = context.openStoreTransaction();
		// create name
		GroupName gname = new GroupName();
		gname.name = name;
		gname = (GroupName) tran.insert(gname);
		if (gname.group_id == null) {
			context.setError("no group with name:" + name);
			return;
		}
		// get group
		JSONObject json = context.getResultJSON();
		json.put(Key.res_group_id, Helper.idToString(gname.group_id));
	}

	static class Helper {

		public static String idToString(HashID id) {
			if (id == null)
				return null;
			return id.toString();
		}
	}

	private void todo(RequestContext context) {

	}
}
