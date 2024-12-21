package ru.mirea.shamrov.domain.usecases.authentication;

import ru.mirea.shamrov.domain.repository.AuthRepository;

public class GetAuthenticatedUserEmailFromFirebase {

	private final AuthRepository authRepository;

	public GetAuthenticatedUserEmailFromFirebase(AuthRepository authRepository) {
		this.authRepository = authRepository;
	}

	public String execute() {
		return authRepository.getAuthenticatedUserEmail();
	}
}
