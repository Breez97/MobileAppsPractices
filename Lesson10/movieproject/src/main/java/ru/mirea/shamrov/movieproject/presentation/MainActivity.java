package ru.mirea.shamrov.movieproject.presentation;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ru.mirea.shamrov.data.repository.MovieRepositoryImpl;
import ru.mirea.shamrov.data.storage.MovieStorage;
import ru.mirea.shamrov.data.storage.sharedprefs.SharedPrefMovieStorage;
import ru.mirea.shamrov.domain.models.Movie;
import ru.mirea.shamrov.domain.repository.MovieRepository;
import ru.mirea.shamrov.domain.usecases.GetFavoriteFilmUseCase;
import ru.mirea.shamrov.domain.usecases.SaveFilmToFavoriteUseCase;
import ru.mirea.shamrov.movieproject.R;

public class MainActivity extends AppCompatActivity {

	private SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		EdgeToEdge.enable(this);
		setContentView(R.layout.activity_main);
		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
			Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
			return insets;
		});

		EditText editText = findViewById(R.id.editTextText);
		TextView textView = findViewById(R.id.textView);

		MovieStorage sharedPrefMovieStorage = new SharedPrefMovieStorage(this);
		MovieRepository movieRepository = new MovieRepositoryImpl(sharedPrefMovieStorage);

		findViewById(R.id.buttonSaveMovie).setOnClickListener(view -> {
			Boolean result = new SaveFilmToFavoriteUseCase(movieRepository)
					.execute(new Movie(2, editText.getText().toString()));
			textView.setText(String.format("Save result %s", result));
		});

		findViewById(R.id.buttonGetMovie).setOnClickListener(view -> {
			Movie movie = new GetFavoriteFilmUseCase(movieRepository).execute();
			textView.setText(String.format("Save result %s", movie.getName()));
		});
	}

}