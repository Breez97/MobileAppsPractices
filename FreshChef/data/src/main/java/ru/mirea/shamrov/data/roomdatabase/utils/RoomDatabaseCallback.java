package ru.mirea.shamrov.data.roomdatabase.utils;

public interface RoomDatabaseCallback<T> {

	void onSuccess(T result);
	void onError(Exception e);

}
