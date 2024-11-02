package ru.mirea.shamrov.recyclerviewapp_task;

import androidx.annotation.NonNull;

import java.util.Date;

public class Event {

	private final String eventTitle;
	private final String eventDate;
	private final String eventDescription;
	private final String eventImage;

	public Event(String eventTitle, String eventDate, String eventDescription, String eventImage) {
		this.eventTitle = eventTitle;
		this.eventDate = eventDate;
		this.eventDescription = eventDescription;
		this.eventImage = eventImage;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public String getEventDate() {
		return eventDate;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public String getEventImage() {
		return eventImage;
	}

}
