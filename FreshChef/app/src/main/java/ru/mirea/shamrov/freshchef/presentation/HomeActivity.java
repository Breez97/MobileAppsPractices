package ru.mirea.shamrov.freshchef.presentation;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import ru.mirea.shamrov.data.repository.DishDatabaseRepositoryImpl;
import ru.mirea.shamrov.data.roomdatabase.DishDatabaseStorage;
import ru.mirea.shamrov.data.roomdatabase.databasestorage.DishRoomDatabaseStorage;
import ru.mirea.shamrov.domain.models.DishDatabaseDTO;
import ru.mirea.shamrov.domain.repository.DishDatabaseRepository;
import ru.mirea.shamrov.domain.usecases.roomdatabase.AddNewDishDatabaseUseCase;
import ru.mirea.shamrov.domain.usecases.roomdatabase.DeleteDishDatabaseUseCase;
import ru.mirea.shamrov.domain.usecases.roomdatabase.GetAllDishesDatabaseUseCase;
import ru.mirea.shamrov.domain.utils.DatabaseCallback;
import ru.mirea.shamrov.freshchef.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

	private ActivityHomeBinding binding;

	private TextView textViewAllDishes;
	private EditText editTextDishTitle;
	private EditText editTextDishPrice;
	private Button buttonAddNewDish;
	private Button buttonDeleteDish;
	private Button buttonGetAllDishesDatabase;

	private AddNewDishDatabaseUseCase addNewDishDatabaseUseCase;
	private DeleteDishDatabaseUseCase deleteDishDatabaseUseCase;
	private GetAllDishesDatabaseUseCase getAllDishesDatabaseUseCase;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = ActivityHomeBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		initUseCases();
		initWidgets();
		buttonsFunctions();
	}

	private void initUseCases() {
		DishDatabaseStorage dishDatabaseStorage = new DishRoomDatabaseStorage(this);
		DishDatabaseRepository dishDatabaseRepository = new DishDatabaseRepositoryImpl(dishDatabaseStorage);
		addNewDishDatabaseUseCase = new AddNewDishDatabaseUseCase(dishDatabaseRepository);
		deleteDishDatabaseUseCase = new DeleteDishDatabaseUseCase(dishDatabaseRepository);
		getAllDishesDatabaseUseCase = new GetAllDishesDatabaseUseCase(dishDatabaseRepository);
	}

	private void initWidgets() {
		textViewAllDishes = binding.textViewAllDishes;
		editTextDishTitle = binding.editTextDishTitle;
		editTextDishPrice = binding.editTextDishPrice;
		buttonAddNewDish = binding.buttonAddNewDish;
		buttonDeleteDish = binding.buttonDeleteDish;
		buttonGetAllDishesDatabase = binding.buttonGetAllDishesDatabase;
	}

	private void buttonsFunctions() {
		buttonAddNewDish.setOnClickListener(view -> {
			String title = editTextDishTitle.getText().toString();
			Double price = Double.parseDouble(editTextDishPrice.getText().toString());
			textViewAllDishes.setText("New dish with title " + title + " and price " + price + " added");
			addNewDishDatabaseUseCase.execute(new DishDatabaseDTO(title, price));
		});

		buttonDeleteDish.setOnClickListener(view -> {
			String title = editTextDishTitle.getText().toString();
			if (!title.isEmpty()) {
				deleteDishDatabaseUseCase.execute(title);
			}
		});

		buttonGetAllDishesDatabase.setOnClickListener(view -> {
			getAllDishesDatabaseUseCase.execute(new DatabaseCallback<List<DishDatabaseDTO>>() {
				@Override
				public void onSuccess(List<DishDatabaseDTO> allDishes) {
					StringBuilder resultString = new StringBuilder();
					if (!allDishes.isEmpty()) {
						resultString.append("List of All Dishes:\n");
						for (DishDatabaseDTO currentDish : allDishes) {
							resultString.append("-------------------------------------------------------\n")
									.append(currentDish.toString())
									.append("\n");
						}
					} else {
						resultString.append("No dish added");
					}
					textViewAllDishes.setText(resultString.toString());
				}
				@Override
				public void onError(Exception e) {
					e.printStackTrace();
				}
			});
		});

	}

}