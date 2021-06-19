package com.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDAOService {

	private static List<User> users = new ArrayList<>();
	private static int usersCount = 6;

	static {
		users.add(new User(1, "Dinesh", new Date()));
		users.add(new User(2, "Soni", new Date()));
		users.add(new User(3, "Divya", new Date()));
		users.add(new User(4, "Mano", new Date()));
		users.add(new User(5, "Sandy", new Date()));
		users.add(new User(6, "Vinith", new Date()));
	}

	public List<User> findAll() {
		return users;
	}

	public User save(User user) {
		if (user.getId() == null) {
			user.setId(++usersCount);
		}
		users.add(user);
		return user;
	}

	public User findOne(int id) {
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}

		return null;
	}

	public User deleteById(int id) {
//		if(users.removeIf(user -> user.getId() == id)) {
//			return users;
//		}

		Iterator<User> iterator = users.iterator();

		while (iterator.hasNext()) {
			User user = iterator.next();
			if (user.getId() == id) {
				iterator.remove();
				return user;
			}
		}

		return null;
	}
}
