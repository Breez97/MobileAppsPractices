package ru.mirea.shamrov.domain.repository;

public interface AuthRepository {

	void login(String email, String password, AuthCallback callback);
	void register(String email, String password, AuthCallback callback);

}
