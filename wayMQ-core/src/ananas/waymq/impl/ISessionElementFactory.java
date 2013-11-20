package ananas.waymq.impl;

import ananas.objectbox.IObject;
import ananas.waymq.core.ISession;
import ananas.waymq.core.ISessionElement;

public interface ISessionElementFactory {

	ISessionElement create(ISession session, IObject obj);

}
