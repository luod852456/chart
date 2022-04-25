package com.luodong.chart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv_line).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LineChartActivity.newInstance(MainActivity.this);
            }
        });
        findViewById(R.id.tv_bar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BarChartActivity.newInstance(MainActivity.this);
            }
        });
        findViewById(R.id.tv_radar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadarChartActivity.newInstance(MainActivity.this);
            }
        });
    }
}
