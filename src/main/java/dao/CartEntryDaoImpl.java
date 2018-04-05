package dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.CartEntry;

@Repository
public class CartEntryDaoImpl implements CartEntryDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public void save(CartEntry cartEntry) {
		sessionFactory.getCurrentSession().save(cartEntry);
	}

	@Override
	@Transactional
	public List<CartEntry> listNonOrdered(String username) {
		TypedQuery<CartEntry> query = sessionFactory.getCurrentSession()
				.createQuery(
						"select c from CartEntry c WHERE c.user.username = :username AND c.order IS NULL",
						CartEntry.class);
		query.setParameter("username", username);
		System.out.println(
				"got " + query.getResultList().size() + " cart entries");
		return query.getResultList();
	}

	@Override
	@Transactional
	public void remove(int cartEntryId) {
		TypedQuery<?> query = sessionFactory.getCurrentSession()
				.createQuery("delete from CartEntry ce WHERE ce.id = :id");
		query.setParameter("id", cartEntryId);
		try {
			query.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void update(CartEntry cartEntry) {
		sessionFactory.getCurrentSession().update(cartEntry);
	}

	@Override
	public CartEntry getById(int cartEntryId) {
		TypedQuery<CartEntry> query = sessionFactory.getCurrentSession()
				.createQuery("from CartEntry ce WHERE ce.id = :id",
						CartEntry.class);
		query.setParameter("id", cartEntryId);
		return query.getSingleResult();
	}

}
