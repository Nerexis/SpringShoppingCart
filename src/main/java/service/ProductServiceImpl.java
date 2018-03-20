package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.ProductDao;
import model.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Transactional
	@Override
	public Product getProductByName(String name) {
		return productDao.getProductByName(name);
	}

	@Transactional
	@Override
	public void save(Product product) {
		productDao.save(product);
	}

	@Transactional
	@Override
	public List<Product> list() {
		return productDao.list();
	}

	@Transactional
	@Override
	public void remove(int id) {
		productDao.remove(id);
	}

}
