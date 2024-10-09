package ru.mirea.shamrov.freshchef.data.storage.internalstorage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ru.mirea.shamrov.freshchef.data.storage.UserStorage;
import ru.mirea.shamrov.freshchef.data.storage.models.User;

public class InternalUserStorage implements UserStorage {

	private final List<User> USERS = new ArrayList<>(
			List.of(
					new User(1, "Ilya", new ArrayList<>(), LocalDate.now()),
					new User(2, "Sergey", new ArrayList<>(List.of(1)), LocalDate.now())
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
