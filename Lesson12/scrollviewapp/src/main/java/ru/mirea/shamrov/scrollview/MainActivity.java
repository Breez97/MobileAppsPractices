package ru.mirea.shamrov.scrollview;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initScrollView();
	}

	private void initScrollView() {
		LinearLayout wrapper = findViewById(R.id.wrapper);
		for (int i = 0; i < 100; i++) {
			View view = getLayoutInflater().inflate(R.layout.item, null, false);
			TextView text = (TextView) view.findViewById(R.id.textView);
			text.setText(String.format(Locale.getDefault(), "Элемент %d", i));
			wrapper.addView(view);
		}
	}
}