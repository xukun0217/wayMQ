package ananas.waymq.core;

import java.util.Map;

import ananas.waymq.api.IMember;
import ananas.waymq.api.IMemberPhone;
import ananas.xgit.repo.ObjectId;

public interface ISession {

	interface type {

		String phone = "Phone";
		String member  = "Member";

	}

	IMember getRoot();

	IMemberPhone newPhone(String number);

	ISessionElement getElement(ObjectId id);

	ISessionElement newElement(String type, Map<String, String> attr);

	IMember getMember(ObjectId id);

	void close();
}
