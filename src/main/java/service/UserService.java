package service;

import java.util.List;

import model.User;

public interface UserService {
	void save(User user);

	User getByUsername(String name);

	List<User> list();

	boolean existsWithName(String name);

	void registerNewUser(String username, String password);
}
