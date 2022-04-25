package com.luodong.chart.chartview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.luodong.chart.R;
import com.luodong.chart.SAppView;
import com.luodong.chart.chartview.bean.ChartModel;
import com.luodong.chart.chartview.marker.CommonMarkerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.github.mikephil.charting.animation.Easing.EaseInOutQuart;

/**
 * Created by luodong on 2020/7/24.
 */

public class CommonLineChartView extends SAppView {

    private LineChart lineChart;
    private List<ChartModel> datalist;

    private YAxis leftAxis;
    private YAxis rightAxis;
    private XAxis xAxis;

    private LineData lineData;
    private LineDataSet lineDataSet;
    private List<String> timeList = new ArrayList<>(); //存储x轴的时间

    public CommonLineChartView(Context context) {
        super(context);
        init();
    }

    public CommonLineChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setContentView(R.layout.view_line_chart_common);
        initLineChart();
    }

    private void initLineChart() {
        lineChart = findViewById(R.id.v_line_chart);
        lineChart.clear();

        leftAxis = lineChart.getAxisLeft();
        rightAxis = lineChart.getAxisRight();
        xAxis = lineChart.getXAxis();

        lineChart.setNoDataText("暂无数据");   // 没有数据时样式
        // 是否可以缩放
        lineChart.setScaleEnabled(false);
        //是否可以拖动
        lineChart.setDragEnabled(false);
        //是否有触摸事件
        lineChart.setTouchEnabled(true);
        lineChart.setDrawGridBackground(false);
        //显示边界
        lineChart.setDrawBorders(false);
        //设置动画效果
        lineChart.animateY(1000, EaseInOutQuart);
//        lineChart.animateX(1000);
        //图例
        lineChart.getLegend().setEnabled(false);

        //右下角说明
        Description description = new Description();
        description.setEnabled(false);
        lineChart.setDescription(description);
        //设置图表四周边距
        lineChart.setExtraOffsets(getActivity().getResources().getDimension(R.dimen.px0dp),
                getActivity().getResources().getDimension(R.dimen.px50dp),
                getActivity().getResources().getDimension(R.dimen.px20dp),
                getActivity().getResources().getDimension(R.dimen.px20dp));


        //保证Y轴从0开始，不然会上移一点
//        leftAxis.setAxisMinimum(0f);
//        rightAxis.setAxisMinimum(0f);

        //网格线设置
        xAxis.setEnabled(true);
        rightAxis.setEnabled(true);
        leftAxis.setEnabled(true);
        //设置虚线
        leftAxis.setDrawGridLines(true);
        leftAxis.setGridColor(ContextCompat.getColor(getActivity(), R.color.sd_color_line));
        leftAxis.setTextColor(ContextCompat.getColor(getActivity(), R.color.sd_color_auxiliary));
        leftAxis.setAxisLineColor(ContextCompat.getColor(getActivity(), R.color.sd_color_line));
        leftAxis.setGranularity(1f);
        leftAxis.setXOffset(getActivity().getResources().getDimension(R.dimen.px10dp));

        rightAxis.setDrawGridLines(false);
        rightAxis.setAxisLineColor(ContextCompat.getColor(getActivity(), R.color.sd_color_transparent));
        rightAxis.setTextColor(ContextCompat.getColor(getActivity(), R.color.sd_color_transparent));

        xAxis.setDrawGridLines(false);
        xAxis.setAvoidFirstLastClipping(true);//x轴首个标签缩进到x轴正向上
        xAxis.setYOffset(getActivity().getResources().getDimensionPixelOffset(R.dimen.px10dp));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setAxisLineColor(ContextCompat.getColor(getActivity(), R.color.sd_color_line));
        xAxis.setTextColor(ContextCompat.getColor(getActivity(), R.color.sd_color_auxiliary));
        xAxis.setLabelCount(3);
//        xAxis.setSpaceMin(0.1f);//不从0开始

        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                try {
                    return timeList.get((int) value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return "";
            }
        });

        initMarkerView();

        initLineDataSet(ContextCompat.getColor(getActivity(), R.color.sd_color_label),
                ContextCompat.getDrawable(getActivity(), R.color.sd_color_white));
    }

    private void initMarkerView() {
        //设置悬浮
        CommonMarkerView mv = new CommonMarkerView(lineChart.getContext(), new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return timeList.get((int) value);
            }
        });
        mv.setChartView(lineChart);
        lineChart.setMarker(mv);
    }

    private void initLineDataSet(Integer color, Drawable bg) {
        lineDataSet = new LineDataSet(null, "");
        lineDataSet.setLineWidth(2.0f);
        lineDataSet.setCircleRadius(1.0f);
        lineDataSet.setCircleHoleRadius(0.0f);
        lineDataSet.setDrawHighlightIndicators(true);//是否设置高亮指标
        lineDataSet.setHighlightEnabled(true);//是否设置高亮指标
        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineDataSet.setDrawValues(false);
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setDrawCircles(true);
        lineDataSet.setDrawHorizontalHighlightIndicator(false);
        lineDataSet.setMode(LineDataSet.Mode.LINEAR);
        lineDataSet.setDrawFilled(false);//是否背景填充
        lineDataSet.setFillDrawable(bg);//背景填充
        lineDataSet.setColor(color);
        lineDataSet.setCircleColor(color);
        lineDataSet.setHighLightColor(ContextCompat.getColor(getActivity(), R.color.sd_color_line));

        lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
    }

    public void setData(List<ChartModel> data) {
        timeList.clear();
        lineChart.clear();//清除
        initLineChart();//初始化

        this.datalist = data;
        for (int i = 0; i < datalist.size(); i++) {
            addEntry(datalist.get(i));
        }

        List<Float> list = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            list.add(data.get(i).getValue());
        }
        float fmax = Collections.max(list);
        float fmin = Collections.min(list);
        leftAxis.setAxisMaximum((int) (fmax + 2));//y轴多一个单位长度，为了好看
        leftAxis.setAxisMinimum((int) (fmin - 1));//y轴少一个单位长度，为了好看

        lineChart.highlightValue(data.size() - 1, 0);//选中最后一项 显示标志
        //通知数据已经改变
        lineData.notifyDataChanged();
        lineChart.notifyDataSetChanged();
        lineChart.invalidate();

    }

    private void addEntry(ChartModel model) {
        timeList.add(model.getText());

        Entry entry = new Entry(lineDataSet.getEntryCount(), model.getValue());
        lineData.addEntry(entry, 0);
    }

    public void clearData() {
        lineData.clearValues();
    }

}
