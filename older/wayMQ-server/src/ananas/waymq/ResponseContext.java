package ananas.waymq;

public interface ResponseContext {

	String cannot_be_null = "default_cannot_be_null__6893695628241233230L";

	String getParameter(String key);

	String getParameter(String key, String defaultValue);

	WayMQ getApp();

}
