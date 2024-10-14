package ru.mirea.shamrov.freshchef.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.shamrov.data.firebase.AuthStorage;
import ru.mirea.shamrov.data.firebase.authstorage.FirebaseAuthStorage;
import ru.mirea.shamrov.data.repository.AuthRepositoryImpl;
import ru.mirea.shamrov.data.repository.UserRepositoryImpl;
import ru.mirea.shamrov.data.storage.UserStorage;
import ru.mirea.shamrov.data.storage.sharedprefs.SharedPrefsUserStorage;
import ru.mirea.shamrov.domain.models.UserDTO;
import ru.mirea.shamrov.domain.repository.AuthRepository;
import ru.mirea.shamrov.domain.repository.UserRepository;
import ru.mirea.shamrov.domain.usecases.authentication.LogoutUseCase;
import ru.mirea.shamrov.domain.usecases.users.GetCurrentUserUseCase;
import ru.mirea.shamrov.freshchef.databinding.ActivityAccountBinding;

public class AccountActivity extends AppCompatActivity {

	private ActivityAccountBinding binding;

	private ImageButton buttonLogoMain;
	private EditText editTextNameAccount;
	private EditText editTextEmailAccount;
	private EditText editTextPasswordAccount;
	private Button buttonSave;
	private Button buttonLogout;

	private GetCurrentUserUseCase getCurrentUserUseCase;
	private LogoutUseCase logoutUseCase;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = ActivityAccountBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		initWidgets();
		initUseCases();
		fillWidgets();
		buttonsFunctions();
	}

	private void initWidgets() {
		buttonLogoMain = binding.buttonLogoMain;
		editTextNameAccount = binding.editTextNameAccount;
		editTextEmailAccount = binding.editTextEmailAccount;
		editTextPasswordAccount = binding.editTextPasswordAccount;
		buttonSave = binding.buttonSave;
		buttonLogout = binding.buttonLogout;
	}

	private void initUseCases() {
		UserStorage userStorage = new SharedPrefsUserStorage(this);
		UserRepository userRepository = new UserRepositoryImpl(userStorage);
		getCurrentUserUseCase = new GetCurrentUserUseCase(userRepository);

		AuthStorage authStorage = new FirebaseAuthStorage();
		AuthRepository authRepository = new AuthRepositoryImpl(authStorage);
		logoutUseCase = new LogoutUseCase(authRepository);
	}

	private void fillWidgets() {
		UserDTO userDTO = getCurrentUserUseCase.execute();
		editTextNameAccount.setText(userDTO.getName());
		editTextEmailAccount.setText(userDTO.getEmail());
	}

	private void buttonsFunctions() {
		buttonLogoMain.setOnClickListener(view -> {
			startActivity(new Intent(AccountActivity.this, HomeActivity.class));
//			startActivity(new Intent(AccountActivity.this, MainActivity.class));
		});

		buttonLogout.setOnClickListener(view -> {
			logoutUseCase.execute();
			startActivity(new Intent(AccountActivity.this, AuthorizationActivity.class));
		});
	}
}