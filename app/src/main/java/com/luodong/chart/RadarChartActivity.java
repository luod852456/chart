package com.luodong.chart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.luodong.chart.chartview.bean.ChartModel;
import com.luodong.chart.chartview.CommonRadarChartView;

import java.util.ArrayList;
import java.util.List;

public class RadarChartActivity extends AppCompatActivity {

    public static void newInstance(Context context) {
        Intent intent = new Intent(context, RadarChartActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar_chart);

        CommonRadarChartView radar_chart = findViewById(R.id.radar_chart);

        List<ChartModel> data = new ArrayList<>();
        data.add(new ChartModel("A能力", 5));
        data.add(new ChartModel("B能力", 4));
        data.add(new ChartModel("C能力", 5));
        data.add(new ChartModel("D能力", 4));
        data.add(new ChartModel("E能力", 4));
        radar_chart.setData(data);
    }
}
