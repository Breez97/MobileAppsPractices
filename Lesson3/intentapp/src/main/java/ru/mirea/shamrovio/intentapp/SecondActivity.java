package ru.mirea.shamrovio.intentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private String _fillText;
    private TextView _textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initObjects();
        _textView.setText(_fillText);
    }

    private void initObjects() {
        _textView = findViewById(R.id.textView);
        _fillText = "КВАДРАТ ЗНАЧЕНИЯ МОЕГО НОМЕРА ПО СПИСКУ В ГРУППЕ СОСТАВЛЯЕТ 841, а текущее время " + (String) getIntent().getSerializableExtra("key") + ".";
    }
}