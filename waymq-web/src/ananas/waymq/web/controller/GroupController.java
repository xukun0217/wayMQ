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

		String res_group_id = "group_id";

	}

	@Override
	public void response(RequestContext context) {
		boolean unsupported = false;
		String do_ = context.getRequestMethod();
		if (do_ == null) {
			do_ = "null";
			unsupported = true;

		} else if (do_.equals("create")) {
			this.create(context);

		} else if (do_.equals("getIdByName")) {
			this.getIdByName(context);

		} else if (do_.equals("todo")) {
			this.todo(context);

		} else {
			unsupported = true;
		}
		if (unsupported) {
			context.setError("unsupported method : " + do_);
		}
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

	private void create(RequestContext context) {

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
		group.name = gname.id;
		group = (Group) tran.insert(group);

		// finish
		gname.group_id = group.id;

		tran.commit();
	}

	static class Helper {

		public static String idToString(HashID id) {
			if (id == null)
				return null;
			return id.toString();
		}
	}

	private void todo(RequestContext context) {
		// TODO Auto-generated method stub

	}

}
