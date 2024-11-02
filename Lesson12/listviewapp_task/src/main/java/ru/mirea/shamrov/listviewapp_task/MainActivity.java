package ru.mirea.shamrov.listviewapp_task;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

	private final String[] books = {
			"Братья Карамазовы", "Дворянское гнездо", "Маскарад", "Анна Каренина",
			"Мёртвые души", "Вишнёвый сад", "Горе от ума", "Обломов", "Левша","Мать",
			"Доктор Живаго", "Жизнь Арсеньева", "Мастер и Маргарита", "Котлован",
			"Архипелаг ГУЛАГ", "Калина красная", "Лолита", "Телеграмма", "Зависть",
			"Облако в штанах", "Случаи", "Чапаев и Пустота", "Прощание с Матёрой",
			"Это я – Эдичка", "Школа для дураков", "Остров Крым", "Казус Кукоцкого", "Обитель",
			"Зулейха открывает глаза", "Белая голубка Кордовы", "Сердце Пармы", "Лёд", "Лавр",
			"Дом, в котором", "Елтышевы", "Каменный мост", "Похороните меня за плинтусом",
			"Сажайте, и вырастет", "Дом, в котором сбываются мечты", "Матисс", "Лабиринты Ехо"
	};

	private final String[] authors = {
			"Фёдор Достоевский", "Иван Тургенев", "Михаил Лермонтов", "Лев Толстой",
			"Николай Гоголь", "Антон Чехов", "Александр Грибоедов","Иван Гончаров","Николай Лесков",
			"Максим Горький","Борис Пастернак", "Иван Бунин", "Михаил Булгаков", "Андрей Платонов",
			"Александр Солженицын", "Василий Шукшин", "Владимир Набоков", "Константин Паустовский",
			"Юрий Олеша", "Владимир Маяковский", "Даниил Хармс", "Виктор Пелевин",
			"Валентин Распутин", "Эдуард Лимонов", "Саша Соколов", "Василий Аксенов",
			"Людмила Улицкая", "Захар Прилепин", "Гузель Яхина", "Дина Рубина",
			"Алексей Иванов", "Владимир Сорокин", "Евгений Водолазкин", "Мариам Петросян",
			"Роман Сенчин", "Александр Терехов", "Павел Санаев", "Андрей Рубанов",
			"Олег Рой", "Александр Иличевский", "Макс Фрай"
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initListView();
	}

	private void initListView() {
		ListView listView = findViewById(R.id.listView);
		ArrayAdapter arrayAdapter = new ArrayAdapter(this,
				android.R.layout.simple_list_item_2, android.R.id.text1, books) {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View view = super.getView(position, convertView, parent);
				TextView text1 = view.findViewById(android.R.id.text1);
				TextView text2 = view.findViewById(android.R.id.text2);
				text1.setText(getItem(position).toString());
				String authorString = "Нет информации";
				if (position < authors.length) authorString = authors[position];
				text2.setText(authorString);
				return view;
			};
		};
		listView.setAdapter(arrayAdapter);
	}
}