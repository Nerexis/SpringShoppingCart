package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.CartEntryDao;
import model.CartEntry;
@Service
public class CartEntryServiceImpl implements CartEntryService {

	@Autowired
	private CartEntryDao cartEntryDao;

	@Transactional
	@Override
	public void save(CartEntry cartEntry) {
		cartEntryDao.save(cartEntry);
	}

	@Transactional
	@Override
	public List<CartEntry> list(int userId) {
		return cartEntryDao.list(userId);
	}



}
