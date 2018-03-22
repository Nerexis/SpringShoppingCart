package service;

import java.util.List;

import model.CartEntry;

public interface CartEntryService {
	void save(CartEntry cartEntry);
	List<CartEntry> list(int userId);
}
