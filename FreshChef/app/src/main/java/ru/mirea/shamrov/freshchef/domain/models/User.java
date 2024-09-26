package ru.mirea.shamrov.freshchef.domain.models;

import java.util.List;

public class User {

	private Integer id;
	private String name;
	private List<Integer> favoriteDishes;

	public User(Integer id, String name, List<Integer> favoriteDishes) {
		this.id = id;
		this.name = name;
		this.favoriteDishes = favoriteDishes;
	}

	public String getName() {
		return name;
	}

	public List<Integer> getFavoriteDishes() {
		return favoriteDishes;
	}

	@Override
	public String toString() {
		return "id: " + id +
				"\nName: " + name;
	}

}
