package ananas.waymq.test;

import java.util.Map;

public class Util4Test {

	public static void test(String http_method, Map<String, String> param) {
		HttpJsonRequestTester tester = new HttpJsonRequestTester();
		tester.test(http_method, param);
	}

}
