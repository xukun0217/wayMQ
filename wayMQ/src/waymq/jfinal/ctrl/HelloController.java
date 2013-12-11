package waymq.jfinal.ctrl;

import com.jfinal.core.Controller;

public class HelloController extends Controller {

	public void index() {
		this.render("/home.html");
	}
}
