package ru.mirea.shamrovio.favoritebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

	private ActivityResultLauncher<Intent> _activityResultLauncher;
	static final String KEY = "book_name";
	static final String USER_MESSAGE = "MESSAGE";
	private TextView _textViewUserBook;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		_textViewUserBook = findViewById(R.id.textViewBook);

		ActivityResultCallback<ActivityResult> _callback = new ActivityResultCallback<ActivityResult>() {
			@Override
			public void onActivityResult(ActivityResult result) {
				if (result.getResultCode() == Activity.RESULT_OK) {
					Intent data = result.getData();
					String _userBook = data.getStringExtra(USER_MESSAGE);
					_textViewUserBook.setText(_userBook);
				}
			}
		};

		_activityResultLauncher = registerForActivityResult(
				new ActivityResultContracts.StartActivityForResult(), _callback);
	}

	public void getInfoAboutBook(View view) {
		Intent _intent = new Intent(this, ShareActivity.class);
		_intent.putExtra(KEY, "Над пропастью во ржи");
		_activityResultLauncher.launch(_intent);
	}
}