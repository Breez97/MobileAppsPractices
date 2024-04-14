package ru.mirea.shamrovio.lesson6;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

import ru.mirea.shamrovio.lesson6.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
	private ActivityMainBinding _binding;
	private SharedPreferences _sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		_binding = ActivityMainBinding.inflate(getLayoutInflater());
		setContentView(_binding.getRoot());

		_sharedPreferences = getSharedPreferences("mirea_settings",
				Context.MODE_PRIVATE);
		_binding.editTextGroupNumber.setText(_sharedPreferences
				.getString("GROUP", "Unknown"));
		_binding.editTextListNumber.setText(String.format(Locale.getDefault(), "%d",
				_sharedPreferences.getInt("NUMBER", 0)));
		_binding.editTextFilmTitle.setText(_sharedPreferences
				.getString("FILM_TITLE", "Unknown"));

		_binding.buttonSave.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				_sharedPreferences = getSharedPreferences("mirea_settings",
						Context.MODE_PRIVATE);
				SharedPreferences.Editor _editor = _sharedPreferences.edit();

				_editor.putString("GROUP", _binding.editTextGroupNumber.getText().toString());
				_editor.putInt("NUMBER", Integer.parseInt(_binding.editTextListNumber.getText().toString()));
				_editor.putString("FILM_TITLE", _binding.editTextFilmTitle.getText().toString());

				_editor.apply();
			}
		});
	}
}