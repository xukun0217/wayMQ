package ananas.waymq.core.logs;

import ananas.waymq.core.IEvent;
import ananas.waymq.core.IMember;
import ananas.waymq.core.IMutableSnapshot;

public class JoinLog extends AbstractLog {

	private IEvent event;
	private IMember member;

	@Override
	public void execute(IMutableSnapshot snap) {
		snap.join(this.member, this.event);
	}

	public void setEvent(IEvent event) {
		this.event = event;
	}

	public void setMember(IMember member) {
		this.member = member;
	}

}
