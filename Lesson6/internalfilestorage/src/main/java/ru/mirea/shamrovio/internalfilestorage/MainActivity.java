package ru.mirea.shamrovio.internalfilestorage;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import ru.mirea.shamrovio.internalfilestorage.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
	private final String TAG = this.getClass().getSimpleName();
	private ActivityMainBinding binding;
	private String fileName = "memorable_date.txt";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		binding = ActivityMainBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());

		binding.editTextTextMultiLine.setText(getTextFromFile());

		binding.button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FileOutputStream outputStream;
				try {
					outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
					String result = binding.editTextTextMultiLine.getText().toString();
					outputStream.write(result.getBytes());
					outputStream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private String getTextFromFile() {
		FileInputStream fin = null;
		try {
			fin = openFileInput(fileName);
			byte[] bytes = new byte[fin.available()];
			fin.read(bytes);
			String text = new String(bytes);
			Log.d(TAG, text);
			return text;
		} catch (IOException e) {
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
		} finally {
			try {
				if(fin != null) {
					fin.close();
				}
			} catch (IOException e) {
				Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
			}
		}
		return null;
	}
}