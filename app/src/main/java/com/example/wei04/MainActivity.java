package com.example.wei04;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.tv);
    }

    public void test1(View view){
        MyThread mt1=new MyThread("A");
        MyThread mt2=new MyThread("B");
        mt1.start();
        mt2.start();
        Log.v("wei","test()");
    }

    private class MyThread extends Thread{
        String name;
        MyThread(String name){this.name=name;}
        @Override
        public void run() {
            for(int i=0;i<10;i++){
                Log.v("wei",name+":i="+i);
//              tv.setText(name+":i"+i);

                Message message=new Message();
                Bundle data=new Bundle();
                data.putCharSequence("data",name+":i="+i);
                message.setData(data);
                uiHandler.sendMessage(message);
                try {
                    Thread.sleep(500);
                }catch(Exception e){

                }
            }
        }
    }

    private UIHandler uiHandler = new UIHandler();

    private class UIHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            CharSequence data= msg.getData().getCharSequence("data","no data");
            tv.setText(data);
        }
    }

    private Timer timer=new Timer();
    private class MyTask extends TimerTask{
        int i;
        @Override
        public void run() {
            Log.v("wei","i="+i++);
        }
    }

    public void test2(View view){
        timer.schedule(new MyTask(),1*1000,1*1000);
    }

    public void test3(View view){}
}
