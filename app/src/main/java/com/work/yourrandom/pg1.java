package com.work.yourrandom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Random;

public class pg1 extends AppCompatActivity {

    private SeekBar sb_normal;
    private TextView txt_cur;
    private Button generate;
    private EditText input_min;
    private EditText input_max;
    private TextView result;
    private static final String TAG = "pg1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg1);
        bindViews();

    }

    private void bindViews() {
        // bind seekbar
        sb_normal = (SeekBar) findViewById(R.id.skb);
        txt_cur = (TextView) findViewById(R.id.skbr);
        sb_normal.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txt_cur.setText(progress + "  / 10");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.i(TAG, "onStartTrackingTouch: 触摸SeekBar");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.i(TAG, "onStartTrackingTouch: 放开SeekBar");
            }
        });

        // get input and generate result
        input_min = (EditText) findViewById(R.id.min_input);
        input_max = (EditText) findViewById(R.id.max_input);
        result = (TextView) findViewById(R.id.result);
        generate = (Button) findViewById(R.id.generate);
        Random random = new Random(42);
        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int min = Integer.parseInt(input_min.getText().toString());
                int max = Integer.parseInt(input_max.getText().toString());
                int num = sb_normal.getProgress();
                result.setText("");
                Log.i(TAG, "onClick: 清空结果");
                for (int i = 0; i < num; i++) {
                    result.append((min + random.nextInt(max - min)) + "\n");
                }
                Log.i(TAG, "onClick: 生成了"+num+"个随机数");
            }
        });

    }
}