package ru.mirea.shamrov.domain.usecases.authentication;

import ru.mirea.shamrov.domain.utils.AuthCallback;
import ru.mirea.shamrov.domain.repository.AuthRepository;

public class LoginUseCase {

	private final AuthRepository authRepository;

	public LoginUseCase(AuthRepository authRepository) {
		this.authRepository = authRepository;
	}

	public void execute(String email, String password, AuthCallback callback) {
		authRepository.login(email, password, callback);
	}

}
