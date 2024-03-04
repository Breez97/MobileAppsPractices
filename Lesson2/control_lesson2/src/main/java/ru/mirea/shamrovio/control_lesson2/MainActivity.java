package ru.mirea.shamrovio.control_lesson2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import java.time.Month;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
	private String _showTextInfo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void onClickShowTimeDialog(View view) {
		MyTimeDialogFragment _timeDialog = new MyTimeDialogFragment(this, (view1, hourOfDay, minute) -> {
			_showTextInfo = String.format(Locale.getDefault(),
					"Hours: %d | Minutes: %d", hourOfDay, minute);
			useSnackbar(view, _showTextInfo);
		}, 17, 52, true);
		_timeDialog.show();
		useSnackbar(view, "Time Picker Opened");
	}
	
	public void onClickShowDateDialog(View view) {
		MyDateDialogFragment _dateDialog = new MyDateDialogFragment(this, (view1, year, month, dayOfMonth) -> {
			_showTextInfo = String.format(Locale.getDefault(),
					"Year: %d | Month: %s | Day: %d", year, Month.of(month + 1), dayOfMonth);
			useSnackbar(view, _showTextInfo);
		}, 2023, 11, 12);
		_dateDialog.show();
		useSnackbar(view, "Date Picker Opened");
	}
	
	public void onClickShowProgressDialog(View view) {
		MyProgressDialogFragment _progressDialog = new MyProgressDialogFragment(this);
		_progressDialog.setTitle("Progress Dialog");
		_progressDialog.setMessage("Loading...");
		_progressDialog.show();
		useSnackbar(view, "Progress Dialog Opened");
	}
	
	private void useSnackbar(View view, String _showTextInfo) {
		Snackbar.make(view, _showTextInfo, Snackbar.LENGTH_LONG).show();
	}
}