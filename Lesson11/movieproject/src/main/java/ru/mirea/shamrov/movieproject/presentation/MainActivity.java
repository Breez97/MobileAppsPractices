package ru.mirea.shamrov.movieproject.presentation;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.shamrov.domain.models.Movie;
import ru.mirea.shamrov.movieproject.R;

public class MainActivity extends AppCompatActivity {

	private SharedPreferences sharedPreferences;
	private MainViewModel mainViewModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		EdgeToEdge.enable(this);
		setContentView(R.layout.activity_main);

		Log.d(MainActivity.class.getSimpleName().toString(), "MainActivity created");

		mainViewModel = new ViewModelProvider(this, new ViewModelFactory(this)).get(MainViewModel.class);

		EditText editText = findViewById(R.id.editTextText);
		TextView textView = findViewById(R.id.textView);

		mainViewModel.getFavoriteMovie().observe(this, new Observer<String>() {
			@Override
			public void onChanged(String s) {
				textView.setText(s);
			}
		});

		findViewById(R.id.buttonSaveMovie).setOnClickListener(view -> {
			mainViewModel.setText(new Movie(2, editText.getText().toString()));
		});

		findViewById(R.id.buttonGetMovie).setOnClickListener(view -> {
			mainViewModel.getText();
		});
	}

}