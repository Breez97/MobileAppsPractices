package ru.mirea.shamrovio.workmanager;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		WorkRequest _uploadWorkRequest = new OneTimeWorkRequest.Builder(UploadWorker.class).build();
		WorkManager.getInstance(this).enqueue(_uploadWorkRequest);

		Constraints _constraints = new Constraints.Builder()
				.setRequiredNetworkType(NetworkType.UNMETERED)
				.setRequiresCharging(true)
				.build();

		_uploadWorkRequest = new OneTimeWorkRequest.Builder(UploadWorker.class)
				.setConstraints(_constraints)
				.build();
	}
}