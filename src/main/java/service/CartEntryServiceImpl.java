package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.CartEntryDao;
import model.CartEntry;
import model.Product;
import model.User;

@Service
public class CartEntryServiceImpl implements CartEntryService {

	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;

	@Autowired
	private CartEntryDao cartEntryDao;

	@Transactional
	@Override
	public void save(CartEntry cartEntry) {
		cartEntryDao.save(cartEntry);
	}

	@Transactional
	@Override
	public List<CartEntry> list(String username) {
		return cartEntryDao.listNonOrdered(username);
	}

	@Transactional
	@Override
	public CartEntry createCartEntryForUser(User user, Product product,
			int quantity) {

		if (quantity <= 0) {
			throw new IllegalArgumentException(
					"Iloœæ nie mo¿e byæ mniejsza/równa 0");
		}
		CartEntry cartEntry = new CartEntry();
		cartEntry.setProduct(product);
		cartEntry.setQuantity(quantity);
		cartEntry.setUser(user);

		return cartEntry;
	}

	@Override
	@Transactional
	public void addCartEntryForCurrentUser(int idProduct, int quantity) {
		System.out.println("Adding cart entry...");

		Product product = productService.getProductById(idProduct);
		if (product == null) {
			throw new RuntimeException("Nie znaleziono produktu");
		}

		if (quantity > product.getQuantity()) {
			throw new RuntimeException(
					"Taka iloœæ produktu nie jest dostêpna w sklepie!");
		}

		product.setQuantity(product.getQuantity() - quantity);
		productService.update(product);

		CartEntry cartEntry = createCartEntryForUser(
				userService.getByUsername(SecurityContextHolder.getContext()
						.getAuthentication().getName()),
				productService.getProductById(idProduct), quantity);
		this.save(cartEntry);

	}

	@Override
	@Transactional
	public void remove(int cartEntryId) {

		CartEntry cartEntry = cartEntryDao.getById(cartEntryId);
		Product product = cartEntry.getProduct();
		product.setQuantity(product.getQuantity() + cartEntry.getQuantity());
		productService.update(product);

		cartEntryDao.remove(cartEntryId);
	}

	@Override
	@Transactional
	public void changeQuantity(int cartEntryId, int newQuantity) {
		CartEntry cartEntry = cartEntryDao.getById(cartEntryId);
		if (cartEntry == null) {
			throw new RuntimeException("Nie znaleziono produktu w koszyku!");
		}
		System.out.println("Updating cart entry quantity to " + newQuantity);

		int quantityDiff = cartEntry.getQuantity() - newQuantity;
		Product product = cartEntry.getProduct();
		int newProductQuantity = product.getQuantity() + quantityDiff;
		if (newProductQuantity < 0) {
			throw new RuntimeException("Nie ma tyle produktów w sklepie, max: "
					+ product.getQuantity());
		}
		product.setQuantity(newProductQuantity);
		productService.update(product);

		if (newQuantity <= 0) {
			System.out.println("New quantity <= 0 removing cart entry");
			cartEntryDao.remove(cartEntryId);
			return;
		}

		cartEntry.setQuantity(newQuantity);
		cartEntryDao.update(cartEntry);
	}

	@Override
	public List<CartEntry> listForNonOrderedForCurrentUser() {
		return this.list(SecurityContextHolder.getContext().getAuthentication()
				.getName());
	}

	@Override
	public void update(CartEntry cartEntry) {
		cartEntryDao.update(cartEntry);
	}

}
