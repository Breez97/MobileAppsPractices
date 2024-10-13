package ru.mirea.shamrov.data.storage;

import ru.mirea.shamrov.data.storage.models.User;

public interface UserStorage {

	boolean saveNewUser(User user);
	User getCurrentUser();

}
