package ananas.waymq.core;

import java.util.Map;

import ananas.waymq.impl.DefaultSessionImpl;
import ananas.waymq.inner.IEvent;
import ananas.waymq.inner.IGroup;
import ananas.waymq.inner.IHoldEventList;
import ananas.waymq.inner.IJoinEventList;
import ananas.waymq.inner.IJoinGroupList;
import ananas.waymq.inner.IMember;
import ananas.waymq.inner.IMemberPhone;
import ananas.xgit.repo.ObjectId;

public class DefaultSession implements ISession {

	private final ISession impl;

	public DefaultSession(IRepo repo) {
		this.impl = new DefaultSessionImpl(repo);
	}

	@Override
	public IMember getRoot() {
		return impl.getRoot();
	}

	@Override
	public IMemberPhone newPhone(String number) {
		return impl.newPhone(number);
	}

	@Override
	public ISessionElement getElement(ObjectId id) {
		return impl.getElement(id);
	}

	@Override
	public ISessionElement newElement(String type, Map<String, String> attr) {
		return impl.newElement(type, attr);
	}

	@Override
	public IMember getMember(ObjectId id) {
		return impl.getMember(id);
	}

	@Override
	public void close() {
		impl.close();
	}

	@Override
	public IMember newMember(String name, IMemberPhone phone) {
		return impl.newMember(name, phone);
	}

	@Override
	public IEvent newEvent(String title, IGroup creatorGroup, IMember creator) {
		return impl.newEvent(title, creatorGroup, creator);
	}

	@Override
	public IGroup newGroup(String name, IMember member) {
		return impl.newGroup(name, member);
	}

	@Override
	public IJoinEventList newJoinEventList(IEvent event, IMember member) {
		return impl.newJoinEventList(event, member);
	}

	@Override
	public IJoinGroupList newJoinGroupList(IGroup group, IMember member) {
		return impl.newJoinGroupList(group, member);
	}

	@Override
	public IHoldEventList newHoldEventList(IEvent event, IGroup group) {
		return impl.newHoldEventList(event, group);
	}

}
