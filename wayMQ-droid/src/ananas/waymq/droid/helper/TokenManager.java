package ananas.waymq.droid.helper;

public class TokenManager {

	static String _token;

	public static String getToken() {
		return _token;
	}

	public static void saveToken(String token) {
		_token = token;
	}

}
