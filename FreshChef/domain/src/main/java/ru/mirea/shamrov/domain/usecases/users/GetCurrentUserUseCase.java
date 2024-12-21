package ru.mirea.shamrov.domain.usecases.users;

import ru.mirea.shamrov.domain.models.UserDTO;
import ru.mirea.shamrov.domain.repository.AuthRepository;
import ru.mirea.shamrov.domain.repository.UserRepository;

public class GetCurrentUserUseCase {

	private final UserRepository userRepository;

	public GetCurrentUserUseCase(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

//	public UserDTO execute() {
////		return userRepository.getCurrentUser("email");
//	}

}
