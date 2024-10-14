package ru.mirea.shamrov.domain.utils;

public interface AuthCallback {

	void onSuccess();
	void onError(String errorMessage);

}
