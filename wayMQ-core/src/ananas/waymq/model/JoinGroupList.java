package ananas.waymq.model;

import com.alibaba.fastjson.JSONObject;

import ananas.objectbox.IObject;
import ananas.waymq.api.IGroup;
import ananas.waymq.api.IJoinGroup;
import ananas.waymq.api.IJoinGroupList;
import ananas.waymq.api.IMember;
import ananas.waymq.core.ISession;

public class JoinGroupList extends WayMQBody implements IJoinGroupList {

	public JoinGroupList(ISession session, IObject obj) {
		super(session, obj);
		// TODO Auto-generated constructor stub
	}

	@Override
	public IJoinGroup[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(IGroup group, IMember member) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLoad(JSONObject json) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSave(JSONObject json) {
		// TODO Auto-generated method stub

	}

}
