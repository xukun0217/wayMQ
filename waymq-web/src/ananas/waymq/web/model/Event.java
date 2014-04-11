package ananas.waymq.web.model;

import ananas.xgit4.HashID;

public class Event {

	// id

	public HashID id;

	// static

	public HashID prev_event;
	public HashID owner_group;

	// mutable

	public String title;
	public String content;
	public long time_create;
	public long time_open;
	public long time_close;

}
