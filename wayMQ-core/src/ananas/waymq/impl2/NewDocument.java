package ananas.waymq.impl2;

import java.security.MessageDigest;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import ananas.lib.io.vfs.VFile;
import ananas.waymq.api.IDocument;
import ananas.waymq.api.IElement;
import ananas.waymq.api.IElementCtrl;
import ananas.waymq.api.IEvent;
import ananas.waymq.api.IGroup;
import ananas.waymq.api.IHoldEvent;
import ananas.waymq.api.IHoldEventList;
import ananas.waymq.api.IJoinEvent;
import ananas.waymq.api.IJoinEventList;
import ananas.waymq.api.IJoinGroup;
import ananas.waymq.api.IJoinGroupList;
import ananas.waymq.api.IMember;
import ananas.waymq.api.IMemberPhone;
import ananas.xgit.repo.ObjectId;

import com.alibaba.fastjson.JSONObject;

public class NewDocument implements IDocument {

	private final Map<String, IMemberPhone> _map_phone_num;// key: phone_num
	private final Map<ObjectId, IElementCtrl> _map_obj_ctrl;
	private final VFile _main_file;

	private IMember _root;

	public NewDocument(VFile main) {
		this._main_file = main;

		this._map_obj_ctrl = new Hashtable<ObjectId, IElementCtrl>(64);
		this._map_phone_num = new Hashtable<String, IMemberPhone>(64);
	}

	@Override
	public void save() {

		Collection<IElementCtrl> list = this._map_obj_ctrl.values();
		for (IElementCtrl ctrl : list) {
			JSONObject json = new JSONObject();
			ctrl.onSave(json);
			String s = com.alibaba.fastjson.JSON.toJSONString(json);
			System.out.println("json = " + s);
		}

	}

	public void load() {
		// TODO Auto-generated method stub

	}

	@Override
	public IMember getRoot() {
		IMember root = this._root;
		if (root == null) {
			IMemberPhone phone = this.newMemberPhone("");
			root = this.newMember("root", phone);
			this._root = root;
		}
		return root;
	}

	@Override
	public IMemberPhone newMemberPhone(String pn) {
		pn = this.__normal_phone_string(pn);
		IMemberPhone phone = this._map_phone_num.get(pn);
		if (phone == null) {
			TheMemberPhone theNew = new TheMemberPhone(this, pn);
			phone = theNew;
			this.__put_new(theNew);
			this._map_phone_num.put(pn, phone);
		}
		return phone;
	}

	private void __put_new(IElementCtrl ctrl) {
		if (ctrl == null)
			return;
		ctrl.onCreate();
		this._map_obj_ctrl.put(ctrl.getElement().getId(), ctrl);
	}

	private String __normal_phone_string(String pn) {
		StringBuilder sb = new StringBuilder();
		if (pn != null) {
			char[] chs = pn.toCharArray();
			for (char ch : chs) {
				if ('0' <= ch && ch <= '9')
					sb.append(ch);
			}
		}
		return sb.toString();
	}

	@Override
	public IMember getMember(String phoneNum) {
		IMemberPhone phone = this.getMemberPhone(phoneNum);
		if (phone == null)
			return null;
		return phone.getMember();
	}

	@Override
	public IMemberPhone getMemberPhone(String phone) {
		phone = this.__normal_phone_string(phone);
		return this._map_phone_num.get(phone);
	}

	private final static char[] hex_char_array = "0123456789abcdef"
			.toCharArray();

	/**
	 * @param time
	 *            if time=0, use currentTime; else use time.
	 * */
	public ObjectId genId(Class<?> cls, long time, IEvent event, IGroup group,
			IMember member) {

		String cid = cls.getName();
		String eid = (event == null) ? "" : event.getId() + "";
		String mid = (member == null) ? "" : member.getId() + "";
		String gid = (group == null) ? "" : group.getId() + "";

		String s = cid + "@" + time + "@" + eid + "@" + gid + "@" + mid;
		return this.genId(s);
	}

	@Override
	public ObjectId genId(String s) {

		try {
			byte[] ba = s.getBytes("UTF-8");
			MessageDigest md = java.security.MessageDigest.getInstance("SHA-1");
			ba = md.digest(ba);
			StringBuilder sb = new StringBuilder();
			for (byte b : ba) {
				sb.append(hex_char_array[(b >> 4) & 0x0f]);
				sb.append(hex_char_array[(b) & 0x0f]);
			}
			return ObjectId.Factory.create(sb.toString());

		} catch (Exception e) {
			e.printStackTrace();
			// . 0123456789012345678901234567890123456789
			s = "00000000000000time00000000000000000000000000";
			return ObjectId.Factory.create(s);
		}

	}

	@Override
	public ObjectId parseId(String s) {
		StringBuilder sb = new StringBuilder();
		char[] chs = s.toCharArray();
		for (char ch : chs) {
			if ('0' <= ch && ch <= '9') {
				sb.append(ch);
			} else if ('a' <= ch && ch <= 'f') {
				sb.append(ch);
			} else if ('A' <= ch && ch <= 'F') {
				sb.append(ch - 'A' + 'a');
			} else {
			}
		}
		return ObjectId.Factory.create(s);
	}

	@Override
	public IJoinGroup newJoinGroup(IMember member, IGroup group) {

		ObjectId id = this.genId(IJoinGroup.class, -1, null, group, member);
		IJoinGroup join = this.getJoinGroup(id);
		if (join != null)
			return join;

		TheJoinGroup theNew;
		join = theNew = new TheJoinGroup(this, id);
		theNew.setGroup(group);
		theNew.setMember(member);
		return join;
	}

	@Override
	public IJoinEvent newJoinEvent(IMember member, IEvent event) {

		ObjectId id = this.genId(IJoinEvent.class, -1, event, null, member);
		IJoinEvent join = this.getJoinEvent(id);
		if (join != null) {
			return join;
		}

		TheJoinEvent theNew;
		join = theNew = new TheJoinEvent(this, id);
		theNew.setEvent(event);
		theNew.setMember(member);
		this.__put_new(theNew);
		return join;
	}

	@Override
	public IMember getMember(ObjectId id) {
		return (IMember) this.__get(id);
	}

	@Override
	public IJoinGroup getJoinGroup(ObjectId id) {
		return (IJoinGroup) this.__get(id);
	}

	private IElement __get(ObjectId id) {
		if (id == null)
			return null;
		IElementCtrl ctrl = this._map_obj_ctrl.get(id);
		if (ctrl == null)
			return null;
		return ctrl.getElement();
	}

	@Override
	public IJoinEvent getJoinEvent(ObjectId id) {
		return (IJoinEvent) this.__get(id);
	}

	@Override
	public IMemberPhone getMemberPhone(ObjectId id) {
		return (IMemberPhone) this.__get(id);
	}

	@Override
	public IGroup getGroup(ObjectId id) {
		return (IGroup) this.__get(id);
	}

	@Override
	public IEvent getEvent(ObjectId id) {
		return (IEvent) this.__get(id);
	}

	@Override
	public IHoldEvent getHoldEvent(ObjectId id) {
		return (IHoldEvent) this.__get(id);
	}

	@Override
	public IMember newMember(String name, IMemberPhone phone) {

		IMember member = phone.getMember();
		if (member != null)
			return member;

		ObjectId id = this.genId(phone.getId() + "@@"
				+ System.currentTimeMillis() + "@@" + name);

		TheMember theNew;
		member = theNew = new TheMember(this, id);
		this.__put_new(theNew);
		theNew.setPhone(phone);
		phone.setMember(member);
		return member;
	}

	@Override
	public IEvent newEvent(String name, IGroup creatorGroup, IMember creator) {
		ObjectId id = this.genId(IEvent.class, 0, null, creatorGroup, creator);
		TheEvent theNew = new TheEvent(this, id);
		IEvent event = theNew;
		theNew.setCreator(creatorGroup, creator);
		this.__put_new(theNew);
		return event;
	}

	@Override
	public IGroup newGroup(String name, IMember creator) {

		ObjectId id = this.genId(IGroup.class, -1, null, null, creator);
		IGroup group = this.getGroup(id);
		if (group != null) {
			return group;
		}

		TheGroup theNew;
		group = theNew = new TheGroup(this, id);
		theNew.setName(name);
		theNew.setCreator(creator);
		this.__put_new(theNew);
		return group;
	}

	@Override
	public IHoldEvent newHoldEvent(IGroup group, IEvent event) {

		ObjectId id = this.genId(IHoldEvent.class, -1, event, group, null);
		IHoldEvent hold = this.getHoldEvent(id);
		if (hold != null)
			return hold;

		TheHoldEvent theNew;
		hold = theNew = new TheHoldEvent(this, id);
		theNew.setTime(System.currentTimeMillis());
		theNew.setGroup(group);
		theNew.setEvent(event);
		return hold;
	}

	@Override
	public IJoinGroupList getJoinGroupList(ObjectId id) {
		return (IJoinGroupList) this.__get(id);
	}

	@Override
	public IJoinEventList getJoinEventList(ObjectId id) {
		return (IJoinEventList) this.__get(id);
	}

	@Override
	public IHoldEventList getHoldEventList(ObjectId id) {
		return (IHoldEventList) this.__get(id);
	}

	@Override
	public IJoinGroupList newJoinGroupList(IMember member, IGroup group) {

		ObjectId id = this.genId(IJoinGroupList.class, -1, null, group, member);
		IJoinGroupList list = this.getJoinGroupList(id);
		if (list != null) {
			return list;
		}
		TheJoinGroupList theNew = new TheJoinGroupList(this, id);
		this.__put_new(theNew);
		return theNew;
	}

	@Override
	public IJoinEventList newJoinEventList(IMember member, IEvent event) {
		ObjectId id = this.genId(IJoinEventList.class, -1, event, null, member);
		IJoinEventList list = this.getJoinEventList(id);
		if (list != null) {
			return list;
		}
		TheJoinEventList theNew = new TheJoinEventList(this, id);
		this.__put_new(theNew);
		return theNew;
	}

	@Override
	public IHoldEventList newHoldEventList(IGroup group, IEvent event) {
		ObjectId id = this.genId(IHoldEventList.class, -1, event, group, null);
		IHoldEventList list = this.getHoldEventList(id);
		if (list != null) {
			return list;
		}
		TheHoldEventList theNew = new TheHoldEventList(this, id);
		this.__put_new(theNew);
		return theNew;
	}

	@Override
	public Enumeration<IElement> objects() {

		Collection<IElementCtrl> objs = this._map_obj_ctrl.values();

		return new MyEnumElements(objs);
	}

	class MyEnumElements implements Enumeration<IElement> {

		private final Iterator<IElementCtrl> iter;

		public MyEnumElements(Collection<IElementCtrl> objs) {
			this.iter = objs.iterator();
		}

		@Override
		public boolean hasMoreElements() {
			return iter.hasNext();
		}

		@Override
		public IElement nextElement() {

			for (;;) {
				IElementCtrl ctrl = iter.next();
				if (ctrl != null) {
					IElement ele = ctrl.getElement();
					if (ele != null)
						return ele;
				}
				if (iter.hasNext())
					continue;
				else
					return null;
			}
		}
	}

}
