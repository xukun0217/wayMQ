package waymq;

import waymq.util.StringUtil;

public class ObjId {

	private final String _string;

	public ObjId(String s) {
		this._string = s;
	}

	@Override
	public String toString() {
		return this._string;
	}

	@Override
	public int hashCode() {
		return this._string.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ObjId other = (ObjId) obj;

		if (_string == null || other._string == null) {
			return false;
		} else {
			return other._string.equals(_string);
		}
	}

	protected static String __hashString(String s) {
		return StringUtil.hashString(s);
	}

}
