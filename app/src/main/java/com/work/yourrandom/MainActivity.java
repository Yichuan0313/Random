package com.work.yourrandom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 随机数生成
        Button bt1 = (Button) findViewById(R.id.bt1);
        bt1.setOnClickListener(v -> {
            Intent pg1 = new Intent(MainActivity.this,pg1.class);
            startActivity(pg1);
        });
        // 一言
        Button bt2 = (Button) findViewById(R.id.bt2);
        bt2.setOnClickListener(v -> {
            Intent pg2 = new Intent(MainActivity.this, pg2.class);
            startActivity(pg2);
        });
        // 一个翻译
        Button bt3 = (Button) findViewById(R.id.bt3);
        bt3.setOnClickListener(v -> {
            Intent pg3 = new Intent(MainActivity.this, pg3.class);
            startActivity(pg3);
        });
}
}