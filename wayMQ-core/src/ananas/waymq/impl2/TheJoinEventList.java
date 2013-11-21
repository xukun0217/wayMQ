package ananas.waymq.impl2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import ananas.waymq.api.IDocument;
import ananas.waymq.api.IJoinEvent;
import ananas.waymq.api.IJoinEventList;
import ananas.xgit.repo.ObjectId;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class TheJoinEventList extends TheElement implements IJoinEventList {

	private final Set<ObjectId> _list;

	public TheJoinEventList(IDocument doc, ObjectId id) {
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
	public IJoinEvent[] toArray() {
		List<IJoinEvent> list = new ArrayList<IJoinEvent>();
		IDocument doc = this.getDocument();
		Iterator<ObjectId> iter = this._list.iterator();
		for (; iter.hasNext();) {
			ObjectId id = iter.next();
			IJoinEvent item = doc.getJoinEvent(id);
			if (item != null)
				list.add(item);
		}
		return list.toArray(new IJoinEvent[list.size()]);
	}

	@Override
	public void add(IJoinEvent join) {
		if (join != null)
			this._list.add(join.getId());
	}

}
