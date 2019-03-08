package com.pp.androidservices.binder_class;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class BinderService extends Service {

    private static final String TAG = "binder_service";

    private final IBinder binder = new LocalBinder();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: BinderService");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.d(TAG, "onStart: BinderService");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: BinderService");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: BinderService");
        return binder;
    }

    public class LocalBinder extends Binder {
        public BinderService getService(){
            Log.d(TAG, "getService: BinderService");
            return BinderService.this;
        }
    }

    public String getMessage(){
        Log.d(TAG, "getMessage: BinderService");
        return "This is a sample message";
    }

    public int getSum(int a, int b){
        Log.d(TAG, "getSum: BinderService");
        return a+b;
    }
}
