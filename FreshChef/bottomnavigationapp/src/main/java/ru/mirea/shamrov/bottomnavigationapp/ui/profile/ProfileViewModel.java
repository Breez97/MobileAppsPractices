package ru.mirea.shamrov.bottomnavigationapp.ui.profile;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ru.mirea.shamrov.domain.models.UserDTO;
import ru.mirea.shamrov.domain.repository.AuthRepository;
import ru.mirea.shamrov.domain.repository.UserRepository;
import ru.mirea.shamrov.domain.usecases.authentication.GetAuthenticatedUserEmailFromFirebase;
import ru.mirea.shamrov.domain.usecases.authentication.LogoutUseCase;
import ru.mirea.shamrov.domain.usecases.roomdatabase.GetUserByEmail;
import ru.mirea.shamrov.domain.utils.DatabaseCallback;

public class ProfileViewModel extends ViewModel {

	private final AuthRepository authRepository;
	private final UserRepository userRepository;

	private final MutableLiveData<UserDTO> userData = new MutableLiveData<>();
	private final MutableLiveData<Boolean> isAuthorized = new MutableLiveData<>();

	private final LogoutUseCase logoutUseCase;
	private final GetAuthenticatedUserEmailFromFirebase emailUseCase;
	private final GetUserByEmail getUserByEmail;

	public ProfileViewModel(AuthRepository authRepository, UserRepository userRepository) {
		this.authRepository = authRepository;
		this.userRepository = userRepository;
		this.logoutUseCase = new LogoutUseCase(authRepository);
		this.emailUseCase = new GetAuthenticatedUserEmailFromFirebase(authRepository);
		this.getUserByEmail = new GetUserByEmail(userRepository);
	}

	public LiveData<UserDTO> getUserData() {
		return userData;
	}

	public LiveData<Boolean> isAuthorized() {
		return isAuthorized;
	}

	public void getUserInfo() {
		String currentUserEmail = emailUseCase.execute();
		getUserByEmail.execute(currentUserEmail, new DatabaseCallback<UserDTO>() {
			@Override
			public void onSuccess(UserDTO result) {
				userData.setValue(result);
			}

			@Override
			public void onError(Exception e) {
				Log.e(ProfileViewModel.class.getSimpleName(), "Error fetching user info", e);
			}
		});
	}

	public void checkAuthorization() {
		isAuthorized.setValue(authRepository.isUserAuthorized());
	}

	public void userLogout() {
		logoutUseCase.execute();
		isAuthorized.setValue(false);
	}
}
