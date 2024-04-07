package ru.mirea.shamrovio.looper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

import ru.mirea.shamrovio.looper.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
	private ActivityMainBinding binding;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		binding = ActivityMainBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());

		Handler _mainThreadHandler = new Handler(Looper.getMainLooper()) {
			@Override
			public void handleMessage(Message _msg) {
				Log.d(MainActivity.class.getSimpleName(), "Task execute. This is result: " +
						_msg.getData().getString("result"));
			}
		};

		MyLooper _myLooper = new MyLooper(_mainThreadHandler);
		_myLooper.start();

		binding.editTextMirea.setText("Мой номер по списку №31");
		binding.buttonMirea.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Message _msg = Message.obtain();
				Bundle _bundle = new Bundle();
				int _age = Integer.parseInt(binding.editTextAge.getText().toString());
				String _work = binding.editTextWork.getText().toString();

				String _result = String.format(Locale.getDefault(), "Возраст: %d, должность: %s", _age, _work);
				_bundle.putString("KEY", _result);
				_bundle.putInt("age", _age);
				_msg.setData(_bundle);
				_myLooper._mHandler.sendMessage(_msg);
			}
		});
	}
}