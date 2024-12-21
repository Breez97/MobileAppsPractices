package ru.mirea.shamrov.bottomnavigationapp.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import ru.mirea.shamrov.bottomnavigationapp.R;
import ru.mirea.shamrov.bottomnavigationapp.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

	private FragmentProfileBinding binding;
	private ProfileViewModel profileViewModel;
	private TextView nameTextView;
	private TextView emailTextView;
	private Button buttonLogout;


	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
							 @Nullable ViewGroup container,
							 @Nullable Bundle savedInstanceState) {
		binding = FragmentProfileBinding.inflate(inflater, container, false);
		profileViewModel = new ViewModelProvider(this, new ProfileViewModelFactory(requireContext()))
				.get(ProfileViewModel.class);
		initWidgets();
		initButtons();
		initViewModel();
		profileViewModel.getUserInfo();
		return binding.getRoot();
	}


	@Override
	public void onDestroyView() {
		super.onDestroyView();
		binding = null;
	}

	private void initWidgets() {
		buttonLogout = binding.buttonLogout;
		nameTextView = binding.nameTextView;
		emailTextView = binding.emailTextView;
	}

	private void initButtons() {
		buttonLogout.setOnClickListener(v -> {
			profileViewModel.userLogout();
		});
	}

	private void initViewModel() {
		profileViewModel.checkAuthorization();
		profileViewModel.isAuthorized().observe(getViewLifecycleOwner(), isAuthorized -> {
			if (!isAuthorized) {
				NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
				navController.navigate(R.id.action_profileFragment_to_authorizationFragment);
			}
		});
		profileViewModel.getUserData().observe(getViewLifecycleOwner(), userDTO -> {
			if (userDTO != null) {
				nameTextView.setText(userDTO.getName());
				emailTextView.setText(userDTO.getEmail());
			}
		});
	}

}