package ru.mirea.shamrov.bottomnavigationapp.ui.profile;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.shamrov.data.firebase.AuthStorage;
import ru.mirea.shamrov.data.firebase.authstorage.FirebaseAuthStorage;
import ru.mirea.shamrov.data.repository.AuthRepositoryImpl;
import ru.mirea.shamrov.data.repository.UserRepositoryImpl;
import ru.mirea.shamrov.data.roomdatabase.UserDatabaseStorage;
import ru.mirea.shamrov.data.roomdatabase.databasestorage.UserRoomDatabaseStorage;
import ru.mirea.shamrov.domain.repository.AuthRepository;
import ru.mirea.shamrov.domain.repository.UserRepository;

public class ProfileViewModelFactory implements ViewModelProvider.Factory {

	private final Context context;

	public ProfileViewModelFactory(Context context) {
		this.context = context;
	}

	@NonNull
	@Override
	public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
		AuthStorage authStorage = new FirebaseAuthStorage();
		AuthRepository authRepository = new AuthRepositoryImpl(authStorage);
		UserDatabaseStorage userDatabaseStorage = new UserRoomDatabaseStorage(context);
		UserRepository userRepository = new UserRepositoryImpl(userDatabaseStorage);
		return (T) new ProfileViewModel(authRepository, userRepository);
	}
}
