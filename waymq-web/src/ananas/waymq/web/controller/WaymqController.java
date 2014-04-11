package ananas.waymq.web.controller;

import ananas.sf4lib.core.JRP;
import ananas.sf4lib.server.RequestContext;
import ananas.sf4lib.server.auth.model.Token;
import ananas.sf4lib.server.jrp.JRPController;
import ananas.sf4lib.server.store.StoreTransaction;
import ananas.waymq.web.model.Event;
import ananas.waymq.web.model.Group;

import com.alibaba.fastjson.JSONObject;

public class WaymqController implements JRPController {

	@Override
	public void response(RequestContext context) {
		boolean unsupported = false;
		String do_ = context.getRequestMethod();
		if (do_ == null) {
			do_ = "null";
			unsupported = true;

		} else if (do_.equals("newEvent")) {
			this.newEvent(context);

		} else if (do_.equals("newGroup")) {
			this.newGroup(context);

		} else {
			unsupported = true;
		}
		if (unsupported) {
			context.setError("unsupported method : " + do_);
		}
	}

	private void newGroup(RequestContext context) {

		StoreTransaction tran = context.openStoreTransaction();

		Group group = new Group();
		group.name = context.getParameter("name");
		group.title = context.getParameter("title", "notitle");
		group.description = context.getParameter("description", "null");
		tran.insert(group);
		group = (Group) tran.get(Group.class, group.id);
		if (group.password_sha1 != null) {
			context.setError("the group has exists: " + group.name);
			return;
		}

		tran.commit();
		JSONObject res = context.getResultJSON();
		res.put("group", group.id.toString());

	}

	private void newEvent(RequestContext context) {

		StoreTransaction tran = context.openStoreTransaction();
		final long now = System.currentTimeMillis();

		// token
		Token token = context.getToken();
		// token.

		String group_id = context.getParameter("group");

		// group
		Group group = (Group) tran.get(Group.class, group_id);

		// event
		Event event = new Event();
		event.owner_group = group.id;
		event.prev_event = group.event_current;
		event.title = context.getParameter("title", "notitle");
		event.content = context.getParameter("content", "null");
		event.time_create = now;
		event.time_open = Long.parseLong(context.getParameter("time_open"));
		tran.insert(event);

		// commit
		tran.commit();

		// response
		JSONObject res = context.getResultJSON();
		res.put("event", event.id.toString());

	}

}
