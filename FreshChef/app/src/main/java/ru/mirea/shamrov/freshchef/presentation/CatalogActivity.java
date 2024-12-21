package ru.mirea.shamrov.freshchef.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import ru.mirea.shamrov.freshchef.databinding.ActivityCatalogBinding;
import ru.mirea.shamrov.freshchef.presentation.adapter.CatalogAdapter;
import ru.mirea.shamrov.freshchef.presentation.decorator.SpacingItemDecoration;
import ru.mirea.shamrov.freshchef.presentation.viewmodel.CatalogViewModel;
import ru.mirea.shamrov.freshchef.presentation.viewmodel.CatalogViewModelFactory;

public class CatalogActivity extends AppCompatActivity {

	private ActivityCatalogBinding binding;
	private CatalogViewModel catalogViewModel;
	private Button buttonCatalogToMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		catalogViewModel = new ViewModelProvider(this, new CatalogViewModelFactory(this)).get(CatalogViewModel.class);
		binding = ActivityCatalogBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		initWidgets();
		initRecyclerView();
		initButtons();
	}

	private void initWidgets() {
		buttonCatalogToMenu = binding.buttonCatalogToMenu;
	}

	private void initRecyclerView() {
		RecyclerView recyclerView = this.binding.recyclerView;
		recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
		CatalogAdapter itemAdapter = new CatalogAdapter();
		recyclerView.setAdapter(itemAdapter);
		recyclerView.addItemDecoration(new SpacingItemDecoration(20));
		catalogViewModel.getItems().observe(this, items -> {
			itemAdapter.setItems(items, this);
		});
	}

	private void initButtons() {
		buttonCatalogToMenu.setOnClickListener(view ->
				startActivity(new Intent(CatalogActivity.this, MenuActivity.class))
		);
	}
}