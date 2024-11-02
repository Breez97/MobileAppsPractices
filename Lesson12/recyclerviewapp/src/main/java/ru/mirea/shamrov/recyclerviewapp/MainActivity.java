package ru.mirea.shamrov.recyclerviewapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		List<Country> countries = getListData();
		RecyclerView recyclerView = this.findViewById(R.id.recyclerView);
		recyclerView.setAdapter(new CountryRecyclerViewAdapter(countries));

		LinearLayoutManager layoutManager = new LinearLayoutManager(this,
				LinearLayoutManager.VERTICAL, false);
		recyclerView.setLayoutManager(layoutManager);
	}

	private List<Country> getListData() {
		List<Country> list = new ArrayList<>();
		Country brazil = new Country("Brazil", "flag_of_brazil", 210000000);
		Country china = new Country("China", "flag_of_china", 1420000000);
		Country india = new Country("India", "flag_of_india", 1430000000);
		Country russia = new Country("Russia", "flag_of_russia", 146000000);
		Country southAfrica = new Country("South Africa", "flag_of_south_africa", 64000000);
		list.add(brazil);
		list.add(china);
		list.add(india);
		list.add(russia);
		list.add(southAfrica);
		return list;
	}
}