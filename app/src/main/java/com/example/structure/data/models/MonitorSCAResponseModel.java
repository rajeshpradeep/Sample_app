package com.example.structure.data.models;

/**
 * Created by Rajesh Pradeep G on 04-02-2020
 */
public class MonitorSCAResponseModel {

    private int monitor_icon;
    private String monitorVal;
    private String monitorKey;

    public int getMonitor_icon() {
        return monitor_icon;
    }

    public void setMonitor_icon(int monitor_icon) {
        this.monitor_icon = monitor_icon;
    }

    public String getMonitorVal() {
        return monitorVal;
    }

    public void setMonitorVal(String monitorVal) {
        this.monitorVal = monitorVal;
    }

    public String getMonitorKey() {
        return monitorKey;
    }

    public void setMonitorKey(String monitorKey) {
        this.monitorKey = monitorKey;
    }
}
