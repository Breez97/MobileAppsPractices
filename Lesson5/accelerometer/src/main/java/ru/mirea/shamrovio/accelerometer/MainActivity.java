package ru.mirea.shamrovio.accelerometer;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.shamrovio.accelerometer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
	private ActivityMainBinding _binding;
	private float _valueAzimuth, _valuePitch, _valueRoll;
	private SensorManager _sensorManager;
	private Sensor _accelerometerSensor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		_binding = ActivityMainBinding.inflate(getLayoutInflater());
		setContentView(_binding.getRoot());

		_sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		_accelerometerSensor = _sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		_sensorManager.registerListener(this, _accelerometerSensor,
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onPause() {
		super.onPause();
		_sensorManager.unregisterListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		_sensorManager.registerListener(this, _accelerometerSensor,
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	public void onSensorChanged(SensorEvent _event) {
		if(_event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			_valueAzimuth = _event.values[0];
			_valuePitch = _event.values[1];
			_valueRoll = _event.values[2];

			_binding.textViewAzimuth.setText("Azimuth: " + _valueAzimuth);
			_binding.textViewPitch.setText("Pitch: " + _valuePitch);
			_binding.textViewRoll.setText("Roll: " + _valueRoll);
		}
	}

	@Override
	public void onAccuracyChanged(Sensor _sensor, int _accuracy) {

	}
}