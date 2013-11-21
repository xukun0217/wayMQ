package ananas.waymq.api;

import ananas.xgit.repo.ObjectId;

public interface IDocument {

	void save();

	IMember getRoot();

	ObjectId genId(String s);

	ObjectId parseId(String s);

	IMember getMember(String phone);

	IMemberPhone getMemberPhone(String phone);

	// get by id

	IGroup getGroup(ObjectId id);

	IEvent getEvent(ObjectId id);

	IMember getMember(ObjectId id);

	IMemberPhone getMemberPhone(ObjectId id);

	IJoinGroup getJoinGroup(ObjectId id);

	IJoinEvent getJoinEvent(ObjectId id);

	IHoldEvent getHoldEvent(ObjectId id);

	IJoinGroupList getJoinGroupList(ObjectId id);

	IJoinEventList getJoinEventList(ObjectId id);

	IHoldEventList getHoldEventList(ObjectId id);

	// new

	IMemberPhone newMemberPhone(String phone);

	IMember newMember(String name, IMemberPhone phone);

	IEvent newEvent(String name, IGroup creatorGroup, IMember creator);

	IGroup newGroup(String name, IMember creator);

	IJoinGroup newJoinGroup(IMember member, IGroup group);

	IJoinEvent newJoinEvent(IMember member, IEvent event);

	IHoldEvent newHoldEvent(IGroup group, IEvent event);

	IJoinGroupList newJoinGroupList(IMember member, IGroup group);

	IJoinEventList newJoinEventList(IMember member, IEvent event);

	IHoldEventList newHoldEventList(IGroup group, IEvent event);

}
