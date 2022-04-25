package com.luodong.chart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.luodong.chart.chartview.BarChartForCommon;
import com.luodong.chart.chartview.ChartModel;

import java.util.ArrayList;
import java.util.List;

public class BarChartActivity extends AppCompatActivity {

    public static void newInstance(Context context) {
        Intent intent = new Intent(context, BarChartActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);

        BarChartForCommon bar_chart = findViewById(R.id.bar_chart);

        List<ChartModel> data = new ArrayList<>();
        data.add(new ChartModel("L1", 1));
        data.add(new ChartModel("L2", 2));
        data.add(new ChartModel("L3", 3));
        data.add(new ChartModel("L4", 4));
        bar_chart.setData(data);
    }
}
