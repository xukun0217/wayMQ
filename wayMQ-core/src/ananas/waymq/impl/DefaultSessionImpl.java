package ananas.waymq.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import ananas.objectbox.IObject;
import ananas.waymq.api.IMember;
import ananas.waymq.api.IMemberPhone;
import ananas.waymq.core.IRepo;
import ananas.waymq.core.ISession;
import ananas.waymq.core.ISessionElement;
import ananas.xgit.repo.ObjectId;

public class DefaultSessionImpl implements ISession {

	private final IRepo _repo;
	private final Map<String, IMemberPhone> _phone_map;
	private final Map<ObjectId, ISessionElement> _obj_map;

	private IMember _root;
	private Map<String, ISessionElementFactory> _type_map;

	public DefaultSessionImpl(IRepo repo) {
		this._repo = repo;

		this._obj_map = new Hashtable<ObjectId, ISessionElement>();
		this._phone_map = new Hashtable<String, IMemberPhone>();
	}

	@Override
	public IMember getRoot() {
		IMember root = this._root;
		if (root == null) {
			IMemberPhone phone = this.newPhone("0");
			root = phone.getMember();
			this._root = root;
		}
		return root;
	}

	@Override
	public IMemberPhone newPhone(String number) {
		char[] chs = number.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (char ch : chs) {
			if ('0' <= ch && ch <= '9') {
				sb.append(ch);
			}
		}
		number = sb.toString();
		// get
		IMemberPhone phone = this._phone_map.get(number);
		if (phone != null) {
			return phone;
		}
		// new phone
		Map<String, String> headers = new HashMap<String, String>();
		headers.put(IMemberPhone.Key.phone_num, number);
		phone = (IMemberPhone) this.newElement(type.phone, headers);

		// new member
		phone.load();
		IMember member = phone.getMember();
		if (member == null) {
			member = this.newMember(phone);
			phone.setMember(member);
		}

		return phone;
	}

	private IMember newMember(IMemberPhone phone) {
		Map<String, String> attr = new HashMap<String, String>();
		attr.put(IObject.HeadKey.create_time, System.currentTimeMillis() + "");
		attr.put(IMember.Key.phone_id, phone.getObject().getId() + "");
		IMember member = (IMember) this.newElement(type.member, attr);
		return member;
	}

	@Override
	public ISessionElement getElement(ObjectId id) {
		ISessionElement element = this._obj_map.get(id);
		if (element != null)
			return element;
		IObject obj = this._repo.getBox().getObject(id);
		if (!obj.getHeadFile().exists()) {
			return null;
		}
		String type = obj.getType();
		Map<String, ISessionElementFactory> types = this.getTypes();
		ISessionElementFactory ef = types.get(type);
		if (ef == null) {
			System.err.println(this + ".no type named : " + type);
		}
		element = ef.create(this, obj);
		this._obj_map.put(id, element);
		element.load();
		return element;
	}

	private Map<String, ISessionElementFactory> getTypes() {

		Map<String, ISessionElementFactory> map = this._type_map;
		if (map != null)
			return map;
		map = new Hashtable<String, ISessionElementFactory>();

		TypeReg.__reg_all_types(map);

		this._type_map = map;
		return map;
	}

	@Override
	public ISessionElement newElement(String type, Map<String, String> attr) {
		IObject obj = this._repo.getBox().newObject(type, attr);
		ObjectId id = obj.getId();
		return this.getElement(id);
	}

	@Override
	public IMember getMember(ObjectId id) {
		return (IMember) this.getElement(id);
	}

	@Override
	public void close() {
		Collection<ISessionElement> all = this._obj_map.values();
		for (ISessionElement element : all) {
			element.save();
		}
	}
}
