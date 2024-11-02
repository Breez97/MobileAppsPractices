package ru.mirea.shamrov.listviewapp;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

	private final String[] countries = {"Россия", "Бразилия", "Китай", "Индия", "ЮАР", "Иран",
			"Египет", "ОАЭ"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initListView();
	}

	private void initListView() {
		ListView listView = findViewById(R.id.listView);
		ArrayAdapter arrayAdapter = new ArrayAdapter(this,
				android.R.layout.simple_list_item_2, android.R.id.text1, countries) {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View view = super.getView(position, convertView, parent);
				TextView text1 = view.findViewById(android.R.id.text1);
				TextView text2 = view.findViewById(android.R.id.text2);
				text1.setText(String.valueOf(position + 1));
				text2.setText(getItem(position).toString());
				return view;
			};
		};
		listView.setAdapter(arrayAdapter);
	}
}