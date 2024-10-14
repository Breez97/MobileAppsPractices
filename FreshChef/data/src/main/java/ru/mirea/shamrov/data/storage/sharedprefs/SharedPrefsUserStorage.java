package ru.mirea.shamrov.data.storage.sharedprefs;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import ru.mirea.shamrov.data.storage.UserStorage;
import ru.mirea.shamrov.data.storage.models.User;

public class SharedPrefsUserStorage implements UserStorage {

	private static final String SHARED_PREFS_NAME = "shared_prefs_name";
	private static final String KEY_ID = "user_id";
	private static final String KEY_NAME = "user_name";
	private static final String KEY_EMAIL = "user_email";
	private static final String KEY_PASSWORD = "user_password";
	private static final String KEY_FAVORITE_DISHES = "user_favorite_dishes";
	private final SharedPreferences sharedPreferences;

	public SharedPrefsUserStorage(Context context) {
		sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
	}

	@Override
	public boolean saveNewUser(User user) {
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putInt(KEY_ID, user.getId());
		editor.putString(KEY_NAME, user.getName());
		editor.putString(KEY_EMAIL, user.getEmail());
		editor.putString(KEY_PASSWORD, user.getPassword());
		String favoriteDishes = user.getFavoriteDishes().stream()
				.map(String::valueOf)
				.collect(Collectors.joining(" "));
		editor.putString(KEY_FAVORITE_DISHES, favoriteDishes);
		editor.apply();
		return true;
	}

	@Override
	public User getCurrentUser() {
		int id = sharedPreferences.getInt(KEY_ID, 0);
		String name = sharedPreferences.getString(KEY_NAME, "");
		String email = sharedPreferences.getString(KEY_EMAIL, "");
		String password = sharedPreferences.getString(KEY_PASSWORD, "");
		List<Integer> favoriteDishes = new ArrayList<>();
		String favoriteDishesString = sharedPreferences.getString(KEY_FAVORITE_DISHES, "");
		if (!favoriteDishesString.isEmpty())
			favoriteDishes = Arrays.stream(favoriteDishesString.split(" "))
					.map(Integer::parseInt)
					.collect(Collectors.toList());
		return new User(id, name, email, password, favoriteDishes);
	}
}
