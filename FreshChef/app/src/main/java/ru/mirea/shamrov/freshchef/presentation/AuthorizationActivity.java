package ru.mirea.shamrov.freshchef.presentation;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import ru.mirea.shamrov.data.repository.AuthRepositoryImpl;
import ru.mirea.shamrov.domain.models.UserAuthDTO;
import ru.mirea.shamrov.domain.repository.AuthCallback;
import ru.mirea.shamrov.domain.usecases.LoginUseCase;
import ru.mirea.shamrov.domain.usecases.RegisterUseCase;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = ActivityAuthorizationBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		initUseCases();
		initWidgets();
		buttonsFunctions();
	}

	private void initUseCases() {
		FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
		AuthRepositoryImpl authRepository = new AuthRepositoryImpl(firebaseAuth);
		loginUseCase = new LoginUseCase(authRepository);
		registerUseCase = new RegisterUseCase(authRepository);
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

	private void buttonsFunctions() {
		textLoginButton.setOnClickListener(view -> changeFormLogin());
		textRegisterButton.setOnClickListener(view -> changeFormRegister());

		binding.buttonLogin.setOnClickListener(v -> {
			String email = binding.editTextEmail.getText().toString();
			String password = binding.editTextPassword.getText().toString();
			loginFunction(email, password);
		});

		binding.buttonRegistration.setOnClickListener(v -> {
			String email = binding.editTextEmail.getText().toString();
			String password = binding.editTextPassword.getText().toString();
			registerFunction(email, password);
		});
	}

	private void loginFunction(String email, String password) {
		loginUseCase.execute(email, password, new AuthCallback() {
			@Override
			public void onSuccess(UserAuthDTO user) {
				Toast.makeText(AuthorizationActivity.this, "Sign in successful", Toast.LENGTH_SHORT).show();
				// TODO: Navigate to main activity
			}
			@Override
			public void onError(String errorMessage) {
				Toast.makeText(AuthorizationActivity.this, "Sign in failed: " + errorMessage, Toast.LENGTH_SHORT).show();
			}
		});
	}

	private void registerFunction(String email, String password) {
		registerUseCase.execute(email, password, new AuthCallback() {
			@Override
			public void onSuccess(UserAuthDTO user) {
				Toast.makeText(AuthorizationActivity.this, "Sign up successful", Toast.LENGTH_SHORT).show();
				// TODO: Navigate to main activity
			}
			@Override
			public void onError(String errorMessage) {
				Toast.makeText(AuthorizationActivity.this, "Sign up failed: " + errorMessage, Toast.LENGTH_SHORT).show();
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