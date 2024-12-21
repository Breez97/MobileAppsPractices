package ru.mirea.shamrov.retrofitapp;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements OnCheckBoxClickListener {

	public static final String TAG = "MainActivity";
	public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
	private ApiService apiService;
	private RecyclerView recyclerView;
	private TodoRecyclerViewAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initLayout();
		fillData();
	}

	private void initLayout() {
		LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
		recyclerView = findViewById(R.id.recyclerView);
		recyclerView.setLayoutManager(layoutManager);
	}

	private void fillData() {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(BASE_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		apiService = retrofit.create(ApiService.class);
		Call<List<Todo>> call = apiService.getTodos();
		call.enqueue(new Callback<>() {
			@Override
			public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
				if (response.isSuccessful() && response.body() != null) {
					List<Todo> todos = response.body();
					List<String> imageUrls = getImageUrls(todos.size());
					adapter = new TodoRecyclerViewAdapter(todos, imageUrls, MainActivity.this);
					recyclerView.setAdapter(adapter);
				} else {
					Log.e(TAG, "onResponse: " + response.code());
				}
			}

			@Override
			public void onFailure(Call<List<Todo>> call, Throwable throwable) {
				Log.e(TAG, "onFailure: " + throwable.getMessage());
			}
		});
	}

	private List<String> getImageUrls(int count) {
		List<String> imageUrls = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			imageUrls.add("https://picsum.photos/200/200?random=" + (i + 1));
		}
		return imageUrls;
	}

	@Override
	public void onCheckBoxClicked(int position) {
		Call<Todo> call = apiService.getCurrentTodo(position + 1);
		call.enqueue(new Callback<Todo>() {
			@Override
			public void onResponse(Call<Todo> call, Response<Todo> response) {
				if (response.isSuccessful() && response.body() != null) {
					Todo currentTodo = response.body();
					updateTodo(currentTodo);
				}
			}

			@Override
			public void onFailure(Call<Todo> call, Throwable throwable) {
				Log.e(TAG, "onFailure: " + throwable.getMessage());
			}
		});
	}

	private void updateTodo(Todo todo) {
		todo.setCompleted(!todo.getCompleted());
		Call<Todo> call = apiService.updateTodo(todo.getId(), todo);
		call.enqueue(new Callback<Todo>() {
			@Override
			public void onResponse(Call<Todo> call, Response<Todo> response) {
				if (response.isSuccessful() && response.body() != null) {
					Log.d(TAG, "onSuccess updateTodo Status: " + response.code());
				} else {
					Log.e(TAG, "Update failed: " + response.code());
				}
			}

			@Override
			public void onFailure(Call<Todo> call, Throwable throwable) {
				Log.e(TAG, "onFailure updateTodo: " + throwable.getMessage());
			}
		});
	}
}