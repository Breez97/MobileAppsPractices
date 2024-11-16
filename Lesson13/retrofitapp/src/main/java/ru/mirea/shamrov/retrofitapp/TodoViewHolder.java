package ru.mirea.shamrov.retrofitapp;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TodoViewHolder extends RecyclerView.ViewHolder {

	private final TextView textViewTitle;
	private final CheckBox checkBoxCompleted;
	private final ImageView imageView;

	public TodoViewHolder(@NonNull View itemView) {
		super(itemView);
		textViewTitle = itemView.findViewById(R.id.textViewTitle);
		checkBoxCompleted = itemView.findViewById(R.id.checkBoxCompleted);
		imageView = itemView.findViewById(R.id.imageView);
	}

	public TextView getTextViewTitle() {
		return textViewTitle;
	}

	public CheckBox getCheckBoxCompleted() {
		return checkBoxCompleted;
	}

	public ImageView getImageView() {
		return imageView;
	}

}
