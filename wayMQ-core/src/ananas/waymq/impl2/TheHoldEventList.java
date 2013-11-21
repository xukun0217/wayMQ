package ananas.waymq.impl2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import ananas.waymq.api.IDocument;
import ananas.waymq.api.IHoldEvent;
import ananas.waymq.api.IHoldEventList;
import ananas.xgit.repo.ObjectId;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class TheHoldEventList extends TheElement implements IHoldEventList {

	private final Set<ObjectId> _list;

	public TheHoldEventList(IDocument doc, ObjectId id) {
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
	public IHoldEvent[] toArray() {
		List<IHoldEvent> list = new ArrayList<IHoldEvent>();
		IDocument doc = this.getDocument();
		Iterator<ObjectId> iter = this._list.iterator();
		for (; iter.hasNext();) {
			ObjectId id = iter.next();
			IHoldEvent item = doc.getHoldEvent(id);
			if (item != null)
				list.add(item);
		}
		return list.toArray(new IHoldEvent[list.size()]);
	}

	@Override
	public void add(IHoldEvent hold) {
		if (hold != null)
			this._list.add(hold.getId());
	}

}
