package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.OrderDao;
import model.CartEntry;
import model.Order;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private CartEntryService cartEntryService;

	@Override
	@Transactional
	public void makeOrderForCurrentUser(String name, String surname,
			String address, String phone) {
		System.out.println("Make order for current user");

		Order order = new Order();
		order.setName(name);
		order.setSurname(surname);
		order.setAddress(address);
		order.setPhone(phone);
		orderDao.save(order);

		List<CartEntry> cartEntries = cartEntryService
				.listForNonOrderedForCurrentUser();
		for (CartEntry ce : cartEntries) {
			ce.setOrder(order);

			cartEntryService.update(ce);
		}

	}

}
