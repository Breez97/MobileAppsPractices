package ru.mirea.shamrov.data.storage.models;

import java.util.List;

public class User {

	private Integer id;
	private String name;
	private String email;
	private String password;
	private List<Integer> favoriteDishes;

	public User(Integer id, String name, String email, String password, List<Integer> favoriteDishes) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.favoriteDishes = favoriteDishes;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
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

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFavoriteDishes(List<Integer> favoriteDishes) {
		this.favoriteDishes = favoriteDishes;
	}

}
