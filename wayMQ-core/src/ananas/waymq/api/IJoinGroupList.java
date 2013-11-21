package ananas.waymq.api;

public interface IJoinGroupList extends IElement {

	IJoinGroup[] toArray();

	void add(IJoinGroup join);

}
