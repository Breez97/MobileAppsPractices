package ru.mirea.shamrov.recyclerviewapp_task;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		List<Event> events = getListData();
		RecyclerView recyclerView = this.findViewById(R.id.recyclerView);
		recyclerView.setAdapter(new EventRecyclerViewAdapter(events));

		LinearLayoutManager layoutManager = new LinearLayoutManager(this,
				LinearLayoutManager.VERTICAL, false);
		recyclerView.setLayoutManager(layoutManager);
	}

	private List<Event> getListData() {
		List<Event> events = new ArrayList<>();
		Event frenchRevolution = new Event("Великая французская революция", "1789-1799",
				"Социальные и политические изменения во Франции, свержение монархии, права человека.",
				"event_french_revolution");
		Event kulikovoBattle = new Event("Куликовская битва", "8 сентября 1380",
				"Победа русских войск над Золотой Ордой на Куликовом поле.",
				"event_kulikovo_battle");
		Event boston = new Event("Бостонское чаепитие", "16 декабря 1773 года",
				"Протест колонистов против британских налогов, приведший к Американской революции.",
				"event_boston");
		Event octRevolution = new Event("Октябрьская революция", "26 октября 1917",
				"Большевистский переворот, изменивший политическую систему России.",
				"event_oct_revolution");
		Event firstWorldWar = new Event("Первая мировая война", "1914-1918",
				"Конфликт 1914–1918, приведший к огромным человеческим жертвам.",
				"event_first_world_war");
		events.add(frenchRevolution);
		events.add(kulikovoBattle);
		events.add(boston);
		events.add(octRevolution);
		events.add(firstWorldWar);
		return events;
	}
}