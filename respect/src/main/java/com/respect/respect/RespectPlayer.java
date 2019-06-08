package com.respect.respect;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import org.gearvrf.GVRActivity;
import org.gearvrf.GVRAndroidResource;
import org.gearvrf.GVRContext;
import org.gearvrf.GVRMain;
import org.gearvrf.GVRSceneObject;
import org.gearvrf.scene_objects.GVRTextViewSceneObject;

import java.util.Locale;

public class RespectPlayer extends GVRMain implements SensorEventListener {
    private GVRVideoPlayerObject mPlayerObj = null;
    private SensorManager mSensorManager;
    private Sensor stepSensor;
    private GVRTextViewSceneObject veloText, distText;
    private long steps, initTime, lastTime, curTime;
    private String path;

    public RespectPlayer(GVRActivity context, String path) {
        this.path = path;
        steps = 0;

        initTime = System.currentTimeMillis();
        lastTime = System.currentTimeMillis();

        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        stepSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        mSensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    public void onInit(GVRContext gvrContext) throws Throwable {
        GVRSceneObject headTracker = new GVRSceneObject(gvrContext, gvrContext.createQuad(.1f, .1f), gvrContext.getAssetLoader().loadTexture(new GVRAndroidResource(gvrContext, R.drawable.cursor)));
        headTracker.getTransform().setPosition(0.0f, 0.0f, -1.0f);
        headTracker.getRenderData().setDepthTest(false);
        headTracker.getRenderData().setRenderingOrder(100000);

        mPlayerObj = new GVRVideoPlayerObject(gvrContext);
        mPlayerObj.loadVideo(path);
        mPlayerObj.setLooping(true);
        mPlayerObj.play();

        gvrContext.getMainScene().addSceneObject(mPlayerObj);

        veloText = new GVRTextViewSceneObject(gvrContext, 10.0f, 1.5f, "평균시속 : ");
        veloText.getTransform().setPosition(-1.0f, 4.0f, -10.0f);
        veloText.getRenderData().setDepthTest(false);
        veloText.getRenderData().setRenderingOrder(100000);
        veloText.setTextColor(Color.WHITE);
        veloText.setTextSize(7);
        veloText.setBackgroundColor(Color.TRANSPARENT);
        gvrContext.getMainScene().getMainCameraRig().addChildObject(veloText);

        distText = new GVRTextViewSceneObject(gvrContext, 10.0f, 1.5f, "이동거리 :");
        distText.getTransform().setPosition(-1.0f, 3.0f, -10.0f);
        distText.getRenderData().setDepthTest(false);
        distText.getRenderData().setRenderingOrder(100000);
        distText.setTextColor(Color.WHITE);
        distText.setTextSize(7);
        distText.setBackgroundColor(Color.TRANSPARENT);
        gvrContext.getMainScene().getMainCameraRig().addChildObject(distText);
    }

    @Override
    public SplashMode getSplashMode() {
        return SplashMode.NONE;
    }

    public void onResume() {
        if(mPlayerObj != null) {
            mPlayerObj.onResume();
            mSensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_FASTEST);
        }
    }

    public void onPause() {
        if(mPlayerObj != null) {
            mSensorManager.unregisterListener(this, stepSensor);
            mPlayerObj.onPause();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;

        if (sensor.getType() == Sensor.TYPE_STEP_DETECTOR)
            steps++;


        curTime = System.currentTimeMillis();
        long duration = curTime - lastTime;
        lastTime = curTime;

        if (veloText != null)
            veloText.setText("평균속도 : " + String.format(Locale.KOREA, "%.1f", getVelocity()) + "m/s");
        if (distText != null)
            distText.setText("이동거리 : " + String.format(Locale.KOREA, "%,.1f", getDistanceWalk()) + "m");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) { }

    private float getDistanceWalk() {
        return (float) (steps * 0.76);
    }

    private float getVelocity() {
        curTime = System.currentTimeMillis();
        float time = (float) ((curTime - initTime) / 1000.0);
        return getDistanceWalk() / time;
    }

    public void setFilePath(String path) {
        this.path = path;
    }

    public void setPlayerSpeed(double speed) {
        mPlayerObj.setPlaySpeed(speed);
    }
}
