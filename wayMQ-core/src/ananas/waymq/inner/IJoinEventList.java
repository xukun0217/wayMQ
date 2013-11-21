package ananas.waymq.inner;

import ananas.waymq.core.ISessionElement;

public interface IJoinEventList extends ISessionElement {

	IJoinEvent[] toArray();

}
