package com.pp.androidservices;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.pp.androidservices.binder_class.BinderService;

public class BinderServiceActivity extends AppCompatActivity {

    private static final String TAG = "binder_service";
    private BinderService serv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder_service);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected: ");
            BinderService.LocalBinder localBinder = (BinderService.LocalBinder) service;
            serv = localBinder.getService();
            Toast.makeText(serv, "Service Connected", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            serv = null;
            Log.d(TAG, "onServiceDisconnected: ");
            Toast.makeText(serv, "Service Disconnected", Toast.LENGTH_SHORT).show();
        }
    };

    public void clickBind(View view) {
        Log.d(TAG, "clickBind: ");
        Intent intent = new Intent(getApplicationContext(), BinderService.class);
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
    }

    public void clickGetSum(View view) {
        Log.d(TAG, "clickGetSum: ");
        if(serv != null)
            Toast.makeText(serv, serv.getSum(1,2)+"", Toast.LENGTH_SHORT).show();
    }

    public void clickGetMessage(View view) {
        Log.d(TAG, "clickGetMessage: ");
        if(serv != null)
            Toast.makeText(serv, serv.getMessage(), Toast.LENGTH_SHORT).show();
    }

    public void clickUnBind(View view) {
        Log.d(TAG, "clickUnBind: ");
        if(serv != null)
            unbindService(serviceConnection);
    }
}
