package ananas.waymq.core;

import java.util.Map;

import ananas.waymq.api.IMember;
import ananas.waymq.api.IMemberPhone;
import ananas.waymq.impl.DefaultSessionImpl;
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

}
