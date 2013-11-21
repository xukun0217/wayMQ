package ananas.waymq.api;

public interface IJoinEventList extends IElement {

	IJoinEvent[] toArray();

	void add(IJoinEvent join);
}
