package service;

import java.util.List;

import model.Product;

public interface ProductService {
	void save(Product product);
	List<Product> list();
	Product getProductByName(String name);
	void remove(int id);
}
