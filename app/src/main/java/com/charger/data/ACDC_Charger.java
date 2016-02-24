package com.charger.data;

/**
 * Created by zhengyajun on 1/2/16.
 */
public class ACDC_Charger {
    public float voltage;
    public float current;
    public float charged_capacity;
    public int charged_time;
    public float temperature;
    public float status;

    public ACDC_Charger(int vol, int curr, int chrg_cap, int chrg_time, int temp, int stat){
        voltage = (float)vol / 10;
        current = (float)curr / 10;
        charged_capacity = (float)chrg_cap / 10;
        charged_time = chrg_time;
        temperature = (float)temp / 10;
        status = stat;
    }
}
