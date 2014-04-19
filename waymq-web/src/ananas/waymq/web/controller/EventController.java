package ananas.waymq.web.controller;

import ananas.sf4lib.server.RequestContext;
import ananas.sf4lib.server.jrp.JRPController;
import ananas.sf4lib.server.store.StoreTransaction;
import ananas.waymq.web.model.Event;

import com.alibaba.fastjson.JSONObject;

public class EventController implements JRPController {

	interface Key {

		String res_title = "title";
		String res_detail = "detail";
		String res_time_open = "time_open";
		String res_time_open_text = "time_open_text";
		String res_time_close = "time_close";
		String res_time_create = "time_create";

	}

	@Override
	public void response(RequestContext context) {
		boolean unsupported = false;
		String do_ = context.getRequestMethod();
		if (do_ == null) {
			do_ = "null";
			unsupported = true;

		} else if (do_.equals("getEventInfo")) {
			this.getEventInfo(context);

		} else if (do_.equals("todo")) {
			this.todo(context);

		} else {
			unsupported = true;
		}
		if (unsupported) {
			context.setError("unsupported method : " + do_);
		}
	}

	private void getEventInfo(RequestContext context) {

		StoreTransaction tran = context.openStoreTransaction();
		String id = context.getRequestThis();
		Event event = (Event) tran.get(Event.class, id);
		if (event == null) {
			context.setError("cannot find event with id: " + id);
			return;
		}

		JSONObject json = context.getResultJSON();
		json.put(Key.res_title, event.m_title);
		json.put(Key.res_detail, event.m_content);

	}

	private void todo(RequestContext context) {
		// TODO Auto-generated method stub

	}

}
