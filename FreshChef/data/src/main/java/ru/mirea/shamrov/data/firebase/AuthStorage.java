package ru.mirea.shamrov.data.firebase;

import ru.mirea.shamrov.data.firebase.utils.AuthCallbackFirebase;

public interface AuthStorage {

	void login(String email, String password, AuthCallbackFirebase callback);
	void register(String email, String password, AuthCallbackFirebase callback);
	boolean isUserAuthorized();
	void logout();
	String getAuthenticatedUserEmail();

}
