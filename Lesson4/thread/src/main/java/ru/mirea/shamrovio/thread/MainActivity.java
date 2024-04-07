package ru.mirea.shamrovio.thread;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

import ru.mirea.shamrovio.thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
	private ActivityMainBinding binding;
	private int _counter = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		binding = ActivityMainBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());

		Thread mainThread = Thread.currentThread();
		binding.infoTextView.setText("Имя текущего потока: " + mainThread.getName());
		mainThread.setName("Мой номер группы: 10, номер по списку: 31, мой любимый фильм: Бегущий по лезвию");
		binding.infoTextView.append("\nНовое имя потока: " + mainThread.getName());
		Log.d(MainActivity.class.getSimpleName(), "Stack: " + Arrays.toString(mainThread.getStackTrace()));

		binding.button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				long _endTime = System.currentTimeMillis() + 20 * 10000;

				while(System.currentTimeMillis() < _endTime) {
					synchronized (this) {
						try {
							wait(_endTime - System.currentTimeMillis());
						} catch (Exception e) {
							throw new RuntimeException(e);
						}
					}
				}
			}
		});

		Log.d(MainActivity.class.getSimpleName(), "Group: " + mainThread.getThreadGroup());

		binding.buttonMultiple.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						int _numberThread = _counter++;
						Log.d("ThreadProject", String.format("Запущен поток № %d студентом группы" +
								"№ %s номер по списку № %d", _numberThread, "БСБО-10-21", 31));

						long _endTime = System.currentTimeMillis() + 20 * 10000;

						while(System.currentTimeMillis() < _endTime) {
							synchronized (this) {
								try {
									wait(_endTime - System.currentTimeMillis());
									Log.d(MainActivity.class.getSimpleName(), "Endtime: " + _endTime);
								} catch (Exception e) {
									throw new RuntimeException(e);
								}
							}
							Log.d("ThreadProject", "Выполнен поток № " + _numberThread);
						}
					}
				}).start();
			}
		});
	}
}