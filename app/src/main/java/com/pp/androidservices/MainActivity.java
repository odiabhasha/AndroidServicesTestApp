package com.pp.androidservices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickService(View view) {
        startActivity(new Intent(getApplicationContext(),ServiceActivity.class));
    }

    public void clickIntentService(View view) {
        startActivity(new Intent(getApplicationContext(),IntentServiceActivity.class));
    }

    public void clickBinderClass(View view) {
        startActivity(new Intent(getApplicationContext(),BinderServiceActivity.class));
    }

    public void clickMessangerClass(View view) {
        startActivity(new Intent(getApplicationContext(),MessangerServiceActivity.class));
    }

    public void clickAidl(View view) {
        startActivity(new Intent(getApplicationContext(),AidlServiceActivity.class));
    }
}
