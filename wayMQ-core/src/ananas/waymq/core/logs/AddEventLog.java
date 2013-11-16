package ananas.waymq.core.logs;

import ananas.waymq.core.IEvent;
import ananas.waymq.core.IMutableSnapshot;

public class AddEventLog extends AbstractLog {

	private IEvent event;

	@Override
	public void execute(IMutableSnapshot snap) {
		snap.addEvent(this.event);
	}

	public void setEvent(IEvent event) {
		this.event = event;
	}

}
