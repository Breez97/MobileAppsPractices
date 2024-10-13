package ru.mirea.shamrov.domain.usecases.authentication;

import ru.mirea.shamrov.domain.repository.AuthRepository;

public class LogoutUseCase {

	private final AuthRepository authRepository;

	public LogoutUseCase(AuthRepository authRepository) {
		this.authRepository = authRepository;
	}

	public void execute() {
		authRepository.logout();;
	}
}
