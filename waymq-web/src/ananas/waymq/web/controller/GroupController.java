package ananas.waymq.web.controller;

import ananas.sf4lib.server.RequestContext;
import ananas.sf4lib.server.jrp.JRPController;

public class GroupController implements JRPController {

	@Override
	public void response(RequestContext context) {
		boolean unsupported = false;
		String do_ = context.getRequestMethod();
		if (do_ == null) {
			do_ = "null";
			unsupported = true;
		} else if (do_.equals("todo")) {
			this.todo(context);
		} else {
			unsupported = true;
		}
		if (unsupported) {
			context.setError("unsupported method : " + do_);
		}
	}

	private void todo(RequestContext context) {
		// TODO Auto-generated method stub

	}

}
