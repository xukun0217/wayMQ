package waymq;

import waymq.util.StringUtil;

public class DefaultTempPassword implements ITempPassword {

	private final long _timeout;
	private final String _plain_text;
	private final String _phone;

	private DefaultTempPassword(long now, String phone) {

		String s = this + "&" + phone + "&" + now;
		s = StringUtil.hashString(s);
		int n = s.hashCode() & 0xfffff;

		this._phone = phone;
		this._plain_text = "" + n;
		this._timeout = now + (300 * 1000);
	}

	public static ITempPassword gen(String phone) {
		long now = System.currentTimeMillis();
		return new DefaultTempPassword(now, phone);
	}

	@Override
	public String getPlainText() {
		return this._plain_text;
	}

	@Override
	public long getTimeout() {
		return this._timeout;
	}

	@Override
	public boolean isTimeout() {
		long now = System.currentTimeMillis();
		return (this._timeout < now);
	}

	@Override
	public boolean isUseable(String phone) {
		return (this._phone.equals(phone) && (!this.isTimeout()));
	}

	@Override
	public String getPhoneNum() {
		return this._phone;
	}

}
