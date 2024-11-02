package ru.mirea.shamrov.recyclerviewapp_task;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EventViewHolder extends RecyclerView.ViewHolder {

	private final ImageView eventImageView;
	private final TextView eventTitleView;
	private final TextView eventDateView;
	private final TextView eventDescriptionView;

	public EventViewHolder(@NonNull View itemView) {
		super(itemView);
		this.eventImageView = itemView.findViewById(R.id.imageView);
		this.eventTitleView = itemView.findViewById(R.id.textViewTitle);
		this.eventDateView = itemView.findViewById(R.id.textViewDate);
		this.eventDescriptionView = itemView.findViewById(R.id.textViewDescription);
	}

	public ImageView getEventImageView() {
		return eventImageView;
	}

	public TextView getEventTitleView() {
		return eventTitleView;
	}

	public TextView getEventDateView() {
		return eventDateView;
	}

	public TextView getEventDescriptionView() {
		return eventDescriptionView;
	}

}
