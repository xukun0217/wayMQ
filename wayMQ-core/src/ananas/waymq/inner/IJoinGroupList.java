package ananas.waymq.inner;

import ananas.waymq.core.ISessionElement;

public interface IJoinGroupList extends ISessionElement {

	IJoinGroup[] toArray();

	void add(IGroup group, IMember member);

}
