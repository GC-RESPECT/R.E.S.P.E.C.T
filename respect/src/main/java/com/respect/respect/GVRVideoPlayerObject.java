package com.respect.respect;

import android.media.MediaPlayer;

import org.gearvrf.GVRContext;
import org.gearvrf.GVRMesh;
import org.gearvrf.GVRSceneObject;
import org.gearvrf.scene_objects.GVRSphereSceneObject;
import org.gearvrf.scene_objects.GVRVideoSceneObject;
import org.gearvrf.scene_objects.GVRVideoSceneObjectPlayer;

import java.io.IOException;

public class GVRVideoPlayerObject extends GVRSceneObject {
    private final GVRVideoSceneObjectPlayer<?> mPlayer;
    private final MediaPlayer mMediaPlayer;
    private final GVRContext gvrContext;

    public GVRVideoPlayerObject(GVRContext gvrContext) {
        super(gvrContext);

        this.gvrContext = gvrContext;

        GVRSphereSceneObject sphere = new GVRSphereSceneObject(gvrContext, 72, 144, false);
        GVRMesh mesh = sphere.getRenderData().getMesh();

        mMediaPlayer = new MediaPlayer();
        mPlayer = GVRVideoSceneObject.makePlayerInstance(mMediaPlayer);

        GVRVideoSceneObject video = new GVRVideoSceneObject(gvrContext, mesh, mPlayer, GVRVideoSceneObject.GVRVideoType.MONO);

        video.getTransform().setScale(100f, 100f, 100f);

        addChildObject(video);
    }

    public void loadVideo(String path) {
        try {
            mMediaPlayer.setDataSource(path);
            mMediaPlayer.prepare();
        } catch (IOException | IllegalStateException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if(mMediaPlayer != null) {
            mMediaPlayer.start();
        }
    }

    public void setLooping(boolean value) {
        mMediaPlayer.setLooping(value);
    }

    public void onPause() {
        mMediaPlayer.pause();
    }

    public void onResume() {
        mMediaPlayer.start();
    }

    public void setPlaySpeed(double speed) {
        mMediaPlayer.setPlaybackParams(mMediaPlayer.getPlaybackParams().setSpeed((float)speed));
    }
}
