package com.work.yourrandom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //config exchange rate
        Button bt = (Button) findViewById(R.id.bt);
        bt.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            Intent pg1 = new Intent(MainActivity.this,pg1.class);
            startActivity(pg1);
        });
}
}