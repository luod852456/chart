package com.luodong.chart.chartview;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.luodong.chart.R;
import com.luodong.chart.SAppView;

import java.util.ArrayList;
import java.util.List;

import static com.github.mikephil.charting.animation.Easing.EaseInOutQuart;


/**
 * Created by luodong on 2020/7/24.
 */

public class BarChartForCommon extends SAppView {

    private BarChart barChart;
    private YAxis leftAxis;             //左侧Y轴
    private YAxis rightAxis;            //右侧Y轴
    private XAxis xAxis;                //X轴
    private List<ChartModel> datalist;

    private BarData barData;
    private BarDataSet barDataSet;
    private List<String> timeList = new ArrayList<>(); //存储x轴的时间

    public BarChartForCommon(Context context) {
        super(context);
        init();
    }

    public BarChartForCommon(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setContentView(R.layout.view_bar_chart_common);
        initLineChart();
    }

    private void initLineChart() {
        barChart = findViewById(R.id.v_bar_chart);

        barChart.setNoDataText("暂无数据");   // 没有数据时样式
        /***图表设置***/
        // 是否可以缩放
        barChart.setScaleEnabled(false);
        //是否可以拖动
        barChart.setDragEnabled(false);
        //是否有触摸事件
        barChart.setTouchEnabled(true);
        //背景颜色
//        barChart.setBackgroundColor(Color.WHITE);
        barChart.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.transparent));
        //不显示图表网格
        barChart.setDrawGridBackground(false);
        //背景阴影
        barChart.setDrawBarShadow(false);
        barChart.setHighlightFullBarEnabled(false);
        //显示边框
        barChart.setDrawBorders(false);
        //设置动画效果
        barChart.animateY(1000, EaseInOutQuart);
//        barChart.animateX(1000);
        //设置图表四周边距
        barChart.setExtraOffsets(getActivity().getResources().getDimension(R.dimen.px0dp),
                getActivity().getResources().getDimension(R.dimen.px70dp),
                getActivity().getResources().getDimension(R.dimen.px0dp),
                getActivity().getResources().getDimension(R.dimen.px0dp));

        /***XY轴的设置***/
        //X轴设置显示位置在底部
        xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisLineColor(ContextCompat.getColor(getActivity(), R.color.transparent));
        xAxis.setTextColor(ContextCompat.getColor(getActivity(), R.color.sd_color_auxiliary));
        xAxis.setTextSize(10f);
        xAxis.setGranularity(1f);
        xAxis.setDrawGridLines(false);
        xAxis.setYOffset(getActivity().getResources().getDimensionPixelOffset(R.dimen.px10dp));
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

        leftAxis = barChart.getAxisLeft();
        rightAxis = barChart.getAxisRight();

        leftAxis.setDrawAxisLine(true);
        leftAxis.setDrawGridLines(true);
        leftAxis.setGridColor(ContextCompat.getColor(getActivity(), R.color.sd_color_tab));
        leftAxis.setAxisLineColor(ContextCompat.getColor(getActivity(), R.color.transparent));
        leftAxis.setTextColor(ContextCompat.getColor(getActivity(), R.color.transparent));
        leftAxis.setAxisMinimum(0);
        leftAxis.setAxisMaximum(4);
        leftAxis.setGranularity(1f);

        rightAxis.setEnabled(false);
        rightAxis.setDrawGridLines(false);

        /***折线图例 标签 设置***/
        Legend legend = barChart.getLegend();
        legend.setForm(Legend.LegendForm.EMPTY);
        legend.setTextSize(11f);
        //显示位置
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        //是否绘制在图表里面
        legend.setDrawInside(false);

        //设置悬浮
        BarMarkerView mv = new BarMarkerView(barChart.getContext(), new ValueFormatter() {
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
        mv.setChartView(barChart);
        barChart.setMarker(mv);

        initLineDataSet();

        setDescription("");
    }


    private void initLineDataSet() {
        List<BarEntry> list = new ArrayList<>();
        barDataSet = new BarDataSet(list, "");
        barDataSet.setColors(ContextCompat.getColor(getActivity(), R.color.color_E9F2FF));
        barDataSet.setFormLineWidth(1f);
        barDataSet.setFormSize(15.f);
        //不显示柱状图顶部值
        barDataSet.setDrawValues(false);
        barDataSet.setHighLightColor(ContextCompat.getColor(getActivity(), R.color.color_3390FF));
        barDataSet.setHighlightEnabled(true);
//        barDataSet.setGradientColor(ContextCompat.getColor(MyApplication.getInstance().getContext(), R.color.transparent), color);

        barData = new BarData(barDataSet);
        barData.setBarWidth(0.43f);
        barChart.setData(barData);
    }

    public void setData(List<ChartModel> data) {
        timeList.clear();
        barChart.clear();//清除
        initLineChart();//初始化

        this.datalist = data;
        for (int i = 0; i < datalist.size(); i++) {
            addEntry(datalist.get(i));
        }

        List<Float> list = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            list.add(data.get(i).getValue());
        }
//        float fmax = Collections.max(list);
//        float fmin = Collections.min(list);
//        leftAxis.setAxisMaximum((int) (fmax + 2));//y轴多一个单位长度，为了好看
//        leftAxis.setAxisMinimum((int) (fmin - 1));//y轴少一个单位长度，为了好看

//        Float f = 1f;
//        for (int i = 0; i < datalist.size(); i++) {
//            if(datalist.get(i).getText().equals(level)){
//                f=datalist.get(i).getValue();
//            }
//        }

        barChart.highlightValue(data.size() - 1, 0);//选中一项 显示标志
        //通知数据已经改变
        barData.notifyDataChanged();
        barChart.notifyDataSetChanged();
        barChart.invalidate();

    }

    private void addEntry(ChartModel model) {
        timeList.add(model.getText());

        BarEntry entry = new BarEntry(barDataSet.getEntryCount(), model.getValue());
        entry.setData(new ChartExtraModel(getActivity().getString(R.string.athletic_ability_level_), model.getText(), 1));
        barData.addEntry(entry, 0);
    }

    /**
     * 设置描述信息（右下角 单位 mmHg）
     *
     * @param str
     */
    public void setDescription(String str) {
        Description description = new Description();
        description.setText(str);
        description.setTextColor(ContextCompat.getColor(getActivity(), R.color.theme_backgroud));
        barChart.setDescription(description);
        barChart.invalidate();
    }


}
