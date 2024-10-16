package ru.mirea.shamrov.data.roomdatabase.databasestorage;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ru.mirea.shamrov.data.roomdatabase.DishDatabaseStorage;
import ru.mirea.shamrov.data.roomdatabase.model.DishDatabase;
import ru.mirea.shamrov.data.roomdatabase.utils.RoomDatabaseCallback;

public class DishRoomDatabaseStorage implements DishDatabaseStorage {

	private ru.mirea.shamrov.data.roomdatabase.DishDatabase dishDatabase;
	private Context context;
	private final ExecutorService databaseExecutor = Executors.newSingleThreadExecutor();
	private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());

	public DishRoomDatabaseStorage(Context context) {
		this.context = context;
		configureDatabase();
	}

	private void configureDatabase() {
		RoomDatabase.Callback customCallback = new RoomDatabase.Callback() {
			@Override
			public void onCreate(SupportSQLiteDatabase db) {
				super.onCreate(db);
			}

			@Override
			public void onOpen(@NonNull SupportSQLiteDatabase db) {
				super.onOpen(db);
			}
		};

		dishDatabase = Room.databaseBuilder(context, ru.mirea.shamrov.data.roomdatabase.DishDatabase.class, "DishDB")
				.addCallback(customCallback)
				.build();
	}

	@Override
	public void addNewDish(DishDatabase dish) {
		databaseExecutor.execute(() -> dishDatabase.getDishDAO().addDish(dish));
	}

	@Override
	public void deleteDish(String title) {
		databaseExecutor.execute(() -> dishDatabase.getDishDAO().deleteDish(title));
	}

	@Override
	public void getAllDishes(RoomDatabaseCallback<List<DishDatabase>> callback) {
		databaseExecutor.execute(() -> {
			List<DishDatabase> dishes = dishDatabase.getDishDAO().getAllDishes();
			mainThreadHandler.post(() -> callback.onSuccess(dishes));
		});
	}
}
