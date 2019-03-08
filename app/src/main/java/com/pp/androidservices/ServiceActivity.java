package com.pp.androidservices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.pp.androidservices.service.MyService;

public class ServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
    }

    public void clickStartBtn(View view) {
        Log.d("my_service", "clickStartBtn: ");
        Intent intent = new Intent(getApplicationContext(), MyService.class);
        intent.putExtra("data",((EditText)findViewById(R.id.editText)).getText().toString());
        startService(intent);
    }

    /*
    1. onStartCommand gets called, flag 0 startId 1
    2. start again, flag 0, startId 2
    3. Lifecycle Sequence: onCreate(), onStartCommand(), onStart()
    4. Everytime startService gets called , new service will not be created,
    only onStartCommand will gets executed with new INTENT and new startId
     */
}
