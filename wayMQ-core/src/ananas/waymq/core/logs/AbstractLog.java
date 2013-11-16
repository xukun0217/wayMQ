package ananas.waymq.core.logs;

import java.util.Map;

import ananas.waymq.core.Log;
import ananas.waymq.core.LogId;

public abstract class AbstractLog implements Log {

	private LogId id;
	private String command;
	private Map<String, String> properties;

	@Override
	public LogId getId() {
		return this.id;
	}

	@Override
	public String getCommandName() {
		return this.command;
	}

	@Override
	public Map<String, String> getProperties() {
		return this.properties;
	}

}
