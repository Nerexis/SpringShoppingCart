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

	@Override
	@Transactional
	public Product getProductById(int idProduct) {
		return productDao.getProductById(idProduct);
	}

	@Override
	@Transactional
	public void addNewProduct(String name, String description, double price) {
		Product product = new Product();
		product.setName(name);
		product.setDescription(description);
		product.setPrice(price);
		this.save(product);
	}

	@Override
	public void update(Product product) {
		productDao.update(product);
	}

}
