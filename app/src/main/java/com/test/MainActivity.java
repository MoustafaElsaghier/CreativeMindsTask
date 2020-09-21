package com.test;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.test.Activities.ReposListActivity;
import com.test.Activities.UiTaskActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.apiTask).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ReposListActivity.class)));
        findViewById(R.id.uiTask).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, UiTaskActivity.class)));
    }
}