package com.geektech.android3_hw3.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.geektech.android3_hw3.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addFragment();
    }

    private void addFragment() {
        getSupportFragmentManager().beginTransaction().add(R.id.container, new HomeFragment()).commit();
    }
}