package com.pp.androidservices.aidl_service_class;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.pp.androidservices.ICommon;
import com.pp.androidservices.binder_class.BinderService;

public class MyAidlService extends Service {
    private static final String TAG = "my_aidl_service";

    MyImpl binder = new MyImpl();


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: MyAidlService");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.d(TAG, "onStart: MyAidlService");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: MyAidlService");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: BinderService");
        return binder;
    }

    private class MyImpl extends ICommon.Stub{

        @Override
        public int calc(int num1, int num2) throws RemoteException {
            return num1+num2;
        }

        @Override
        public String getMessage() throws RemoteException {
            return "This is AIDL Message String";
        }
    }
}
