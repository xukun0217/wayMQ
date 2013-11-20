package ananas.waymq.api;

import ananas.waymq.core.ISessionElement;

public interface IJoinGroup extends ISessionElement {

	IGroup getGroup();

	IMember getMember();

	long getTime();
}
