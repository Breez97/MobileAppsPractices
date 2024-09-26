package ru.mirea.shamrov.freshchef.domain.usecases;

import java.util.List;

import ru.mirea.shamrov.freshchef.domain.repository.UserRepository;

public class GetUserFavoriteDishesUseCase {

	private UserRepository userRepository;

	public GetUserFavoriteDishesUseCase(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<Integer> execute(String name) {
		return userRepository.getUserFavoriteDishes(name);
	}

}
