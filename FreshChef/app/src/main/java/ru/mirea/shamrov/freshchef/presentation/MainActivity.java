package ru.mirea.shamrov.freshchef.presentation;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import ru.mirea.shamrov.freshchef.data.repository.DishRepositoryImpl;
import ru.mirea.shamrov.freshchef.data.repository.UserRepositoryImpl;
import ru.mirea.shamrov.freshchef.data.storage.DishStorage;
import ru.mirea.shamrov.freshchef.data.storage.UserStorage;
import ru.mirea.shamrov.freshchef.data.storage.internalstorage.InternalDishStorage;
import ru.mirea.shamrov.freshchef.data.storage.internalstorage.InternalUserStorage;
import ru.mirea.shamrov.freshchef.databinding.ActivityMainBinding;
import ru.mirea.shamrov.freshchef.domain.models.DishDTO;
import ru.mirea.shamrov.freshchef.domain.models.UserDTO;
import ru.mirea.shamrov.freshchef.domain.repository.DishRepository;
import ru.mirea.shamrov.freshchef.domain.repository.UserRepository;
import ru.mirea.shamrov.freshchef.domain.usecases.AddNewDishUseCase;
import ru.mirea.shamrov.freshchef.domain.usecases.GetAllDishesUseCase;
import ru.mirea.shamrov.freshchef.domain.usecases.GetAllUsersUseCase;
import ru.mirea.shamrov.freshchef.domain.usecases.GetDishesByIdUseCase;
import ru.mirea.shamrov.freshchef.domain.usecases.GetDishesByTitleUseCase;
import ru.mirea.shamrov.freshchef.domain.usecases.GetUserFavoriteDishesUseCase;

public class MainActivity extends AppCompatActivity {

	private ActivityMainBinding binding;

	private DishRepository dishRepository;
	private UserRepository userRepository;

	private TextView textViewInfoText;
	private EditText editTextFindDishByTitle;
	private EditText editTextAddTitle;
	private EditText editTextAddPrice;
	private Button buttonGetAllDishes;
	private Button buttonFindDishByTitle;
	private Button buttonAddNewDish;

	private EditText editTextFindUsersFavoriteDishes;
	private Button buttonGetAllUsers;
	private Button buttonFindUserFavoriteDishes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = ActivityMainBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		initWidgets();
		initRepository();
		buttonsFunctions();
	}

	private void initWidgets() {
		textViewInfoText = binding.textViewInfoText;
		editTextFindDishByTitle = binding.editTextFindDishByTitle;
		editTextAddTitle = binding.editTextAddTitle;
		editTextAddPrice = binding.editTextAddPrice;
		buttonGetAllDishes = binding.buttonGetAllDishes;
		buttonFindDishByTitle = binding.buttonFindDishByTitle;
		buttonAddNewDish = binding.buttonAddNewDish;
		buttonGetAllUsers = binding.buttonGetAllUsers;
		editTextFindUsersFavoriteDishes = binding.editTextFindUsersFavoriteDishes;
		buttonFindUserFavoriteDishes = binding.buttonFindUserFavoriteDishes;
	}

	private void initRepository() {
		DishStorage dishStorage = new InternalDishStorage();
		dishRepository = new DishRepositoryImpl(dishStorage);
		UserStorage userStorage = new InternalUserStorage();
		userRepository = new UserRepositoryImpl(userStorage);
	}

	private void buttonsFunctions() {
		buttonGetAllDishes.setOnClickListener(view -> {
			List<DishDTO> resultAllDishes = new GetAllDishesUseCase(dishRepository).execute();
			StringBuilder resultString = new StringBuilder();
			if (!resultAllDishes.isEmpty()) {
				resultString.append("List of All Dishes:\n");
				for (DishDTO currentDish : resultAllDishes) {
					resultString.append("-------------------------------------------------------\n")
							.append(currentDish.toString())
							.append("\n");
				}
			} else {
				resultString.append("No dish added");
			}
			textViewInfoText.setText(resultString);
		});

		buttonFindDishByTitle.setOnClickListener(view -> {
			List<DishDTO> foundDishes = new GetDishesByTitleUseCase(dishRepository)
					.execute(editTextFindDishByTitle.getText().toString());
			StringBuilder resultString = new StringBuilder();
			if (!foundDishes.isEmpty()) {
				resultString.append("List of Found Dishes:\n");
				for (DishDTO currentDish : foundDishes) {
					resultString.append("-------------------------------------------------------\n")
							.append(currentDish.toString())
							.append("\n\n");
				}
			} else {
				resultString.append("No dishes found");
			}
			textViewInfoText.setText(resultString);
		});

		buttonAddNewDish.setOnClickListener(view -> {
			String priceText = editTextAddPrice.getText().toString();
			try {
				Double price = Double.parseDouble(priceText);
				boolean resultAdd = new AddNewDishUseCase(dishRepository)
						.execute(new DishDTO(0, editTextAddTitle.getText().toString(), price));
				if (resultAdd) {
					textViewInfoText.setText("Dish added successfully");
				} else {
					textViewInfoText.setText("Current dish already exist");
				}
			} catch (NumberFormatException e) {
				textViewInfoText.setText("Incorrect price input");
			}
		});

		buttonGetAllUsers.setOnClickListener(view -> {
			List<UserDTO> resultAllUsers = new GetAllUsersUseCase(userRepository).execute();
			StringBuilder resultString = new StringBuilder();
			if (!resultAllUsers.isEmpty()) {
				resultString.append("List of All Users:\n");
				for (UserDTO currentUser : resultAllUsers) {
					resultString.append("-------------------------------------------------------\n")
							.append(currentUser.toString())
							.append("\n");
				}
			} else {
				resultString.append("No users added");
			}
			textViewInfoText.setText(resultString);
		});

		buttonFindUserFavoriteDishes.setOnClickListener(view -> {
			List<Integer> idsFavouriteDishes = new GetUserFavoriteDishesUseCase(userRepository)
					.execute(editTextFindUsersFavoriteDishes.getText().toString());
			StringBuilder resultString = new StringBuilder();
			if (idsFavouriteDishes.contains(-1)) {
				resultString.append("No user with such name found");
			} else {
				if (idsFavouriteDishes.isEmpty()) {
					resultString.append("User didn't add any dish to his favorite");
				} else {
					List<DishDTO> resultFavoriteDishes = new GetDishesByIdUseCase(dishRepository)
							.execute(idsFavouriteDishes);
					resultString.append("List of Favorite Dishes:\n");
					for (DishDTO currentDish : resultFavoriteDishes) {
						resultString.append("-------------------------------------------------------\n")
								.append(currentDish.toString())
								.append("\n\n");
					}
				}
			}
			textViewInfoText.setText(resultString);
		});
	}

}
