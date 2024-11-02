package ru.mirea.shamrov.recyclerviewapp;

import androidx.annotation.NonNull;

public class Country {

	private final String countryName;
	private final String flagName;
	private final int population;

	public Country(String countryName, String flagName, int population) {
		this.countryName = countryName;
		this.flagName = flagName;
		this.population = population;
	}

	public String getCountryName() {
		return countryName;
	}

	public String getFlagName() {
		return flagName;
	}

	public int getPopulation() {
		return population;
	}

	@NonNull
	@Override
	public String toString() {
		return this.countryName + " (Population: " + this.population + ")";
	}
}
