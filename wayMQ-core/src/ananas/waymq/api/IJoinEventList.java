package ananas.waymq.api;

import ananas.waymq.core.ISessionElement;

public interface IJoinEventList extends ISessionElement {

	IJoinEvent[] toArray();

}
