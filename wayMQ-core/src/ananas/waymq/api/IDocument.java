package ananas.waymq.api;

import java.util.Enumeration;

import ananas.lib.io.vfs.VFile;
import ananas.objectbox.ITypeRegistrar;
import ananas.xgit.repo.ObjectId;

public interface IDocument {

	VFile getFile();

	void save();

	void init();

	void check();

	boolean exists();

	void cleanCache();

	//

	ITypeRegistrar getTypes();

	//

	IUser getRoot();

	ObjectId genId(String s);

	ObjectId parseId(String s);

	IMember getMember(String phone);

	IPhone getPhone(String phone);

	Enumeration<IElement> objects();

	// get by id

	IGroup getGroup(ObjectId id);

	IEvent getEvent(ObjectId id);

	IMember getMember(ObjectId id);

	IPhone getPhone(ObjectId id);

	IJoinGroup getJoinGroup(ObjectId id);

	IJoinEvent getJoinEvent(ObjectId id);

	IHoldEvent getHoldEvent(ObjectId id);

	// new

	IPhone newPhone(String phone);

	IMember newMember(String name, IPhone phone);

	IEvent newEvent(String name, IGroup creatorGroup, IMember creator);

	IGroup newGroup(String name, IMember creator);

	IJoinGroup newJoinGroup(IMember member, IGroup group);

	IJoinEvent newJoinEvent(IMember member, IEvent event);

	IHoldEvent newHoldEvent(IGroup group, IEvent event);

}
