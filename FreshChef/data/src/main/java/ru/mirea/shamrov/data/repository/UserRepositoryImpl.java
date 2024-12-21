package ru.mirea.shamrov.data.repository;


import ru.mirea.shamrov.data.roomdatabase.UserDatabaseStorage;
import ru.mirea.shamrov.data.roomdatabase.model.UserDatabase;
import ru.mirea.shamrov.data.roomdatabase.utils.RoomDatabaseCallback;
import ru.mirea.shamrov.domain.models.UserDTO;
import ru.mirea.shamrov.domain.repository.UserRepository;
import ru.mirea.shamrov.domain.utils.DatabaseCallback;

public class UserRepositoryImpl implements UserRepository {

	private final UserDatabaseStorage userDatabaseStorage;

	public UserRepositoryImpl(UserDatabaseStorage userDatabaseStorage) {
		this.userDatabaseStorage = userDatabaseStorage;
	}

	@Override
	public void saveNewUser(UserDTO userDTO) {
		userDatabaseStorage.addNewUser(mapToUserDatabase(userDTO));
	}

	@Override
	public void getCurrentUser(String email, DatabaseCallback<UserDTO> callback) {
		userDatabaseStorage.getCurrentUser(email, new RoomDatabaseCallback<UserDatabase>() {
			@Override
			public void onSuccess(UserDatabase user) {
				callback.onSuccess(mapToUserDTO(user));
			}

			@Override
			public void onError(Exception e) {
				callback.onError(e);
			}
		});
	}

	@Override
	public void updateUserInfoDatabase(UserDTO userDTO) {
		userDatabaseStorage.updateUserInfoDatabase(mapToUserDatabase(userDTO));
	}

	private UserDatabase mapToUserDatabase(UserDTO userDTO) {
		return new UserDatabase(userDTO.getName(), userDTO.getEmail());
	}

	private UserDTO mapToUserDTO(UserDatabase userDatabase) {
		return new UserDTO(userDatabase.getName(), userDatabase.getEmail());
	}

}
