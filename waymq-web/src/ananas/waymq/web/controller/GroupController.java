package ananas.waymq.web.controller;

import ananas.sf4lib.server.RequestContext;
import ananas.sf4lib.server.jrp.JRPController;
import ananas.sf4lib.server.store.StoreTransaction;
import ananas.waymq.web.model.Group;
import ananas.waymq.web.model.GroupName;

public class GroupController implements JRPController {

	interface Key {

		String req_name = "name";

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

		} else if (do_.equals("todo")) {
			this.todo(context);

		} else {
			unsupported = true;
		}
		if (unsupported) {
			context.setError("unsupported method : " + do_);
		}
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

	private void todo(RequestContext context) {
		// TODO Auto-generated method stub

	}

}
