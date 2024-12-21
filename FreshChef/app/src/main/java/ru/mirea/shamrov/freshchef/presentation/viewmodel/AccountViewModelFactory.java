package ru.mirea.shamrov.freshchef.presentation.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.shamrov.data.firebase.AuthStorage;
import ru.mirea.shamrov.data.firebase.authstorage.FirebaseAuthStorage;
import ru.mirea.shamrov.data.repository.AuthRepositoryImpl;
import ru.mirea.shamrov.data.storage.UserStorage;
import ru.mirea.shamrov.data.storage.sharedprefs.SharedPrefsUserStorage;
import ru.mirea.shamrov.domain.repository.AuthRepository;
import ru.mirea.shamrov.domain.repository.UserRepository;

public class AccountViewModelFactory implements ViewModelProvider.Factory {

	private final Context context;

	public AccountViewModelFactory(Context context) {
		this.context = context;
	}

	@NonNull
	@Override
	public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
		AuthStorage authStorage = new FirebaseAuthStorage();
		AuthRepository authRepository = new AuthRepositoryImpl(authStorage);
		UserStorage userStorage = new SharedPrefsUserStorage(context);
		UserRepository userRepository = new UserRepositoryImpl(userStorage);
		return (T) new AccountViewModel(authRepository, userRepository);
	}
}
