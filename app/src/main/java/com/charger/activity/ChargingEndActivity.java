package com.charger.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nordicsemi.nrfUARTv2.R;

/**
 * Created by zhengyajun on 1/2/16.
 */
public class ChargingEndActivity extends Activity {
    private static final String TAG = "ChargingActivity";
    private static final String charge_slow_mod = "慢充";
    private static final String charge_quick_mod = "快充";
    private static int charging_time = 100;
    private static int left_time = 100;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.charging_end);

            TextView chargingend_tv = (TextView) findViewById(R.id.chargingend_tv);
            String charging_text = "当前充电方式： " + charge_slow_mod;
            charging_text+="已充电时间： "+ charging_time +" 分钟";
            charging_text+="还需时间："+ left_time +" 分钟";

        chargingend_tv.setText(charging_text);
        }
    }
