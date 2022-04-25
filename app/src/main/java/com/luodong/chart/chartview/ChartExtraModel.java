package com.luodong.chart.chartview;

public class ChartExtraModel {
    private String title;
    private String unit;
    private int type;

    public ChartExtraModel(String title, String unit, int type) {
        this.title = title;
        this.unit = unit;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
