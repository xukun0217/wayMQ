package ananas.waymq.test;

// import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import ananas.waymq.Protocol;

public class TestAccount {

	@Test
	public void test() {
		// fail("Not yet implemented");

		Map<String, String> param = new HashMap<String, String>();

		{
			// register
			param.clear();
			param.put(Protocol.Attr.class_, Protocol.Account.class_name);
			param.put(Protocol.Attr.method_, Protocol.Account.do_register);

			Util4Test.test("POST", param);
		}
		{
			// getInfo
			param.clear();
			param.put(Protocol.Attr.class_, Protocol.Account.class_name);
			param.put(Protocol.Attr.method_, Protocol.Account.do_get_info);

			Util4Test.test("GET", param);
		}

		{
			// login
			param.clear();
			param.put(Protocol.Attr.class_, Protocol.Account.class_name);
			param.put(Protocol.Attr.method_, Protocol.Account.do_login);

			Util4Test.test("POST", param);
		}
		{
			// getInfo
			param.clear();
			param.put(Protocol.Attr.class_, Protocol.Account.class_name);
			param.put(Protocol.Attr.method_, Protocol.Account.do_get_info);

			Util4Test.test("GET", param);
		}
		{
			// logout
			param.clear();
			param.put(Protocol.Attr.class_, Protocol.Account.class_name);
			param.put(Protocol.Attr.method_, Protocol.Account.do_logout);

			Util4Test.test("POST", param);
		}

	}

}
