package com.luodong.chart.chartview;

public class ChartModel {

    private float value;
    private String text;

    public ChartModel(String text, float value) {
        this.text = text;
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
