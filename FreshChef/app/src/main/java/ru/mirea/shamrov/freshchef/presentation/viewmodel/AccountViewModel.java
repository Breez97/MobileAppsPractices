package ru.mirea.shamrov.freshchef.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ru.mirea.shamrov.domain.models.UserDTO;
import ru.mirea.shamrov.domain.repository.AuthRepository;
import ru.mirea.shamrov.domain.repository.UserRepository;
import ru.mirea.shamrov.domain.usecases.authentication.LogoutUseCase;
import ru.mirea.shamrov.domain.usecases.users.GetCurrentUserUseCase;

public class AccountViewModel extends ViewModel {

	private final AuthRepository authRepository;
	private final UserRepository userRepository;

	private final MutableLiveData<UserDTO> userData = new MutableLiveData<>();

	private final GetCurrentUserUseCase getCurrentUserUseCase;
	private final LogoutUseCase logoutUseCase;

	public AccountViewModel(AuthRepository authRepository, UserRepository userRepository) {
		this.authRepository = authRepository;
		this.userRepository = userRepository;
		logoutUseCase = new LogoutUseCase(authRepository);
		getCurrentUserUseCase = new GetCurrentUserUseCase(userRepository);
	}

	public LiveData<UserDTO> getUserData() {
		return userData;
	}

	public void getUserInfo() {
//		UserDTO userDTO = getCurrentUserUseCase.execute();
//		System.out.println(userDTO);
//		userData.setValue(userDTO);
	}

	public void userLogout() {
		logoutUseCase.execute();
	}

}
