package ananas.waymq.core;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import ananas.objectbox.IObjectBody;
import ananas.objectbox.body.json.JsonBody;
import ananas.xgit.repo.ObjectId;

public abstract class WayMQBody extends JsonBody {

	protected IObjectBody get(ObjectId id) {
		return this.getHead().getOwnerBox().getObject(id);
	}

	protected IObjectBody create(Class<?> cls, Map<String, String> head) {
		return this.getHead().getOwnerBox().newObject(cls, head);
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
	public void onCreate() {
		// TODO Auto-generated method stub
		
	}

}
