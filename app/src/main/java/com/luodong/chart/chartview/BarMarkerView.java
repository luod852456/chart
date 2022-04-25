package com.luodong.chart.chartview;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.luodong.chart.R;
import com.luodong.chart.SStringUtil;

import java.text.DecimalFormat;

public class BarMarkerView extends MarkerView {

    private TextView tvDate;
    private ValueFormatter xAxisValueFormatter;
    DecimalFormat df = new DecimalFormat("0.0");

    public BarMarkerView(Context context, ValueFormatter xAxisValueFormatter) {
        super(context, R.layout.view_bar_marker);

        this.xAxisValueFormatter = xAxisValueFormatter;
        tvDate = findViewById(R.id.tvDate);
    }

    //回调函数每次MarkerView重绘,可以用来更新内容(用户界面)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        if (e.getData() instanceof ChartExtraModel) {
            ChartExtraModel m = (ChartExtraModel) e.getData();
            //运动能力
            tvDate.setText(SStringUtil.append(m.getTitle(), m.getUnit()));
        } else {
            tvDate.setText(df.format(e.getY()));
        }
        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}
