package ru.mirea.shamrov.retrofitapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class TodoRecyclerViewAdapter extends RecyclerView.Adapter<TodoViewHolder> {

	private Context context;
	private final List<Todo> todos;
	private final OnCheckBoxClickListener listener;
	private final List<String> imageUrls;

	public TodoRecyclerViewAdapter(List<Todo> todos, List<String> imageUrls, OnCheckBoxClickListener listener) {
		this.todos = todos;
		this.imageUrls = imageUrls;
		this.listener = listener;
	}

	@NonNull
	@Override
	public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		context = parent.getContext();
		View recyclerView = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
		return new TodoViewHolder(recyclerView);
	}

	@Override
	public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
		Todo todo = todos.get(position);
		holder.getTextViewTitle().setText(todo.getTitle());
		holder.getCheckBoxCompleted().setChecked(todo.getCompleted());
		Picasso.get()
				.load(imageUrls.get(position))
				.placeholder(R.drawable.ic_launcher_foreground)
				.error(R.drawable.ic_launcher_foreground)
				.resize(100, 100)
				.centerCrop()
				.into(holder.getImageView());
		holder.getCheckBoxCompleted().setOnCheckedChangeListener((buttonView, isChecked) -> {
			listener.onCheckBoxClicked(position);
		});
	}


	@Override
	public int getItemCount() {
		return todos.size();
	}

	public void updateTodo(int position, String newTitle, String newImage) {
		todos.get(position).setTitle(newTitle);
		todos.get(position).setCompleted(false);
		imageUrls.set(position, newImage);
		notifyItemChanged(position);
	}

}
