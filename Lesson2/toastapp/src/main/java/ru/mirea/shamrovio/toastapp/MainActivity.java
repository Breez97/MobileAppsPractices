package ru.mirea.shamrovio.toastapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
	
	private TextView _textView;
	private String _resultString = "";
	int _stringLength = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initObjects();
	}
	
	private void initObjects() {
		_textView = findViewById(R.id.editTextText);
	}
	
	public void showToast(View view) {
		Toast _toast = Toast.makeText(getApplicationContext(), fillToast(), Toast.LENGTH_SHORT);
		_toast.show();
	}
	
	private String fillToast() {
		_resultString = _textView.getText().toString();
		_stringLength = _resultString.length();
		_resultString = String.format(Locale.getDefault(), "Студент №29 Группа БСБО-10-21 " +
				"Количество символов - %d", _stringLength);
		return _resultString;
	}
}