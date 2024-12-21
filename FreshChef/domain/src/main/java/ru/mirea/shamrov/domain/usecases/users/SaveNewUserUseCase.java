package ru.mirea.shamrov.domain.usecases.users;

import ru.mirea.shamrov.domain.models.UserDTO;
import ru.mirea.shamrov.domain.repository.UserRepository;

public class SaveNewUserUseCase {

	private final UserRepository userRepository;

	public SaveNewUserUseCase(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void execute(UserDTO userDTO) {
		userRepository.saveNewUser(userDTO);
	}

}
