package ru.mirea.shamrov.freshchef.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.shamrov.freshchef.databinding.ActivityAccountBinding;
import ru.mirea.shamrov.freshchef.presentation.viewmodel.AccountViewModel;
import ru.mirea.shamrov.freshchef.presentation.viewmodel.AccountViewModelFactory;

public class AccountActivity extends AppCompatActivity {

	private ActivityAccountBinding binding;

	private AccountViewModel accountViewModel;

	private ImageButton buttonLogoMain;
	private EditText editTextNameAccount;
	private EditText editTextEmailAccount;
	private EditText editTextPasswordAccount;
	private Button buttonSave;
	private Button buttonLogout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		accountViewModel = new ViewModelProvider(this, new AccountViewModelFactory(this)).get(AccountViewModel.class);
		binding = ActivityAccountBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		initWidgets();
		initObservers();
		buttonsFunctions();
		accountViewModel.getUserInfo();
	}

	private void initWidgets() {
		buttonLogoMain = binding.buttonLogoMain;
		editTextNameAccount = binding.editTextNameAccount;
		editTextEmailAccount = binding.editTextEmailAccount;
		editTextPasswordAccount = binding.editTextPasswordAccount;
		buttonSave = binding.buttonSave;
		buttonLogout = binding.buttonLogout;
	}

	private void initObservers() {
		accountViewModel.getUserData().observe(this, userDTO -> {
			if (userDTO != null) {
				editTextNameAccount.setText(userDTO.getName());
				editTextEmailAccount.setText(userDTO.getEmail());
			}
		});
	}

	private void buttonsFunctions() {
		buttonLogoMain.setOnClickListener(view -> {
			startActivity(new Intent(AccountActivity.this, MenuActivity.class));
		});

		buttonLogout.setOnClickListener(view -> {
			accountViewModel.userLogout();
			startActivity(new Intent(AccountActivity.this, AuthorizationActivity.class));
		});
	}
}