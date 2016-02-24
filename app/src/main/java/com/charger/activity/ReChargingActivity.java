package com.charger.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.charger.data.Battery;
import com.nordicsemi.nrfUARTv2.R;

/**
 * Created by zhengyajun on 1/2/16.
 */
public class ReChargingActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recharging);

            TextView charging_tv = (TextView) findViewById(R.id.charging_tv);

        int voltage = 100;
        int current = 0;
        int residual_capacity = 30;
        int temperature = 50;
        int status = 0;
        Battery battery = new Battery(voltage, current, residual_capacity, temperature, status);
        battery.getClass();


        String charging_text = "当前充电方式：" + "";
        charging_text+="已充电时间："+" ";
        charging_text+="还需时间："+" ";

        charging_tv.setText(charging_text);
        }
    }
