package ru.mirea.shamrov.domain.usecases.roomdatabase;

import ru.mirea.shamrov.domain.models.UserDTO;
import ru.mirea.shamrov.domain.repository.UserRepository;
import ru.mirea.shamrov.domain.utils.DatabaseCallback;

public class GetUserByEmail {

	private final UserRepository userRepository;

	public GetUserByEmail(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void execute(String email, DatabaseCallback<UserDTO> callback) {
		userRepository.getCurrentUser(email, new DatabaseCallback<>() {
			@Override
			public void onSuccess(UserDTO result) {
				callback.onSuccess(result);
			}

			@Override
			public void onError(Exception e) {
				callback.onError(e);
			}
		});
	}

}
