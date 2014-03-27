package ananas.waymq;

public interface Protocol {

	interface Attr {
		String class_ = "class";
		String this_ = "this";
		String method_ = "method";
		String token_ = "token";
	}

	interface Resp {

		String set_token_ = "set_token";
		String exception = "exception";
		String error = "error";
		String success = "success";

	}

	interface Account {
		String class_name = "Account";
		String do_get_info = "getInfo";
		String do_register = "register";
		String do_login = "login";
		String do_logout = "logout";
	}

	interface Event {
		String class_name = "Event";
	}

	interface Group {
		String class_name = "Group";
	}

}
