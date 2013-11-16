package ananas.waymq.core;

public class PhoneNum {

	private final String _num;

	public PhoneNum(String num) {
		this._num = __normal(num);
	}

	private String __normal(String s) {
		char[] chs = s.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (char ch : chs) {
			if (('0' <= ch) && (ch <= '9')) {
				sb.append(ch);
			}
		}
		return sb.toString();
	}

	@Override
	public String toString() {
		return this._num;
	}
}
