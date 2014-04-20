package ananas.waymq.web.model;

import ananas.xgit4.HashID;

public class Event {

	// id

	public HashID id;

	// static

	public HashID s_prev_event;
	public HashID s_owner_group;

	// mutable

	public HashID m_join_list;
	public String m_title;
	public String m_content;
	public long m_time_create;
	public long m_time_open;
	public long m_time_close;

	// access

	public final Access access = new Access();

	public class Access {
	}

}
