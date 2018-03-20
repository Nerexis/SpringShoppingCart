package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.UserDao;
import model.User;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserService, UserDetailsService {
	@Autowired
	private UserDao userDao;

	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		System.out.println("Loading user");
		User user = userDao.getByUsername(name);
		UserBuilder builder = null;
		if (user != null) {

			builder = org.springframework.security.core.userdetails.User.withUsername(name);
			builder.disabled(!user.isEnabled());
			builder.password(user.getPassword());
			String[] authorities = user.getAuthorities()
					.stream().map(a -> a.getAuthority()).toArray(String[]::new);

			builder.authorities(authorities);
		} else {
			throw new UsernameNotFoundException("User not found.");
		}
		System.out.println("Got user with id: " + user.getId());
		return builder.build();
	}

	@Override
	@Transactional
	public void save(User user) {
		userDao.save(user);

	}

	@Override
	@Transactional
	public User getByUsername(String name) {
		return userDao.getByUsername(name);
	}

	@Override
	@Transactional
	public List<User> list() {
		return userDao.list();
	}
	@Override
	@Transactional
	public boolean existsWithName(String name) {
		if(getByUsername(name) != null) {
			return true;
		}
		return false;
	}
}
