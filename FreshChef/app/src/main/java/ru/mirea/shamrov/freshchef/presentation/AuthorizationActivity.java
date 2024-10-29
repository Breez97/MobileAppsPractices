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
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.shamrov.freshchef.R;
import ru.mirea.shamrov.freshchef.databinding.ActivityAuthorizationBinding;
import ru.mirea.shamrov.freshchef.presentation.viewmodel.AuthorizationViewModel;
import ru.mirea.shamrov.freshchef.presentation.viewmodel.AuthorizationViewModelFactory;

public class AuthorizationActivity extends AppCompatActivity {

	private ActivityAuthorizationBinding binding;
	private AuthorizationViewModel authorizationViewModel;

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = ActivityAuthorizationBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		authorizationViewModel = new ViewModelProvider(this, new AuthorizationViewModelFactory(this)).get(AuthorizationViewModel.class);
		initWidgets();
		observeViewModel();
		checkAuthorization();
		buttonsFunctions();
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

	private void observeViewModel() {
		authorizationViewModel.isAuthorized().observe(this, isAuthorized -> {
			if (isAuthorized) {
				startActivity(new Intent(AuthorizationActivity.this, AccountActivity.class));
			}
		});

		authorizationViewModel.getErrorMessage().observe(this, errorMessage -> {
			if (errorMessage != null) {
				Toast.makeText(AuthorizationActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
			}
		});
	}

	private void checkAuthorization() {
		authorizationViewModel.checkAuthorization();
	}

	private void buttonsFunctions() {
		textLoginButton.setOnClickListener(view -> changeFormLogin());
		textRegisterButton.setOnClickListener(view -> changeFormRegister());

		binding.buttonLogin.setOnClickListener(v -> {
			String email = binding.editTextEmail.getText().toString();
			String password = binding.editTextPassword.getText().toString();
			if (checkFields(email, password)) {
				authorizationViewModel.login(email, password);
			}
		});

		binding.buttonRegistration.setOnClickListener(v -> {
			String name = editTextName.getText().toString();
			String email = editTextEmail.getText().toString();
			String password = editTextPassword.getText().toString();
			if (name.isEmpty()) {
				Toast.makeText(AuthorizationActivity.this, "Name can't be empty", Toast.LENGTH_SHORT).show();
			} else if (checkFields(email, password)) {
				authorizationViewModel.register(name, email, password);
			}
		});
	}

	private boolean checkFields(String email, String password) {
		if (email.isBlank()) {
			Toast.makeText(AuthorizationActivity.this, "Email can't be empty", Toast.LENGTH_SHORT).show();
			return false;
		} else if (password.isBlank()) {
			Toast.makeText(AuthorizationActivity.this, "Password can't be empty", Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
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