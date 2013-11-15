package ananas.waymq.droid.core;

public interface ICoreApi {

	IMember[] findMembers(String keyword);

	IBaseDirectory getBaseDirectory();

}
