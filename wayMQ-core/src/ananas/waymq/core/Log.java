package ananas.waymq.core;

import java.util.Map;

public interface Log {

	LogId getId();

	String getCommandName();

	Map<String, String> getProperties();

	void execute(IMutableSnapshot snap);

}
