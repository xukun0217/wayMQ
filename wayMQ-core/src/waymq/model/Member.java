package waymq.model;

import java.util.HashMap;
import java.util.Map;

import ananas.objectbox.IBox;
import ananas.waymq.api.IGroup;
import ananas.waymq.api.IJoinEvent;
import ananas.waymq.api.IJoinEventList;
import ananas.waymq.api.IJoinGroup;
import ananas.waymq.api.IJoinGroupList;
import ananas.waymq.api.IMember;
import ananas.waymq.api.IMemberPhone;
import ananas.waymq.core.WayMQBody;
import ananas.xgit.repo.ObjectId;

import com.alibaba.fastjson.JSONObject;

public class Member extends WayMQBody implements IMember {

	private ObjectId _phone;
	private ObjectId _list_groups;
	private ObjectId _list_events;

	@Override
	public IJoinGroup[] listGroups() {
		IJoinGroupList list = (IJoinGroupList) this.get(this._list_groups);
		return list.toArray();
	}

	@Override
	public IJoinEvent[] listEvents() {
		IJoinEventList list = (IJoinEventList) this.get(this._list_events);
		return list.toArray();
	}

	@Override
	public IGroup createGroup(String name) {

		IBox box = this.getHead().getOwnerBox();

		Map<String, String> attr = new HashMap<String, String>();
		IGroup group = Group.create(box, this);

		IJoinGroupList list = (IJoinGroupList) this.get(this._list_groups);
		list.add(group, this);

		return group;
	}

	@Override
	public void onLoad(JSONObject root) {
		// TODO Auto-generated method stub

	}

	@Override
	public JSONObject onSave(JSONObject root) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IMemberPhone getPhone() {
		return (IMemberPhone) this.get(_phone);
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub

	}

}
