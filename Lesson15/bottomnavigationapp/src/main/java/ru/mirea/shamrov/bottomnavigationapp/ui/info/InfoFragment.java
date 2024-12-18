package ru.mirea.shamrov.bottomnavigationapp.ui.info;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.mirea.shamrov.bottomnavigationapp.databinding.FragmentInfoBinding;

public class InfoFragment extends Fragment {

	private FragmentInfoBinding binding;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
							 @Nullable ViewGroup container,
							 @Nullable Bundle savedInstanceState) {
		InfoViewModel infoViewModel =
				new ViewModelProvider(this).get(InfoViewModel.class);

		binding = FragmentInfoBinding.inflate(inflater, container, false);
		View root = binding.getRoot();

		final TextView textView = binding.textInfo;
		infoViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
		return root;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		binding = null;
	}

}