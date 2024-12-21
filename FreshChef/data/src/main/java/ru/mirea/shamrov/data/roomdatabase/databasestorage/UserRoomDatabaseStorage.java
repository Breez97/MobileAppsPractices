package ru.mirea.shamrov.data.roomdatabase.databasestorage;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ru.mirea.shamrov.data.roomdatabase.UserDatabase;
import ru.mirea.shamrov.data.roomdatabase.UserDatabaseStorage;
import ru.mirea.shamrov.data.roomdatabase.utils.RoomDatabaseCallback;

public class UserRoomDatabaseStorage implements UserDatabaseStorage {

	private UserDatabase userDatabase;
	private Context context;
	private final ExecutorService databaseExecutor = Executors.newSingleThreadExecutor();

	private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());

	public UserRoomDatabaseStorage(Context context) {
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

		userDatabase = Room.databaseBuilder(context, UserDatabase.class, "UsersDB")
				.addCallback(customCallback)
				.build();
	}

	@Override
	public void addNewUser(ru.mirea.shamrov.data.roomdatabase.model.UserDatabase user) {
		databaseExecutor.execute(() -> userDatabase.getUserDAO().addUser(user));
	}

	@Override
	public void getCurrentUser(String email, RoomDatabaseCallback<ru.mirea.shamrov.data.roomdatabase.model.UserDatabase> callback) {
		databaseExecutor.execute(() -> {
			ru.mirea.shamrov.data.roomdatabase.model.UserDatabase user = userDatabase.getUserDAO().getUser(email);
			if (user != null) {
				mainThreadHandler.post(() -> callback.onSuccess(user));
			} else {
				mainThreadHandler.post(() -> callback.onError(new Exception("User not found")));
			}
		});
	}

	@Override
	public void updateUserInfoDatabase(ru.mirea.shamrov.data.roomdatabase.model.UserDatabase userDatabaseNew) {
		databaseExecutor.execute(() -> userDatabase.getUserDAO().insertOrUpdateUser(userDatabaseNew));
	}
}
