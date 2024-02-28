package ru.mirea.shamrovio.buttonclicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textShowInfo;
    private Button btnWhoAmI;
    private Button btnItsNotMe;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitObjects();
        View.OnClickListener oclBtnWhoAmI = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textShowInfo.setText("Мой номер по списку № 29");
                ChangeCheckBoxStatus();
            }
        };
        btnWhoAmI.setOnClickListener(oclBtnWhoAmI);
    }

    private void InitObjects() {
        textShowInfo = findViewById(R.id.tvOut);
        btnWhoAmI = findViewById(R.id.btnWhoAmI);
        btnItsNotMe = findViewById(R.id.btnItIsNotMe);
        checkBox = findViewById(R.id.checkBoxChange);
    }

    public void onMyButtonClick(View view) {
        textShowInfo.setText("Это не я сделал");
        ChangeCheckBoxStatus();
    }

    public void ChangeCheckBoxStatus() {
        if(checkBox.isChecked()) {
            checkBox.setChecked(false);
            checkBox.setText("Это не я нажал");
        }
        else {
            checkBox.setChecked(true);
            checkBox.setText("Это я нажал");
        }
    }
}