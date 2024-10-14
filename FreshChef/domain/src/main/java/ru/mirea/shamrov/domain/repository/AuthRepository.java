package ru.mirea.shamrov.domain.repository;

import ru.mirea.shamrov.domain.utils.AuthCallback;

public interface AuthRepository {

	void login(String email, String password, AuthCallback callback);
	void register(String email, String password, AuthCallback callback);
	boolean isUserAuthorized();
	void logout();

}
