package ru.mirea.shamrov.freshchef.data.storage.models;

import java.time.LocalDate;
import java.util.List;

public class User {

	private Integer id;
	private String name;
	private List<Integer> favoriteDishes;
	private LocalDate localDate;

	public User(Integer id, String name, List<Integer> favoriteDishes, LocalDate localDate) {
		this.id = id;
		this.name = name;
		this.favoriteDishes = favoriteDishes;
		this.localDate = localDate;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Integer> getFavoriteDishes() {
		return favoriteDishes;
	}

	public LocalDate getLocalDate() {
		return localDate;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFavoriteDishes(List<Integer> favoriteDishes) {
		this.favoriteDishes = favoriteDishes;
	}

	public void setLocalDate(LocalDate localDate) {
		this.localDate = localDate;
	}

}
