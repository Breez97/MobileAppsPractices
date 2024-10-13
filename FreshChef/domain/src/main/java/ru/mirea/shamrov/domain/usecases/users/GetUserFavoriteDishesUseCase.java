package ru.mirea.shamrov.domain.usecases.users;

import ru.mirea.shamrov.domain.repository.UserRepository;

public class GetUserFavoriteDishesUseCase {

	private final UserRepository userRepository;

	public GetUserFavoriteDishesUseCase(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

//	public List<Integer> execute(String name) {
//		return userRepository.getUserFavoriteDishes(name);
//	}

}
