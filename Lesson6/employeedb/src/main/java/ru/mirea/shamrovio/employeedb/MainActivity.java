package ru.mirea.shamrovio.employeedb;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import ru.mirea.shamrovio.employeedb.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
	private final String TAG = this.getClass().getSimpleName();
	private ActivityMainBinding binding;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		binding = ActivityMainBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());

		AppDatabase db = App.getInstance().getDatabase();
		EmployeeDao employeeDao = db.employeeDao();

		Employee employee = new Employee();
		employee.id = 1;
		employee.name = "John Smith";
		employee.salary = 10000;

		employeeDao.insert(employee);

		List<Employee> employees = employeeDao.getAll();
		employee = employeeDao.getById(1);
		employee.salary = 20000;
		employeeDao.update(employee);
		Log.d(TAG, employee.name + " " + employee.salary);
	}
}