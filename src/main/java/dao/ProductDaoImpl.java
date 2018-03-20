package dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Product;
@Repository
public class ProductDaoImpl implements ProductDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(Product product) {
		sessionFactory.getCurrentSession().save(product);

	}

	@Override
	public List<Product> list() {
		TypedQuery<Product> query = sessionFactory.getCurrentSession().createQuery(
				"from Product",Product.class);
		return query.getResultList();
	}

	@Override
	public Product getProductByName(String name) {
		return sessionFactory.getCurrentSession().get(Product.class, name);
	}

	@Override
	public void remove(int id) {
		//Transaction transaction = sessionFactory.getCurrentSession().beginTransaction();
		TypedQuery query = sessionFactory.getCurrentSession().createQuery(
				"delete from Product p WHERE p.id = :id");
		query.setParameter("id", id);
		try {
			query.executeUpdate();
			//transaction.commit();
		} catch(Exception e) {
			//transaction.rollback();
		}
	}

}
