package com.luodong.chart.chartview;

import android.content.Context;
import android.util.AttributeSet;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.highlight.BarHighlighter;

/**
 * Chart that draws bars.
 *
 * @author Philipp Jahoda
 */
public class RoundBarChart extends BarChart {


    public RoundBarChart(Context context) {
        super(context);
    }

    public RoundBarChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RoundBarChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void init() {
        super.init();

        mRenderer = new RoundBarChartRenderer(this, mAnimator, mViewPortHandler);

        setHighlighter(new BarHighlighter(this));

//        getXAxis().setSpaceMin(0.5f);
//        getXAxis().setSpaceMax(0.5f);
    }

}
