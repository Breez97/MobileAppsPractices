package ru.mirea.shamrov.freshchef.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import ru.mirea.shamrov.data.firebase.AuthStorage;
import ru.mirea.shamrov.data.firebase.authstorage.FirebaseAuthStorage;
import ru.mirea.shamrov.data.repository.AuthRepositoryImpl;
import ru.mirea.shamrov.data.repository.UserRepositoryImpl;
import ru.mirea.shamrov.data.storage.UserStorage;
import ru.mirea.shamrov.data.storage.sharedprefs.SharedPrefsUserStorage;
import ru.mirea.shamrov.domain.models.UserDTO;
import ru.mirea.shamrov.domain.utils.AuthCallback;
import ru.mirea.shamrov.domain.repository.UserRepository;
import ru.mirea.shamrov.domain.usecases.users.GetCurrentUserUseCase;
import ru.mirea.shamrov.domain.usecases.authentication.IsUserAuthorizedUseCase;
import ru.mirea.shamrov.domain.usecases.authentication.LoginUseCase;
import ru.mirea.shamrov.domain.usecases.authentication.RegisterUseCase;
import ru.mirea.shamrov.domain.usecases.users.SaveNewUserUseCase;
import ru.mirea.shamrov.freshchef.R;
import ru.mirea.shamrov.freshchef.databinding.ActivityAuthorizationBinding;

public class AuthorizationActivity extends AppCompatActivity {

	private ActivityAuthorizationBinding binding;

	private TextView textLoginButton;
	private TextView textRegisterButton;
	private EditText editTextEmail;
	private EditText editTextPassword;
	private EditText editTextName;
	private Button buttonLogin;
	private Button buttonRegistration;
	private LinearLayout mainLayout;

	private int greenColor;
	private int blackColor;

	private LoginUseCase loginUseCase;
	private RegisterUseCase registerUseCase;
	private SaveNewUserUseCase saveNewUserUseCase;
	private GetCurrentUserUseCase getCurrentUserUseCase;
	private IsUserAuthorizedUseCase isUserAuthorizedUseCase;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = ActivityAuthorizationBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		initUseCases();
		initWidgets();
		checkAuthorization();
		buttonsFunctions();
	}

	private void initUseCases() {
		AuthStorage authStorage = new FirebaseAuthStorage();
		AuthRepositoryImpl authRepository = new AuthRepositoryImpl(authStorage);
		loginUseCase = new LoginUseCase(authRepository);
		registerUseCase = new RegisterUseCase(authRepository);
		isUserAuthorizedUseCase = new IsUserAuthorizedUseCase(authRepository);

		UserStorage userStorage = new SharedPrefsUserStorage(this);
		UserRepository userRepository = new UserRepositoryImpl(userStorage);
		saveNewUserUseCase = new SaveNewUserUseCase(userRepository);
		getCurrentUserUseCase = new GetCurrentUserUseCase(userRepository);
	}

	private void initWidgets() {
		textLoginButton = binding.textLoginButton;
		textRegisterButton = binding.textRegisterButton;
		editTextEmail = binding.editTextEmail;
		editTextPassword = binding.editTextPassword;
		editTextName = binding.editTextName;
		buttonLogin = binding.buttonLogin;
		buttonRegistration = binding.buttonRegistration;
		mainLayout = binding.mainLayout;
		greenColor = getResources().getColor(R.color.main_green, null);
		blackColor = getResources().getColor(R.color.black, null);
	}

	private void checkAuthorization() {
		boolean isAuthorized = isUserAuthorizedUseCase.execute();
		if (isAuthorized) {
			startActivity(new Intent(AuthorizationActivity.this, AccountActivity.class));
		}
	}

	private void buttonsFunctions() {
		textLoginButton.setOnClickListener(view -> changeFormLogin());
		textRegisterButton.setOnClickListener(view -> changeFormRegister());

		binding.buttonLogin.setOnClickListener(v -> {
			String email = binding.editTextEmail.getText().toString();
			String password = binding.editTextPassword.getText().toString();
			loginFunction(email, password);
		});

		binding.buttonRegistration.setOnClickListener(v -> {
			String name = editTextName.getText().toString();
			String email = editTextEmail.getText().toString();
			String password = editTextPassword.getText().toString();
			registerFunction(name, email, password);
		});
	}

	private void loginFunction(String email, String password) {
		loginUseCase.execute(email, password, new AuthCallback() {
			@Override
			public void onSuccess() {
				Toast.makeText(AuthorizationActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
				startActivity(new Intent(AuthorizationActivity.this, AccountActivity.class));
			}
			@Override
			public void onError(String errorMessage) {
				Toast.makeText(AuthorizationActivity.this, "Login failed: " + errorMessage, Toast.LENGTH_SHORT).show();
				editTextPassword.setText("");
			}
		});
	}

	private void registerFunction(String name, String email, String password) {
		registerUseCase.execute(email, password, new AuthCallback() {
			@Override
			public void onSuccess() {
				Toast.makeText(AuthorizationActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
				saveNewUserUseCase.execute(new UserDTO(1, name, email, password, new ArrayList<>()));
				startActivity(new Intent(AuthorizationActivity.this, AccountActivity.class));
			}
			@Override
			public void onError(String errorMessage) {
				Toast.makeText(AuthorizationActivity.this, "Registration failed: " + errorMessage, Toast.LENGTH_SHORT).show();
				editTextEmail.setText("");
				editTextPassword.setText("");
			}
		});
	}

	private void changeFormLogin() {
		textLoginButton.setTextColor(greenColor);
		textRegisterButton.setTextColor(blackColor);
		editTextName.setVisibility(View.GONE);
		buttonLogin.setVisibility(View.VISIBLE);
		buttonRegistration.setVisibility(View.GONE);
		ViewGroup.LayoutParams params = mainLayout.getLayoutParams();
		params.height = convertDpToPx(290);
		mainLayout.setLayoutParams(params);
	}

	private void changeFormRegister() {
		textLoginButton.setTextColor(blackColor);
		textRegisterButton.setTextColor(greenColor);
		editTextName.setVisibility(View.VISIBLE);
		buttonLogin.setVisibility(View.GONE);
		buttonRegistration.setVisibility(View.VISIBLE);
		ViewGroup.LayoutParams params = mainLayout.getLayoutParams();
		params.height = convertDpToPx(350);
		mainLayout.setLayoutParams(params);
	}

	private int convertDpToPx(int dp) {
		return (int) (dp * getResources().getDisplayMetrics().density);
	}

}