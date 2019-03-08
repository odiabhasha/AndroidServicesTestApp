package com.pp.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.pp.androidservices.ICommon;

public class AidlClientActivity extends AppCompatActivity {

    private static final String TAG = "my_aidl_service_client";
    ICommon common;

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            common = ICommon.Stub.asInterface(service);
            Toast.makeText(AidlClientActivity.this, "Connected", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            common = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl_client);
    }

    public void clickGetMessage(View view) {
        if(common != null) {
            try {
                Toast.makeText(this, ""+common.getMessage(), Toast.LENGTH_SHORT).show();
            } catch (RemoteException e) {
                Log.d(TAG, "clickGetSum: "+e);
            }
        }
    }

    public void clickBind(View view) {
        Intent intent = new Intent("com.pp.androidservices.aidl");
        intent.setPackage("com.pp.androidservices");
        bindService(intent,conn, Context.BIND_AUTO_CREATE);
    }

    public void clickGetSum(View view) {
        if(common != null) {
            try {
                Toast.makeText(this, ""+common.calc(1,2), Toast.LENGTH_SHORT).show();
            } catch (RemoteException e) {
                Log.d(TAG, "clickGetSum: "+e);
            }
        }
    }

    public void clickUnBind(View view) {
        if(common != null)
            unbindService(conn);
    }
}
