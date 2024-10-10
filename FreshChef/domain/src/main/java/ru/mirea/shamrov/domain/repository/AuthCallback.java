package ru.mirea.shamrov.domain.repository;

import ru.mirea.shamrov.domain.models.UserAuthDTO;

public interface AuthCallback {

	void onSuccess(UserAuthDTO user);
	void onError(String errorMessage);

}
