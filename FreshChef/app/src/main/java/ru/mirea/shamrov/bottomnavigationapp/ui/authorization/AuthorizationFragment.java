package ru.mirea.shamrov.bottomnavigationapp.ui.authorization;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import ru.mirea.shamrov.bottomnavigationapp.R;
import ru.mirea.shamrov.bottomnavigationapp.databinding.FragmentAuthorizationBinding;

public class AuthorizationFragment extends Fragment {

	private FragmentAuthorizationBinding binding;
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

	public AuthorizationFragment() {

	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
							 @Nullable ViewGroup container,
							 @Nullable Bundle savedInstanceState) {
		binding = FragmentAuthorizationBinding.inflate(inflater, container, false);
		authorizationViewModel = new ViewModelProvider(this, new AuthorizationViewModelFactory(requireContext()))
				.get(AuthorizationViewModel.class);
		authorizationViewModel.checkAuthorization();
		authorizationViewModel.isAuthorized().observe(getViewLifecycleOwner(), isAuthorized -> {
			if (isAuthorized) {
				NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
				navController.navigate(R.id.action_authorizationFragment_to_profileFragment);
			}
		});
		authorizationViewModel.getErrorMessage().observe(getViewLifecycleOwner(), errorMessage -> {
			if (errorMessage != null) {
				Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show();
			}
		});
		initWidgets();
		initButtons();
		return binding.getRoot();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		binding = null;
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

	private void initButtons() {
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
				Toast.makeText(requireContext(), "Name can't be empty", Toast.LENGTH_SHORT).show();
			} else if (checkFields(email, password)) {
				authorizationViewModel.register(name, email, password);
			}
		});
	}

	private boolean checkFields(String email, String password) {
		if (email.isBlank()) {
			Toast.makeText(requireContext(), "Email can't be empty", Toast.LENGTH_SHORT).show();
			return false;
		} else if (password.isBlank()) {
			Toast.makeText(requireContext(), "Password can't be empty", Toast.LENGTH_SHORT).show();
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