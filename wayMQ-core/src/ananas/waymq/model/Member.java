package ananas.waymq.model;

import ananas.objectbox.IObject;
import ananas.waymq.api.IGroup;
import ananas.waymq.api.IJoinEvent;
import ananas.waymq.api.IJoinEventList;
import ananas.waymq.api.IJoinGroup;
import ananas.waymq.api.IJoinGroupList;
import ananas.waymq.api.IMember;
import ananas.waymq.api.IMemberPhone;
import ananas.waymq.core.ElementProxy;
import ananas.waymq.core.ISession;

import com.alibaba.fastjson.JSONObject;

public class Member extends WayMQBody implements IMember {

	private final ElementProxy<IMemberPhone> _phone;

	private ElementProxy<IJoinGroupList> _list_groups;
	private ElementProxy<IJoinEventList> _list_events;

	public Member(ISession session, IObject obj) {
		super(session, obj);

		this._phone = new ElementProxy<IMemberPhone>(session,
				obj.getHeader(Key.phone_id));

	}

	@Override
	public IJoinGroup[] listGroups() {
		IJoinGroupList list = this.__get_group_list();
		if (list == null) {
			return new IJoinGroup[0];
		} else {
			return list.toArray();
		}
	}

	@Override
	public IJoinEvent[] listEvents() {
		IJoinEventList list = this.__get_event_list();
		if (list == null) {
			return new IJoinEvent[0];
		} else {
			return list.toArray();
		}
	}

	@Override
	public IGroup createGroup(String name) {

		ISession session = this.getSession();
		IGroup group = session.newGroup(name, this);

		IJoinGroupList list = this.__get_group_list();
		list.add(group, this);

		return group;
	}

	private IJoinGroupList __get_group_list() {
		ElementProxy<IJoinGroupList> pro = this._list_groups;
		if (pro == null) {
			ISession session = this.getSession();
			IJoinGroupList list = session.newJoinGroupList(null, this);
			pro = new ElementProxy<IJoinGroupList>(session, list.getId());
			this._list_groups = pro;
			this.setModified(true);
		}
		return pro.get();
	}

	private IJoinEventList __get_event_list() {
		ElementProxy<IJoinEventList> pro = this._list_events;
		if (pro == null) {
			ISession session = this.getSession();
			IJoinEventList list = session.newJoinEventList(null, this);
			pro = new ElementProxy<IJoinEventList>(session, list.getId());
			this._list_events = pro;
			this.setModified(true);
		}
		return pro.get();
	}

	@Override
	public IMemberPhone getPhone() {
		return this._phone.get();
	}

	@Override
	public void onLoad(JSONObject json) {

		ISession session = this.getSession();

		String events = json.getString(Key.join_event_list_id);
		this._list_events = new ElementProxy<IJoinEventList>(session, events);

		String groups = json.getString(Key.join_group_list_id);
		this._list_groups = new ElementProxy<IJoinGroupList>(session, groups);

	}

	@Override
	public void onSave(JSONObject json) {

		json.put(Key.join_event_list_id, this._list_events.getIdString());
		json.put(Key.join_group_list_id, this._list_groups.getIdString());

	}

}
