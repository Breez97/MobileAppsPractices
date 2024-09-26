package ru.mirea.shamrov.freshchef.data.repository;

import java.util.ArrayList;
import java.util.List;

import ru.mirea.shamrov.freshchef.domain.models.User;
import ru.mirea.shamrov.freshchef.domain.repository.UserRepository;

public class UserRepositoryImpl implements UserRepository {

	private final List<User> USERS = new ArrayList<>(
			List.of(
					new User(1, "Ilya", new ArrayList<>()),
					new User(2, "Sergey", new ArrayList<>(List.of(1)))
			)
	);

	@Override
	public List<User> getAllUsers() {
		return USERS;
	}

	@Override
	public List<Integer> getUserFavoriteDishes(String name) {
		for (User currentUser : USERS) {
			if (currentUser.getName().equalsIgnoreCase(name)) {
				return currentUser.getFavoriteDishes();
			}
		}
		return new ArrayList<>(List.of(-1));
	}

}
