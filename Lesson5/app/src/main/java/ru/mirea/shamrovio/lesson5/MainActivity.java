package ru.mirea.shamrovio.lesson5;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import ru.mirea.shamrovio.lesson5.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
	private ActivityMainBinding _binding;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		_binding = ActivityMainBinding.inflate(getLayoutInflater());
		setContentView(_binding.getRoot());

		SensorManager _sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		List<Sensor> _sensors = _sensorManager.getSensorList(Sensor.TYPE_ALL);
		ListView _listSensor = _binding.sensorListView;

		ArrayList<HashMap<String, Object>> _arrayList = new ArrayList<>();
		for(int i = 0; i < _sensors.size(); i++) {
			HashMap<String, Object> _sensorTypeList = new HashMap<>();
			_sensorTypeList.put("Name", _sensors.get(i).getName());
			_sensorTypeList.put("Value", _sensors.get(i).getMaximumRange());
			_arrayList.add(_sensorTypeList);
		}

		SimpleAdapter _mHistory = new SimpleAdapter(this, _arrayList,
				android.R.layout.simple_list_item_2, new String[]{"Name", "Value"},
				new int[]{android.R.id.text1, android.R.id.text2});
		_listSensor.setAdapter(_mHistory);
	}
}