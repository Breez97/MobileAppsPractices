package ru.mirea.shamrov.domain.usecases;

import java.util.List;

import ru.mirea.shamrov.domain.repository.UserRepository;

public class GetUserFavoriteDishesUseCase {

	private UserRepository userRepository;

	public GetUserFavoriteDishesUseCase(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<Integer> execute(String name) {
		return userRepository.getUserFavoriteDishes(name);
	}

}
