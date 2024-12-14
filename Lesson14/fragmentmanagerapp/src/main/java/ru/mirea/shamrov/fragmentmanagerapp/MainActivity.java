package ru.mirea.shamrov.fragmentmanagerapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

	private ShareViewModel viewModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		viewModel = new ViewModelProvider(this).get(ShareViewModel.class);

		if (savedInstanceState == null) {
			HeaderFragment headerFragment = HeaderFragment.newInstance("MIREA");
			getSupportFragmentManager().beginTransaction()
					.setReorderingAllowed(true)
					.add(R.id.headerContainerView, headerFragment, "header")
					.add(R.id.detailsFragmentView, new DetailsFragment(), "details")
					.commit();
		}
	}
}