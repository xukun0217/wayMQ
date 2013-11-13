package waymq;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IJsonResponderDriver {

	void proc(HttpServletRequest request, HttpServletResponse response) throws IOException;

	void setNeedAdmin(boolean need);

	void setNeedLogin(boolean need);

}
