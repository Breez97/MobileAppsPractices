package ru.mirea.shamrov.domain.usecases.users;

import ru.mirea.shamrov.domain.repository.UserRepository;

public class GetAllUsersUseCase {

	private final UserRepository userRepository;

	public GetAllUsersUseCase(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

//	public List<UserDTO> execute() {
//		return userRepository.getAllUsers();
//	}

}
