package com.luodong.chart.chartview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.luodong.chart.R;
import com.luodong.chart.SAppView;

import java.util.ArrayList;
import java.util.List;

import static com.github.mikephil.charting.animation.Easing.EaseInOutQuart;


/**
 * Created by luodong on 2022/4/22.
 */

public class RadarChartForCommon extends SAppView {

    private RadarChart radar;
    private List<ChartModel> datalist;
    List<RadarEntry> list;

    private List<String> timeList = new ArrayList<>(); //存储x轴的时间

    public RadarChartForCommon(Context context) {
        super(context);
        init();
    }

    public RadarChartForCommon(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setContentView(R.layout.view_radar_chart_common);
        initChart();
    }

    private void initChart() {
        radar = findViewById(R.id.v_radar_chart);

        radar.setNoDataText("暂无数据");   // 没有数据时样式
        //是否有触摸事件
        radar.setTouchEnabled(false);
        //设置动画效果
        radar.animateY(1000, EaseInOutQuart);
//        lineChart.animateX(1000);
        //Y轴最小值不设置会导致数据中最小值默认成为Y轴最小值
//        radar.getYAxis().setAxisMinimum(0);
        //大字的颜色（中心点和各顶点的连线）
        radar.setWebColor(ContextCompat.getColor(getActivity(), R.color.color_B5D3FF));
        //所有五边形的颜色
        radar.setWebColorInner(ContextCompat.getColor(getActivity(), R.color.color_B5D3FF));
        //整个控件的背景颜色
        radar.setBackgroundColor(Color.TRANSPARENT);
        //对于右下角一串字母的操作
        radar.getDescription().setEnabled(false);                  //是否显示右下角描述
//        radar.getDescription().setText("这是修改那串英文的方法");    //修改右下角字母的显示
//        radar.getDescription().setTextSize(20);                    //字体大小
//        radar.getDescription().setTextColor(Color.CYAN);             //字体颜色
//        //图例
        Legend legend = radar.getLegend();
        legend.setEnabled(false);    //是否显示图例
        radar.setExtraOffsets(getActivity().getResources().getDimension(R.dimen.px0dp),
                getActivity().getResources().getDimension(R.dimen.px0dp),
                getActivity().getResources().getDimension(R.dimen.px0dp),
                getActivity().getResources().getDimension(R.dimen.px0dp));


        XAxis xAxis = radar.getXAxis();
        xAxis.setTextColor(Color.BLACK);//X轴字体颜色
        xAxis.setTextSize(10);     //X轴字体大小
//        xAxis.mLabelHeight = getActivity().getResources().getDimensionPixelOffset(R.dimen.px200dp);
//        xAxis.mLabelWidth = getActivity().getResources().getDimensionPixelOffset(R.dimen.px200dp);
        //自定义X轴坐标描述（也就是五个顶点上的文字,默认是0、1、2、3、4）
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float v) {
                return datalist.get((int) v).getText() +
                        "  " +
                        datalist.get((int) v).getValue() +
                        getActivity().getString(R.string.string_fen);
            }
        });


        YAxis yAxis = radar.getYAxis();
        //是否绘制Y轴坐标点  和雷达框数据一般不同时存在 否则显得很挤 默认为true
        yAxis.setDrawLabels(true);
        yAxis.setTextColor(Color.TRANSPARENT);//Y轴坐标数据的颜色
        yAxis.setAxisMaximum(5);   //Y轴最大数值
        yAxis.setAxisMinimum(0);   //Y轴最小数值
        //Y轴坐标的个数    第二个参数一般填false     true表示强制设置标签数 可能会导致X轴坐标显示不全等问题
        yAxis.setLabelCount(6, true);

    }

    public void setData(List<ChartModel> data) {
        timeList.clear();
        radar.clear();//清除
        initChart();//初始化

        list = new ArrayList<>();
        this.datalist = data;
        for (int i = 0; i < datalist.size(); i++) {
            list.add(new RadarEntry(data.get(i).getValue()));
        }

        RadarDataSet radarDataSet = new RadarDataSet(list, "");
        radarDataSet.setColor(ContextCompat.getColor(getActivity(), R.color.sd_color_label));
        //是否绘制雷达框上对每个点的数据的标注    和Y轴坐标点一般不同时存在 否则显得很挤  默认为true
        radarDataSet.setDrawValues(false);
//        radarDataSet1.setDrawValues(false);
        radarDataSet.setValueTextSize(12);  //数据值得字体大小（这里只是写在这）
        radarDataSet.setValueTextColor(Color.TRANSPARENT);//数据值得字体颜色（这里只是写在这）
        radarDataSet.setLineWidth(2.0f);
        radarDataSet.setDrawHighlightIndicators(false);//是否设置高亮指标
        radarDataSet.setHighlightEnabled(false);//是否设置高亮指标
        radarDataSet.setDrawValues(false);
        radarDataSet.setDrawHorizontalHighlightIndicator(false);
        radarDataSet.setDrawFilled(true);//是否背景填充
        radarDataSet.setFillDrawable(ContextCompat.getDrawable(getActivity(), R.color.color_993896FF));//背景填充
//        radarDataSet.setFillAlpha(60);//背景填充透明度
//        radarDataSet.enableDashedHighlightLine(getActivity().getResources().getDimension(R.dimen.px4dp), getActivity().getResources().getDimension(R.dimen.px4dp), 0);//破折线
        radarDataSet.setFormLineDashEffect(new DashPathEffect(new float[]{
                getActivity().getResources().getDimension(R.dimen.px4dp),
                getActivity().getResources().getDimension(R.dimen.px4dp)}, 0));
        RadarData radarData = new RadarData(radarDataSet);
//        radarData.addDataSet(radarDataSet1);
        radar.setData(radarData);

        //通知数据已经改变
        radarData.notifyDataChanged();
        radar.notifyDataSetChanged();
        radar.invalidate();
    }
}
