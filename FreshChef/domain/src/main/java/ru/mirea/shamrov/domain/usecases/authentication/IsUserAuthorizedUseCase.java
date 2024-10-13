package ru.mirea.shamrov.domain.usecases.authentication;

import ru.mirea.shamrov.domain.repository.AuthRepository;

public class IsUserAuthorizedUseCase {

	private final AuthRepository authRepository;

	public IsUserAuthorizedUseCase(AuthRepository authRepository) {
		this.authRepository = authRepository;
	}

	public boolean execute() {
		return authRepository.isUserAuthorized();
	}

}
