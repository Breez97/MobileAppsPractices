package ru.mirea.shamrovio.intentfilter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void openBrowser(View view) {
		Uri _address = Uri.parse("https://www.mirea.ru/");
		Intent _openLinkIntent = new Intent(Intent.ACTION_VIEW, _address);
		startActivity(_openLinkIntent);
	}
	
	public void sendInfo(View view) {
		Intent _shareIntent = new Intent(Intent.ACTION_SEND);
		_shareIntent.setType("text/plain");
		_shareIntent.putExtra(Intent.EXTRA_SUBJECT, "MIREA");
		_shareIntent.putExtra(Intent.EXTRA_TEXT, "Шамров Илья Олегович");
		startActivity(Intent.createChooser(_shareIntent, "Мои ФИО"));
	}
}