package ru.mirea.shamrov.freshchef.domain.models;

import java.util.List;

public class UserDTO {

	private Integer id;
	private String name;
	private List<Integer> favoriteDishes;

	public UserDTO(Integer id, String name, List<Integer> favoriteDishes) {
		this.id = id;
		this.name = name;
		this.favoriteDishes = favoriteDishes;
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

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFavoriteDishes(List<Integer> favoriteDishes) {
		this.favoriteDishes = favoriteDishes;
	}

	@Override
	public String toString() {
		return "id: " + id +
				"\nName: " + name;
	}

}
