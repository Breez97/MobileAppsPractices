package ru.mirea.shamrov.scrollviewapp;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item);

		LinearLayout wrapper = findViewById(R.id.wrapper);

		for (int i = 0; i < 100; i++) {
			View view = getLayoutInflater().inflate(R.layout.item, null, false);
			TextView textView = view.findViewById(R.id.textView);
			textView.setText(String.format("Элемент %d", i));
			wrapper.addView(view);
		}
	}
}