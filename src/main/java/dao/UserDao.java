package dao;

import java.util.List;

import model.User;

public interface UserDao {
	void save(User user);
	User getByUsername(String name);
	List<User> list();
}
