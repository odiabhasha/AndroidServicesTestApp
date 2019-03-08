package com.pp.androidservices.messanger_class;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

public class MyMessengerService extends Service {

    private static final String TAG = "my_messenger_service";
    private Messenger messenger;

    private class MyHandler extends Handler {

        private Context context;

        public MyHandler(Context context) {
            this.context = context;
        }

        @Override
        public void handleMessage(Message msg) {
            Log.d(TAG, "handleMessage: MyMessengerService");
            switch (msg.what) {
                case 1:
                    Toast.makeText(context, "handleMessage", Toast.LENGTH_SHORT).show();
                    try {
                        Bundle bundle = msg.getData();
                        Message message = new Message();
                        message.obj = bundle.getString("rec")+" Yes loud and clear";
                        msg.replyTo.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: MyMessengerService");
        messenger = new Messenger(new MyHandler(getApplicationContext()));
        return messenger.getBinder();
    }
}
