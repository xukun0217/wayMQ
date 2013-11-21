package ananas.waymq.model;

import com.alibaba.fastjson.JSONObject;

import ananas.objectbox.IObject;
import ananas.waymq.core.ISession;
import ananas.waymq.inner.IJoinEvent;
import ananas.waymq.inner.IJoinEventList;

public class JoinEventList extends WayMQBody implements IJoinEventList {

	 

	public JoinEventList(ISession session, IObject obj) {
		super(session, obj);
		// TODO Auto-generated constructor stub
	}

	@Override
	public IJoinEvent[] toArray() {
		// TODO Auto-generated method stub
		return null;
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
