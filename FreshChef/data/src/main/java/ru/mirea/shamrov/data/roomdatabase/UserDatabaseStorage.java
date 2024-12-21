package ru.mirea.shamrov.data.roomdatabase;

import ru.mirea.shamrov.data.roomdatabase.model.UserDatabase;
import ru.mirea.shamrov.data.roomdatabase.utils.RoomDatabaseCallback;

public interface UserDatabaseStorage {

	void addNewUser(UserDatabase user);
	void getCurrentUser(String email, RoomDatabaseCallback<UserDatabase> callback);
	void updateUserInfoDatabase(UserDatabase userDatabaseNew);

}
