package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.android.android_me.R;

/**
 * Created by ${farhanarnob} on ${06-Oct-16}.
 */

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onImageSelected(int position) {
        Toast.makeText(getApplicationContext(), "List item clicked. Position:" + position,
                Toast.LENGTH_SHORT)
                .show();
    }
}
