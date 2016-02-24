package com.charger.data;

/**
 * Created by zhengyajun on 31/1/16.
 */
public class Battery {
    private float voltage;
    private float current;
    private float residual_capacity;
    private float temperature;
    private int status;

    public Battery(int vol, int curr, int res_cap, int temp, int stat){
        voltage = (float)vol / 10;
        current = (float)curr / 10;
        residual_capacity = (float)res_cap / 10;
        temperature = (float)temp / 10;
        status = stat;
    }

    void setVoltage (int vol){
        voltage = (float)vol / 10;
    }
    void setCurrent(int curr){
        current = (float)curr / 10;
    }

}
