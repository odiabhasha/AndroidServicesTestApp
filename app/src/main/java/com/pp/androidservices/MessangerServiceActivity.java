package com.pp.androidservices;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.pp.androidservices.binder_class.BinderService;
import com.pp.androidservices.messanger_class.MyMessengerService;

public class MessangerServiceActivity extends AppCompatActivity {

    private static final String TAG = "my_messenger_service";

    private Messenger messenger;


    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            messenger = new Messenger(service);
            Toast.makeText(MessangerServiceActivity.this, "Connected", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            messenger = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messanger_service);
    }

    public void clickBind(View view) {
        Log.d(TAG, "clickBind: ");
        Intent intent = new Intent(getApplicationContext(), MyMessengerService.class);
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
    }

    public void clickGetSum(View view) {
        Log.d(TAG, "clickGetSum: ");
        if(messenger != null){
            Message msg = Message.obtain(null, 2, 1, 2);
            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                Log.d(TAG, "clickGetMessage: "+e.toString());
            }
        }
    }

    public void clickGetMessage(View view) {
        Log.d(TAG, "clickGetMessage: ");
        if(messenger != null){
            Message message = Message.obtain(null, 1, 0, 0);
            message.replyTo = replyMessenger;

            Bundle bundle = new Bundle();
            bundle.putString("rec", "Hi, you hear me");
            message.setData(bundle);
            try {
                messenger.send(message);
            } catch (RemoteException e) {
                Log.d(TAG, "clickGetMessage: "+e.toString());
            }
        }
    }

    public void clickUnBind(View view) {
        Log.d(TAG, "clickUnBind: ");
        if(messenger != null)
            unbindService(serviceConnection);
    }

    Messenger replyMessenger = new Messenger(new HandlerReplyMsg());

    class HandlerReplyMsg extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String recdMessage = msg.obj.toString(); //msg received from service
            Toast.makeText(getApplicationContext(), "Reply "+ recdMessage, Toast.LENGTH_SHORT).show();
        }
    }
}
