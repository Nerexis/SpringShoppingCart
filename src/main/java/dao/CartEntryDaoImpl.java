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
	public List<CartEntry> list(int userId) {
		TypedQuery<CartEntry> query = sessionFactory.getCurrentSession().createQuery(
				"from CartEntry c WHERE c.idUser = :userId",CartEntry.class);
		query.setParameter("userId", userId);
		return query.getResultList();
	}

}
