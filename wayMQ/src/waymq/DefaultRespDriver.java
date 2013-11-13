package waymq;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DefaultRespDriver implements IJsonResponderDriver {

	private final IJsonResponder _resper;

	public DefaultRespDriver(IJsonResponder resper) {
		this._resper = resper;
	}

	@Override
	public void proc(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setNeedAdmin(boolean need) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setNeedLogin(boolean need) {
		// TODO Auto-generated method stub

	}

}
