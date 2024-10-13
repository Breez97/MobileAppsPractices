package ru.mirea.shamrov.domain.repository;

public interface AuthCallback {

	void onSuccess();
	void onError(String errorMessage);

}
