package dao;

import java.util.List;

import model.CartEntry;

public interface CartEntryDao {
	void save(CartEntry cartEntry);
	List<CartEntry> list(int userId);
}
