package ananas.waymq.impl2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import ananas.waymq.api.IDocument;
import ananas.waymq.api.IJoinGroup;
import ananas.waymq.api.IJoinGroupList;
import ananas.xgit.repo.ObjectId;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class TheJoinGroupList extends TheElement implements IJoinGroupList {

	private final Set<ObjectId> _list;

	public TheJoinGroupList(IDocument doc, ObjectId id) {
		super(doc, id);
		this._list = new HashSet<ObjectId>();
	}

	@Override
	public void onSave(JSONObject json) {
		JSONArray list = new JSONArray();
		Iterator<ObjectId> iter = _list.iterator();
		for (; iter.hasNext();) {
			ObjectId item = iter.next();
			list.add("" + item);
		}
		json.put("list", list);
		super.onSave(json);
	}

	@Override
	public IJoinGroup[] toArray() {
		List<IJoinGroup> list = new ArrayList<IJoinGroup>();
		IDocument doc = this.getDocument();
		Iterator<ObjectId> iter = this._list.iterator();
		for (; iter.hasNext();) {
			ObjectId id = iter.next();
			IJoinGroup item = doc.getJoinGroup(id);
			if (item != null)
				list.add(item);
		}
		return list.toArray(new IJoinGroup[list.size()]);
	}

	@Override
	public void add(IJoinGroup join) {
		if (join != null)
			this._list.add(join.getId());
	}

}
