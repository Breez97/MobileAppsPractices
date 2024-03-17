package ru.mirea.shamrovio.favoritebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.appcompat.app.AppCompatActivity;

public class ShareActivity extends AppCompatActivity {

	private TextView _textView;
	private EditText _editText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_share);

		initObjects();
	}

	private void initObjects() {
		_textView = findViewById(R.id.textViewShareActivity);
		_editText = findViewById(R.id.editTextShareActivity);

		Bundle _extras = getIntent().getExtras();
		if (_extras != null) {
			String _input = _extras.getString(MainActivity.KEY);
			_textView.setText(String.format("Моя любимая книга: %s", _input));

			if(_textView.getText().length() > 0) {
				_editText.setText(_input);
			}
		}
	}

	public void onClickSendData(View view) {
		Intent _data = new Intent();
		if(_editText.getText().length() > 0) {
			_data.putExtra(MainActivity.USER_MESSAGE, _editText.getText().toString());
			setResult(Activity.RESULT_OK, _data);
		}
		else {
			setResult(Activity.RESULT_CANCELED, _data);
		}
		finish();
	}
}