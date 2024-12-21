package ru.mirea.shamrov.bottomnavigationapp.ui.authorization;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ru.mirea.shamrov.domain.models.UserDTO;
import ru.mirea.shamrov.domain.repository.AuthRepository;
import ru.mirea.shamrov.domain.repository.UserRepository;
import ru.mirea.shamrov.domain.usecases.authentication.LoginUseCase;
import ru.mirea.shamrov.domain.usecases.authentication.RegisterUseCase;
import ru.mirea.shamrov.domain.usecases.users.SaveNewUserUseCase;
import ru.mirea.shamrov.domain.utils.AuthCallback;

public class AuthorizationViewModel extends ViewModel {

	private final AuthRepository authRepository;
	private final UserRepository userRepository;

	private final MutableLiveData<Boolean> isAuthorized = new MutableLiveData<>();
	private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

	private final LoginUseCase loginUseCase;
	private final RegisterUseCase registerUseCase;
	private final SaveNewUserUseCase saveNewUserUseCase;

	public AuthorizationViewModel(AuthRepository authRepository, UserRepository userRepository) {
		this.authRepository = authRepository;
		this.userRepository = userRepository;
		this.loginUseCase = new LoginUseCase(authRepository);
		this.registerUseCase = new RegisterUseCase(authRepository);
		this.saveNewUserUseCase = new SaveNewUserUseCase(userRepository);
	}

	public MutableLiveData<Boolean> isAuthorized() {
		return isAuthorized;
	}

	public LiveData<String> getErrorMessage() {
		return errorMessage;
	}

	public void checkAuthorization() {
		isAuthorized.setValue(authRepository.isUserAuthorized());
	}

	public void login(String email, String password) {
		loginUseCase.execute(email, password, new AuthCallback() {
			@Override
			public void onSuccess() {
				isAuthorized.setValue(true);
			}

			@Override
			public void onError(String error) {
				errorMessage.setValue(error);
			}
		});
	}

	public void register(String name, String email, String password) {
		registerUseCase.execute(email, password, new AuthCallback() {
			@Override
			public void onSuccess() {
				UserDTO newUser = new UserDTO(name, email);
				saveNewUserUseCase.execute(newUser);
				isAuthorized.setValue(true);
			}

			@Override
			public void onError(String error) {
				errorMessage.setValue(error);
			}
		});
	}

}
