package com.respect.respectdemo;

import android.os.Bundle;

import com.respect.respect.RespectPlayer;

import org.gearvrf.GVRActivity;

public class MainActivity extends GVRActivity {
    RespectPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPlayer = new RespectPlayer(this, "sdcard/DCIM/Gear 360/SAM_100_0528.mp4");
    }

    @Override
    protected void onResume() {
        super.onResume();

        mPlayer.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        mPlayer.onPause();
    }
}
