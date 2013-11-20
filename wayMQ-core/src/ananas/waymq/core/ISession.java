package ananas.waymq.core;

import java.util.Map;

import ananas.waymq.api.IEvent;
import ananas.waymq.api.IGroup;
import ananas.waymq.api.IHoldEventList;
import ananas.waymq.api.IJoinEventList;
import ananas.waymq.api.IJoinGroupList;
import ananas.waymq.api.IMember;
import ananas.waymq.api.IMemberPhone;
import ananas.xgit.repo.ObjectId;

public interface ISession {

	interface type {

		String phone = "Phone";
		String member = "Member";

	}

	void close();

	IMember getRoot();

	ISessionElement newElement(String type, Map<String, String> attr);

	ISessionElement getElement(ObjectId id);

	// get by id

	IMember getMember(ObjectId id);

	// new

	IMemberPhone newPhone(String number);

	IMember newMember(String name, IMemberPhone phone);

	IEvent newEvent(String title, IGroup creatorGroup, IMember creator);

	IGroup newGroup(String name, IMember member);

	IJoinEventList newJoinEventList(IEvent event, IMember member);

	IJoinGroupList newJoinGroupList(IGroup group, IMember member);

	IHoldEventList newHoldEventList(IEvent event, IGroup group);
}
