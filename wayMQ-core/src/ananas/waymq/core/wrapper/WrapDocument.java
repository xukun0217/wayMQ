package ananas.waymq.core.wrapper;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import ananas.lib.io.vfs.VFile;
import ananas.objectbox.IObject;
import ananas.objectbox.ITypeRegistrar;
import ananas.waymq.api.IDocument;
import ananas.waymq.api.IElement;
import ananas.waymq.api.IEvent;
import ananas.waymq.api.IGroup;
import ananas.waymq.api.IHoldEvent;
import ananas.waymq.api.IJoinEvent;
import ananas.waymq.api.IJoinGroup;
import ananas.waymq.api.IMember;
import ananas.waymq.api.IPhone;
import ananas.waymq.api.IUser;
import ananas.waymq.core.IDocumentCore;
import ananas.xgit.repo.ObjectId;

public class WrapDocument implements IDocument {

	private final IDocumentCore _core;

	public WrapDocument(IDocumentCore core) {
		this._core = core;
	}

	@Override
	public VFile getFile() {
		return this._core.getBox().getBaseDirectory();
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void check() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean exists() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void cleanCache() {
		// TODO Auto-generated method stub

	}

	@Override
	public ITypeRegistrar getTypes() {
		return this._core.getBox().getTypeRegistrar();
	}

	@Override
	public IUser getRoot() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectId genId(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectId parseId(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IMember getMember(String phone) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPhone getPhone(String phone) {
		IPhone ip = this.__get_phone_by_number(phone);
		return ip;
	}

	private IPhone __get_phone_by_number(String num) {
		Map<String, String> h = new HashMap<String, String>();
		// TODO : h.put( IPhone , num ) ;
		IObject obj = this._core.getBox().newObject(WrapPhone.class, h);
		ObjectId id = obj.getId();
		return this.getPhone(id);
	}

	@Override
	public Enumeration<IElement> objects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IGroup getGroup(ObjectId id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IEvent getEvent(ObjectId id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IMember getMember(ObjectId id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IJoinGroup getJoinGroup(ObjectId id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IJoinEvent getJoinEvent(ObjectId id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IHoldEvent getHoldEvent(ObjectId id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPhone newPhone(String phone) {
		return this.__get_phone_by_number(phone);
	}

	@Override
	public IMember newMember(String name, IPhone phone) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IEvent newEvent(String name, IGroup creatorGroup, IMember creator) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IGroup newGroup(String name, IMember creator) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IJoinGroup newJoinGroup(IMember member, IGroup group) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IJoinEvent newJoinEvent(IMember member, IEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IHoldEvent newHoldEvent(IGroup group, IEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPhone getPhone(ObjectId id) {
		// TODO Auto-generated method stub
		return null;
	}

}
