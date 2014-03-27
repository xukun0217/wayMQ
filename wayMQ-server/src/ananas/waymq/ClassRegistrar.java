package ananas.waymq;

public interface ClassRegistrar {

	JsonResponderClass getClass(String name);

	void registerClass(String name, JsonResponderClass class_);

}
