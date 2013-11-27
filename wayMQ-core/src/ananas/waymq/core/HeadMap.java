package ananas.waymq.core;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ananas.objectbox.IObject;

public class HeadMap implements Map<String, String> {

	private final IObject _obj;
	private final String[] _names;

	public HeadMap(IObject obj) {
		this._obj = obj;
		this._names = obj.getHeaderNames();
	}

	@Override
	public int size() {
		return this._names.length;
	}

	@Override
	public boolean isEmpty() {
		return (this._names.length == 0);
	}

	@Override
	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String get(Object key) {
		return _obj.getHeader(key.toString());
	}

	@Override
	public String put(String key, String value) {
		return null;
	}

	@Override
	public String remove(Object key) {
		return null;
	}

	@Override
	public void putAll(Map<? extends String, ? extends String> m) {

	}

	@Override
	public void clear() {

	}

	@Override
	public Set<String> keySet() {
		Set<String> s = new HashSet<String>();
		for (String n : this._names)
			s.add(n);
		return s;
	}

	@Override
	public Collection<String> values() {
		Set<String> s = new HashSet<String>();
		for (String k : this._names) {
			String v = _obj.getHeader(k);
			s.add(v);
		}
		return s;
	}

	@Override
	public Set<java.util.Map.Entry<String, String>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

}
