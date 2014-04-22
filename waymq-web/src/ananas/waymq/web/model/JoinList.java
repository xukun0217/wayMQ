package ananas.waymq.web.model;

import java.util.HashMap;
import java.util.Map;

import ananas.xgit4.HashID;

public class JoinList {

	public static class Item {

		public String nickname;
		public int count;
		public boolean join;
		public String phone; // last 6 digits only

		public long last_modified;
		public long first_modified;

	}

	// id
	public HashID id;

	// static
	public HashID s_owner_event;

	// mutable
	public final Map<String, Item> m_map = new HashMap<String, Item>();

	// access

	public final Access access = new Access();

	public class Access {
	}

}
