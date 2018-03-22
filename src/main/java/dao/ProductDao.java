package dao;

import java.util.List;

import model.Product;

public interface ProductDao {
	void save(Product product);
	List<Product> list();
	Product getProductByName(String name);
	Product getProductById(int idProduct);
	void remove(int id);
}
