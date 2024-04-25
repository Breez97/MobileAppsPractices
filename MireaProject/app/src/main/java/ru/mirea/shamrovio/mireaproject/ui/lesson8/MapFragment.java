package ru.mirea.shamrovio.mireaproject.ui.lesson8;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.mirea.shamrovio.mireaproject.R;
import ru.mirea.shamrovio.mireaproject.databinding.FragmentMapBinding;

public class MapFragment extends Fragment {
	private FragmentMapBinding binding;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		binding = FragmentMapBinding.inflate(inflater, container, false);

		setButtons();

		return binding.getRoot();
	}

	private void setButtons() {
		binding.buttonShowMainCorpus.setOnClickListener(v -> startMapActivity(
				"МИРЭА Главный корпус",
				"Москва, просп. Вернадского, 78",
				55.670010,
				37.480426));

		binding.buttonShowStromynkaCorpus.setOnClickListener(v -> startMapActivity(
				"МИРЭА Корпус на Стромынке",
				"Москва, улица Стромынка, 20",
				55.794137,
				37.701500));

		binding.buttonShowTheByk.setOnClickListener(v -> startMapActivity(
				"The Бык",
				"Москва, улица Арбат, 44с1",
				55.748033,
				37.586563));

		binding.buttonShowParkGorkogo.setOnClickListener(v -> startMapActivity(
				"Парк Горького" ,
				"Москва, Ленинская площадь",
				55.730670,
				37.603089));
	}

	private void startMapActivity(String title, String description, double latitude, double longitude) {
		Intent intent = new Intent(getActivity(), MapActivity.class);
		intent.putExtra("title", title);
		intent.putExtra("description", description);
		intent.putExtra("latitude", latitude);
		intent.putExtra("longitude", longitude);
		startActivity(intent);
	}
}