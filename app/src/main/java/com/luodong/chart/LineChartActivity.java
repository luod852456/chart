package com.luodong.chart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.luodong.chart.chartview.bean.ChartModel;
import com.luodong.chart.chartview.CommonLineChartView;

import java.util.ArrayList;
import java.util.List;

public class LineChartActivity extends AppCompatActivity {

    public static void newInstance(Context context) {
        Intent intent = new Intent(context, LineChartActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);


        CommonLineChartView lineChart = findViewById(R.id.line_chart);

        List<ChartModel> data = new ArrayList<>();
        data.add(new ChartModel("1-1", 8.8f));
        data.add(new ChartModel("1-2", 9.1f));
        data.add(new ChartModel("1-3", 7.9f));
        data.add(new ChartModel("1-4", 8.3f));
        data.add(new ChartModel("1-5", 7.6f));
        data.add(new ChartModel("1-6", 8.1f));
        data.add(new ChartModel("1-7", 9.0f));
        lineChart.setData(data);

    }
}
