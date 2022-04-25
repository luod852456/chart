package com.luodong.chart.chartview.marker;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.luodong.chart.R;

import java.text.DecimalFormat;

public class CommonMarkerView extends MarkerView {

    private TextView tvDate;
    private ValueFormatter xAxisValueFormatter;
    DecimalFormat df = new DecimalFormat("0.0");

    public CommonMarkerView(Context context, ValueFormatter xAxisValueFormatter) {
        super(context, R.layout.view_common_marker);

        this.xAxisValueFormatter = xAxisValueFormatter;
        tvDate = findViewById(R.id.tvDate);
    }

    //回调函数每次MarkerView重绘,可以用来更新内容(用户界面)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        tvDate.setText(df.format(e.getY()));
        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}
