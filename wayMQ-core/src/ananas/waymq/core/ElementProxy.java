package ananas.waymq.core;

import ananas.xgit.repo.ObjectId;

public class ElementProxy<T> {

	private final ISession _session;
	private final ObjectId _id;
	private T _ptr;

	public ElementProxy(ISession session, ObjectId id) {
		this._session = session;
		this._id = id;
	}

	public ElementProxy(ISession session, String id) {
		this._session = session;
		if (id == null) {
			id = "00000000";
		}
		if (id.length() != 40) {
			// .. 0123456789012345678901234567890123456789
			id = "0000000000000000000000000000000000000000";
		}
		this._id = ObjectId.Factory.create(id);
	}

	@SuppressWarnings("unchecked")
	public T get() {
		T p = this._ptr;
		if (p == null) {
			p = (T) this._session.getElement(_id);
			this._ptr = p;
		}
		return p;
	}

	public String getIdString() {
		return this._id.toString();
	}

}
