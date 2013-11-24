package ananas.waymq.orm;

import java.io.Serializable;

import org.hibernate.Session;

public class BaseDAO<T extends OrmObject> {

	private final Class<?> _model_class;

	public BaseDAO(Class<?> modelClass) {
		this._model_class = modelClass;
	}

	public Session getSession() {
		return WaymqSessionFactory.getSession();
	}

	@SuppressWarnings("unchecked")
	public T get(String id) {
		if (id == null)
			return null;
		Session session = this.getSession();
		return (T) session.get(this._model_class, id);
	}

	@SuppressWarnings("unchecked")
	public T put(T obj) {

		String id = obj.getId();
		if (id == null) {
			// new
			Session session = this.getSession();
			session.beginTransaction();
			Serializable id2 = session.save(obj);
			obj = (T) session.get(this._model_class, id2);
			session.getTransaction().commit();
		} else {
			// update
			Session session = this.getSession();
			session.beginTransaction();
			session.update(obj);
			session.getTransaction().commit();
		}
		return obj;
	}

}
