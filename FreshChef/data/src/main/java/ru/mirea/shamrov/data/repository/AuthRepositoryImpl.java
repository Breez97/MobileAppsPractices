package ru.mirea.shamrov.data.repository;

import ru.mirea.shamrov.data.firebase.utils.AuthCallbackFirebase;
import ru.mirea.shamrov.data.firebase.AuthStorage;
import ru.mirea.shamrov.domain.utils.AuthCallback;
import ru.mirea.shamrov.domain.repository.AuthRepository;

public class AuthRepositoryImpl implements AuthRepository {

	private final AuthStorage authStorage;

	public AuthRepositoryImpl(AuthStorage authStorage) {
		this.authStorage = authStorage;
	}

	@Override
	public void login(String email, String password, AuthCallback callback) {
		authStorage.login(email, password, new AuthCallbackAdapter(callback));
	}

	@Override
	public void register(String email, String password, AuthCallback callback) {
		authStorage.register(email, password, new AuthCallbackAdapter(callback));
	}

	@Override
	public boolean isUserAuthorized() {
		return authStorage.isUserAuthorized();
	}

	@Override
	public void logout() {
		authStorage.logout();
	}

	static class AuthCallbackAdapter implements AuthCallbackFirebase {

		private final AuthCallback authCallback;

		public AuthCallbackAdapter(AuthCallback authCallback) {
			this.authCallback = authCallback;
		}

		@Override
		public void onSuccess() {
			authCallback.onSuccess();
		}

		@Override
		public void onError(String errorMessage) {
			authCallback.onError(errorMessage);
		}
	}

}
