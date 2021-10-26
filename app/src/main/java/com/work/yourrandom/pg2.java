package com.work.yourrandom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class pg2 extends AppCompatActivity implements Runnable{

    public Handler handler;
    private TextView result;
    private static final String TAG = "pg2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg2);

    }

    public void onclick(View view) throws IOException {
        try {
            Thread t=new Thread(this);
            t.start();
            handler=new Handler(Looper.myLooper()){
                public void handleMessage(Message msg){
                    if(msg.what==7){
                        String s = (String) msg.obj;
                        Log.i(TAG, "handleMessage: "+s);
                        result = (TextView) findViewById(R.id.sentence);
                        result.setText(s);
                    }
                    super.handleMessage(msg);
                }
            };

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Message msg=handler.obtainMessage(7);
        try {
            Document doc = Jsoup.connect("https://hitokoto.cn/").get();
            Element hitokoto = doc.getElementById("hitokoto");
            msg.obj= hitokoto.text();
            handler.sendMessage(msg);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}