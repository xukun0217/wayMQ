package ananas.waymq.droid.api;

public interface ICoreApi {

	IBaseDirectory getBaseDirectory();

	IMemberManager getMemberManager();

	void save();

	void load();

}
