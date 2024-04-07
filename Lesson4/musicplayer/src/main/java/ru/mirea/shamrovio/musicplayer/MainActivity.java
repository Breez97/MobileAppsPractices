package ru.mirea.shamrovio.musicplayer;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.shamrovio.musicplayer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
	private ActivityMainBinding binding;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		binding = ActivityMainBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());

		binding.titleOfAlbum.setText("Album \"Tickets To My Downfall\"");
		binding.titleOfTrack.setText("bloody valentine");
		binding.artistName.setText("Machine Gun Kelly");
		binding.currentTimeOfTrack.setText("1:25");
		binding.totalTimeOfTrack.setText("3:32");

		binding.pauseTrackButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d(MainActivity.class.getSimpleName(), "You paused the track");
			}
		});
	}
}