package com.pp.androidservices.intent_service;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyIntentService extends IntentService {

    private static final String TAG = "my_intent_service";
    private String data;

    public MyIntentService() {
        super("Default");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: MyIntentService");
    }

    @Override
    public void onStart( Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.d(TAG, "onStart: MyIntentService");
    }

    @Override
    public int onStartCommand( Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: MyIntentService flag"+flags+" startId"+startId);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandleIntent: MyIntentService");
        data = intent.getStringExtra("data");
        for(int i=0;i<10;i++){
            Log.d(TAG, "run: "+i+" Data "+data);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: MyIntentService");
        return super.onBind(intent);
    }
}
