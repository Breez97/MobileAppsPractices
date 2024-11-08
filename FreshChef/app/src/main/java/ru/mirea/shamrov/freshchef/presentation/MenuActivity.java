package ru.mirea.shamrov.freshchef.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.shamrov.freshchef.databinding.ActivityMenuBinding;

public class MenuActivity extends AppCompatActivity {

	private ActivityMenuBinding binding;

	private Button buttonMainPage;
	private Button buttonCatalog;
	private Button buttonProfile;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = ActivityMenuBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		initWidgets();
		initButtons();
	}

	private void initWidgets() {
		buttonMainPage = binding.buttonMainPage;
		buttonCatalog = binding.buttonCatalog;
		buttonProfile = binding.buttonProfile;
	}

	private void initButtons() {
		buttonMainPage.setOnClickListener(view ->
				startActivity(new Intent(MenuActivity.this, MainActivity.class))
		);

		buttonCatalog.setOnClickListener(view ->
				startActivity(new Intent(MenuActivity.this, CatalogActivity.class))
		);

		buttonProfile.setOnClickListener(view ->
				startActivity(new Intent(MenuActivity.this, AccountActivity.class))
		);
	}
}