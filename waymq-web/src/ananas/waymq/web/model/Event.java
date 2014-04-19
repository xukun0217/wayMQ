package ananas.waymq.web.model;

import ananas.xgit4.HashID;

public class Event {

	// id

	public HashID id;

	// static

	public HashID s_prev_event;
	public HashID s_owner_group;

	// mutable

	public String m_title;
	public String m_content;
	public long m_time_create;
	public long m_time_open;
	public long m_time_close;

}
