package ru.mirea.shamrov.fragmentmanagerapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HeaderFragment extends Fragment {

	private ShareViewModel viewModel;

	private final Map<String, String> countriesList = new HashMap<>();

	private static final String ARG_PARAM1 = "param1";

	private String mParam1;

	public HeaderFragment() {

	}

	public static HeaderFragment newInstance(String param1) {
		HeaderFragment fragment = new HeaderFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mParam1 = getArguments().getString(ARG_PARAM1);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_header, container, false);
		viewModel = new ViewModelProvider(requireActivity()).get(ShareViewModel.class);
		fillCountryList();
		ListView countriesListView = view.findViewById(R.id.countriesList);
		List<String> countryNames = new ArrayList<>(countriesList.keySet());
		ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, countryNames);
		countriesListView.setAdapter(arrayAdapter);
		countriesListView.setOnItemClickListener(((parent, view1, position, id) -> {
			String selectedCountry = countryNames.get(position);
			viewModel.setSelectedItem(countriesList.get(selectedCountry));
		}));
		return view;
	}

	private void fillCountryList() {
		countriesList.put("Франция", "Европейская страна, известная своей богатой культурой, Эйфелевой башней и изысканной кухней.");
		countriesList.put("Япония", "Островное государство в Восточной Азии, знаменитое своими технологиями, традиционным искусством и сакурой.");
		countriesList.put("Бразилия", "Крупнейшая страна Южной Америки, известная Амазонкой, карнавалами и яркой культурой.");
		countriesList.put("Австралия", "Страна и континент, известный уникальной природой, Большим Барьерным рифом и захватывающими пейзажами.");
		countriesList.put("Египет", "Североафриканская страна, знаменитая своей древней цивилизацией, пирамидами и рекой Нил.");
	}

}