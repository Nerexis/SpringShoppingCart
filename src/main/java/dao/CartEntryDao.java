package dao;

import java.util.List;

import model.CartEntry;

public interface CartEntryDao {
	void save(CartEntry cartEntry);

	void update(CartEntry cartEntry);

	List<CartEntry> listNonOrdered(String username);

	void remove(int cartEntryId);

	CartEntry getById(int cartEntryId);

}
