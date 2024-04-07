package ru.mirea.shamrovio.looper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.util.Locale;

public class MyLooper extends Thread {
	public Handler _mHandler;
	private Handler _mainHandler;
	public MyLooper(Handler _mainThreadHandler) {
		_mainHandler = _mainThreadHandler;
	}

	public void run() {
		Log.d("MyLooper", "run");
		Looper.prepare();
		_mHandler = new Handler(Looper.myLooper()) {
			public void handleMessage(Message _msg) {
				String _data = _msg.getData().getString("KEY");
				int _age = _msg.getData().getInt("age");
				Log.d("MyLooper get message: ", _data);

				int _count = _data.length();
				Message _message = new Message();
				Bundle _bundle = new Bundle();
				_bundle.putString("result", String.format(Locale.getDefault(), "Полученная строка: %s\nДлина строки: %d", _data, _count));
				_message.setData(_bundle);

//				_mainHandler.sendMessage(_message);

				_mainHandler.postDelayed(new Runnable() {
					@Override
					public void run() {
						_mainHandler.sendMessage(_message);
					}
				}, _age);
			}
		};
		Looper.loop();
	}
}
