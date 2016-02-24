package com.charger.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.charger.data.Battery;
import com.nordicsemi.nrfUARTv2.R;

/**
 * Created by zhengyajun on 1/2/16.
 */
public class ChargingActivity extends Activity {

    private static final String TAG = "ChargingActivity";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.charging);
        final Button stopChargingBtn = (Button) findViewById(R.id.stopChargingBtn);
        final Button btnNext = (Button) findViewById(R.id.btn_next2);

        stopChargingBtn.setBackgroundColor(Color.RED);
        stopChargingBtn.setOnClickListener(new Button.OnClickListener() { //创建监听
            public void onClick(View v) {
                String strTmp = "停止充电中 。。。";
                stopChargingBtn.setText(strTmp);

            }
        });

        int voltage = 100;
        int current = 0;
        int residual_capacity = 30;
        int temperature = 50;
        int status = 0;
        Battery battery = new Battery(voltage, current, residual_capacity, temperature, status);
        battery.getClass();

        TextView charging_tv = (TextView) findViewById(R.id.charging_tv);
        String charging_text = "当前充电方式：" + "";
        charging_text+="已充电时间："+" ";
        charging_text+="还需时间："+" ";

        charging_tv.setText(charging_text);

        // Handle next
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "to ChargingEndActivity");
                Intent intent = new Intent();
                intent.setClass(ChargingActivity.this, ChargingEndActivity.class);
                ChargingActivity.this.startActivity(intent);

            }
        });
        }

    }
