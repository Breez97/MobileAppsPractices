package ru.mirea.shamrovio.mireaproject.ui.lesson8;

import android.app.Application;

import com.yandex.mapkit.MapKitFactory;

public class App extends Application {
	private final String MAPKIT_API_KEY = "4f78a4a3-cfa4-48a7-8901-a5aaedddfafe";

	@Override
	public void onCreate() {
		super.onCreate();
		MapKitFactory.setApiKey(MAPKIT_API_KEY);
	}
}
