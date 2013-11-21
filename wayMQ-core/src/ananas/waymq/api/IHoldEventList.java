package ananas.waymq.api;

public interface IHoldEventList extends IElement {

	IHoldEvent[] toArray();

	void add(IHoldEvent hold);

}
