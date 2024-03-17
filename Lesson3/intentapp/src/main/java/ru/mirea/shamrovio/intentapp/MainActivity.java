package ru.mirea.shamrovio.intentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickSendTime(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        String _send = getTime();
        intent.putExtra("key", _send);
        startActivity(intent);
    }

    private String getTime() {
        long _dateInMillis = System.currentTimeMillis();
        String format = "yyyy-MM-dd HH:mm:ss";
        final SimpleDateFormat _sdf = new SimpleDateFormat(format);
        String _dateString = _sdf.format(new Date(_dateInMillis));
        return _dateString;
    }
}