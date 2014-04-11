package ananas.waymq.web.model;

import ananas.xgit4.HashID;

public class Group {

	// id

	public HashID id;

	// static

	public String name;

	// mutable

	public String title;
	public String description;

	public String password_plain;
	public String password_sha1;
	public HashID event_current;

}
