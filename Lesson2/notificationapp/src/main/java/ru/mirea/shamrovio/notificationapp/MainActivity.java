package ru.mirea.shamrovio.notificationapp;

import static android.Manifest.permission.POST_NOTIFICATIONS;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationBuilderWithBuilderAccessor;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
	
	private static final String CHANNEL_ID = "com.mirea.asd.notification.ANDROID";
	private int _permissionCode = 200;
	
	@RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		if (ContextCompat.checkSelfPermission(this, POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
			Log.d(MainActivity.class.getSimpleName().toString(), "Разрешения получены");
		} else {
			Log.d(MainActivity.class.getSimpleName().toString(), "Нет разрешений");
			ActivityCompat.requestPermissions(this, new String[]{POST_NOTIFICATIONS}, _permissionCode);
		}
	}
	
	public void onClickNewMessageNotification(View view) {
		if (ActivityCompat.checkSelfPermission(this, POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
			return;
		}
		
		NotificationCompat.Builder _builder = new NotificationCompat.Builder(this, CHANNEL_ID)
				.setContentText("Congratulation")
				.setSmallIcon(R.drawable.ic_launcher_background)
				.setPriority(NotificationCompat.PRIORITY_HIGH)
				.setStyle(new NotificationCompat.BigTextStyle().bigText("Much longer text that cannot fit one line..."))
				.setContentTitle("MIREA");
		
		int _importance = NotificationManager.IMPORTANCE_DEFAULT;
		
		NotificationChannel _channel = new NotificationChannel(CHANNEL_ID, "Student Shamrov Ilya Olegovich Notification", _importance);
		_channel.setDescription("MIREA Channel");
		
		NotificationManagerCompat _notificationManager = NotificationManagerCompat.from(this);
		_notificationManager.createNotificationChannel(_channel);
		_notificationManager.notify(1, _builder.build());
	}
}