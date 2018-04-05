package service;

import java.util.List;

import model.Product;

public interface ProductService {
	void save(Product product);

	void update(Product product);

	List<Product> list();

	Product getProductByName(String name);

	Product getProductById(int idProduct);

	void addNewProduct(String name, String description, double price);

	void remove(int id);
}
