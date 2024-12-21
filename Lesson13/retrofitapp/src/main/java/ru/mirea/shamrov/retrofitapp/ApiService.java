package ru.mirea.shamrov.retrofitapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {

	@GET("todos")
	Call<List<Todo>> getTodos();

	@GET("/todos/{randomId}")
	Call<Todo> getRandomTodo(@Path("randomId") Integer randomId);

	@GET("/todos/{id}")
	Call<Todo> getCurrentTodo(@Path("id") Integer id);

	@PUT("/todos/{id}")
	Call<Todo> updateTodo(@Path("id") Integer id, @Body Todo todo);

}
