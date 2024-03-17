package ru.mirea.shamrovio.simplefragmentapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

	private Fragment _firstFragment, _secondFragment;
	private FragmentManager _fragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		_firstFragment = new FirstFragment();
		_secondFragment = new SecondFragment();
	}

	public void onClick(View view) {
		_fragmentManager = getSupportFragmentManager();
		if(view.getId() == R.id.btnFirstFragment) {
			_fragmentManager.beginTransaction().replace(R.id.fragmentContainer, _firstFragment).commit();
		}
		else if(view.getId() == R.id.btnSecondFragment) {
			_fragmentManager.beginTransaction().replace(R.id.fragmentContainer, _secondFragment).commit();
		}
	}
}