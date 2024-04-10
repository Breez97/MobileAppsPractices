package ru.mirea.shamrovio.audiorecord;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.IOException;

import ru.mirea.shamrovio.audiorecord.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
	private final String TAG = MainActivity.class.getSimpleName();
	private ActivityMainBinding _binding;
	private static final int REQUEST_CODE_PERMISSION = 200;
	private boolean _isWork;
	private String _fileName = null;
	private Button _recordButton = null;
	private Button _playButton = null;
	private MediaRecorder _recorder = null;
	private MediaPlayer _player = null;
	boolean _isStartRecording = true;
	boolean _isStartPlaying = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		_binding = ActivityMainBinding.inflate(getLayoutInflater());
		setContentView(_binding.getRoot());
		_recordButton = _binding.buttonSartRecording;
		_playButton = _binding.buttonPlayRecord;
		_playButton.setEnabled(false);
		_fileName = (new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC),
				"/audiorecordtest.3gp")).getAbsolutePath();

		setPermissions();

		manageButtons();
	}

	@Override
	public void onRequestPermissionsResult(int _requestCode, @NonNull String[] _permissions,
										  @NonNull int[] _grantResults) {
		super.onRequestPermissionsResult(_requestCode, _permissions, _grantResults);
		switch(_requestCode) {
			case REQUEST_CODE_PERMISSION:
				_isWork = _grantResults[0] == PackageManager.PERMISSION_GRANTED;
				break;
		}
		if(!_isWork) finish();
	}

	private void setPermissions() {
		int _audioRecordPermissionStatus = ContextCompat.checkSelfPermission(this,
				android.Manifest.permission.RECORD_AUDIO);
		int _storagePermissionStatus = ContextCompat.checkSelfPermission(this,
				android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
		if(_audioRecordPermissionStatus == PackageManager.PERMISSION_GRANTED &&
				_storagePermissionStatus == PackageManager.PERMISSION_GRANTED) {
			_isWork = true;
		} else {
			ActivityCompat.requestPermissions(this, new String[]
							{Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE},
					REQUEST_CODE_PERMISSION);
		}
	}

	private void manageButtons() {
		_recordButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(_isStartRecording) {
					_recordButton.setText("Stop recording");
					_playButton.setEnabled(false);
					startRecording();
				} else {
					_recordButton.setText("Start recording");
					_playButton.setEnabled(true);
					stopRecording();
				}
				_isStartRecording = !_isStartRecording;
			}
		});

		_playButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(_isStartPlaying) {
					_playButton.setText("Stop playing");
					_recordButton.setEnabled(false);
					startPlaying();
				} else {
					_playButton.setText("Start playing");
					_recordButton.setEnabled(true);
					stopPlaying();
				}
				_isStartPlaying = !_isStartPlaying;
			}
		});
	}

	private void startRecording() {
		_recorder = new MediaRecorder();
		_recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		_recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		_recorder.setOutputFile(_fileName);
		_recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		try {
			_recorder.prepare();
		} catch (IOException e) {
			Log.e(TAG, "prepare() failed");
		}
		_recorder.start();
	}

	private void stopRecording() {
		_recorder.stop();
		_recorder.release();
		_recorder = null;
	}

	private void startPlaying() {
		_player = new MediaPlayer();
		try {
			_player.setDataSource(_fileName);
			_player.prepare();
			_player.start();
		} catch (IOException e) {
			Log.e(TAG, "prepare() failed");
		}
	}

	private void stopPlaying() {
		_player.release();
		_player = null;
	}
}