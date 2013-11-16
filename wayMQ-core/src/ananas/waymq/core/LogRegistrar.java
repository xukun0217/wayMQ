package ananas.waymq.core;

public interface LogRegistrar {

	void register(String name, LogFactory lf);

	LogFactory getFactory(String name);

}
