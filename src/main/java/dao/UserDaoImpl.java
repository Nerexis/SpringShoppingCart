package dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.User;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(User user) {
		sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public User getByUsername(String name) {
		final TypedQuery<User> query = sessionFactory.getCurrentSession()
				.createQuery("from User u WHERE u.username = :name ",
						User.class);
		query.setParameter("name", name);
		return query.getSingleResult();

	}

	@Override
	public List<User> list() {
		TypedQuery<User> query = sessionFactory.getCurrentSession()
				.createQuery("from User");
		return query.getResultList();
	}

	@Override
	public void update(User user) {
		sessionFactory.getCurrentSession().update(user);
	}

}
