package ru.mirea.shamrov.retrofitapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

	@GET("todos")
	Call<List<Todo>> getTodos();

	@GET("/todos/{randomId}")
	Call<Todo> getRandomTodo(@Path("randomId") Integer randomId);

}
