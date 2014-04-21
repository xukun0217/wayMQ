package ananas.waymq.droid.offline;

public interface ISign {

	interface Key {

		String phone_id = "column_phone_id";
		String nickname = "column_nickname";
		String weight = "column_weight";
		String amount = "column_amount";
		String timestamp = "timestamp";

	}

	String id();

	String name();

	int count();

	int money();

	long timestamp();
}
