package ananas.waymq.droid.gap;

public interface GapConst {

	interface Default {

		String home_page = "http://puyatech.com/waymq";
		String endpoint = "http://puyatech.com/waymq";
		String config_json = "http://puyatech.com/waymq/config.json";
		String news_url = "http://puyatech.com/waymq/news.json";

	}

	interface Key {

		String home_page = "home_page";
		String endpoint = "endpoint";
		String config_json = "config_json";

		String news_url = "news_url";
		String news_load_time = "news_load_time";
		String news_json = "news_json";

		String sms_from = "sms_from";
		String sms_to = "sms_to";
		String sms_text = "sms_text";

	}

}
