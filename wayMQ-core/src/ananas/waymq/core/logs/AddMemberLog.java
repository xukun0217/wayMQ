package ananas.waymq.core.logs;

import ananas.waymq.core.IMember;
import ananas.waymq.core.IMutableSnapshot;

public class AddMemberLog extends AbstractLog {

	private IMember member;

	@Override
	public void execute(IMutableSnapshot snap) {
		snap.addMember(member);
	}

	public void set(IMember member) {
		this.member = member;
	}

}
