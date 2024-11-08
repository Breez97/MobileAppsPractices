package ru.mirea.shamrov.freshchef.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.shamrov.freshchef.databinding.ActivityMainBinding;
import ru.mirea.shamrov.freshchef.presentation.viewmodel.MainViewModel;
import ru.mirea.shamrov.freshchef.presentation.viewmodel.MainViewModelFactory;

public class MainActivity extends AppCompatActivity {

	private ActivityMainBinding binding;
	private MainViewModel homeViewModel;

	private TextView textViewAllDishes;
	private EditText editTextDishTitle;
	private EditText editTextDishPrice;
	private Button buttonAddNewDish;
	private Button buttonDeleteDish;
	private Button buttonGetDishFromApi;
	private Button buttonMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		homeViewModel = new ViewModelProvider(this, new MainViewModelFactory(this)).get(MainViewModel.class);
		binding = ActivityMainBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		initWidgets();
		initObservers();
		initButtons();
		homeViewModel.getAllDishes();
	}

	private void initWidgets() {
		textViewAllDishes = binding.textViewAllDishes;
		editTextDishTitle = binding.editTextDishTitle;
		editTextDishPrice = binding.editTextDishPrice;
		buttonAddNewDish = binding.buttonAddNewDish;
		buttonDeleteDish = binding.buttonDeleteDish;
		buttonGetDishFromApi = binding.buttonGetDishFromApi;
		buttonMenu = binding.buttonMenu;
	}

	private void initObservers() {
		homeViewModel.getFormattedData().observe(this, text ->
				textViewAllDishes.setText(text)
		);
		homeViewModel.getApiDish().observe(this, apiDish -> {
			if (apiDish != null) {
				editTextDishTitle.setText(apiDish.getTitle());
				editTextDishPrice.setText(apiDish.getPrice().toString());
			}
		});
		homeViewModel.getErrorMessage().observe(this, error ->
				Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
		);
		homeViewModel.getSuccessMessage().observe(this, message ->
				Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
		);
	}

	private void initButtons() {
		buttonAddNewDish.setOnClickListener(view -> {
			try {
				String title = editTextDishTitle.getText().toString();
				Double price = Double.parseDouble(editTextDishPrice.getText().toString());
				homeViewModel.addNewDish(title, price);
			} catch (NumberFormatException e) {
				Toast.makeText(this, "Invalid price format", Toast.LENGTH_SHORT).show();
			}
		});

		buttonDeleteDish.setOnClickListener(view -> {
			String title = editTextDishTitle.getText().toString();
			homeViewModel.deleteDish(title);
		});

		buttonGetDishFromApi.setOnClickListener(view -> homeViewModel.getDishFromApi());

		buttonMenu.setOnClickListener(view ->
				startActivity(new Intent(MainActivity.this, MenuActivity.class))
		);
	}
}