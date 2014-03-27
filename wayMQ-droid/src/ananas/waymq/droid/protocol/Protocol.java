package ananas.waymq.droid.protocol;

public interface Protocol {

	interface ParamKey {

		String this_ = "this";
		String class_ = "class";
		String method_ = "method";

	}

	interface Event {

		String class_name = "Event";

		String do_get_info = "getInfo";
		String do_join = "join";
		String do_cancel = "cancel";

	}

}
