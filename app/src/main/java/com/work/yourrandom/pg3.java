package com.work.yourrandom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class pg3 extends AppCompatActivity implements Runnable{

    public Handler handler;
    private TextView translation;
    private EditText input;
    private static final String TAG = "pg3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg3);

    }

    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Message msg=handler.obtainMessage(5);
        try {
            input = (EditText) findViewById(R.id.input);
            String input_word = input.getText().toString();
            String url = "http://dict.kekenet.com/en/" + input_word;
            Document doc = Jsoup.connect(url).get();
            Elements flag  = doc.select("ul:matches(没有找到与您查询的)");
            if (!flag.isEmpty()){
                msg.obj= "没有找到与您查询的"+input.getText().toString()+"相符的字词。";
            }else {
                Element word_phn = doc.getElementsByClass("phn").get(0);
                String phn = word_phn.text();
                Element wordm = doc.getElementById("wordm");
                String en_ch = wordm.html();
                Element words = doc.getElementById("s_ul");
                Elements divs = words.getElementsByTag("div");
                String sentences = "";
                for(Element div : divs){
                    sentences += div.text()+"<br><br>";
                }
                String s = input_word + "&nbsp" +phn + "<br>"+ en_ch + "<br>" + sentences;
                s = s.replaceAll(input_word,"<b>"+input_word+"</b>");
                msg.obj= s;
            }
            handler.sendMessage(msg);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void myClick(View view) {
        try {
            Thread t=new Thread(this);
            t.start();
            handler=new Handler(Looper.myLooper()){
                public void handleMessage(Message msg){
                    if(msg.what==5){
                        String s = (String) msg.obj;
                        Log.i(TAG, "handleMessage: 获取了翻译");
                        translation = (TextView) findViewById(R.id.translation);
                        translation.setText(Html.fromHtml(s));
                    }
                    super.handleMessage(msg);
                }
            };

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}