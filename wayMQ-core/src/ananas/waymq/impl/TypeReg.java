package ananas.waymq.impl;

import java.util.Map;

import ananas.objectbox.IObject;
import ananas.waymq.core.ISession;
import ananas.waymq.core.ISession.type;
import ananas.waymq.core.ISessionElement;
import ananas.waymq.model.Member;
import ananas.waymq.model.MemberPhone;

public class TypeReg {

	public static void __add_type(Map<String, ISessionElementFactory> map,
			String type, ISessionElementFactory factory) {

		if (map.get(type) == null) {
			map.put(type, factory);
		} else {
			throw new RuntimeException("the type has been reg : " + type);
		}

	}

	public static void __reg_all_types(Map<String, ISessionElementFactory> map) {

		TypeReg.__add_type(map, type.phone, new ISessionElementFactory() {
			@Override
			public ISessionElement create(ISession session, IObject obj) {
				return new MemberPhone(session, obj);
			}
		});

		TypeReg.__add_type(map, type.member, new ISessionElementFactory() {
			@Override
			public ISessionElement create(ISession session, IObject obj) {
				return new Member(session, obj);
			}
		});

	}

}
