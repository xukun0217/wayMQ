package ananas.waymq.orm.dao;

import ananas.waymq.orm.BaseDAO;
import ananas.waymq.orm.model.Group;
import ananas.waymq.orm.model.User;

public class GroupDAO extends BaseDAO<Group> {

	public GroupDAO() {
		super(Group.class);
	}

	public Group create(User user) {
		Group group = new Group();
		group.setUserId(user.getId());
		return this.put(group);
	}

}
