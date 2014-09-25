package com.example.soaprpg;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.util.FPSLogger;
import org.andengine.ui.activity.BaseGameActivity;

import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.Log;

public class MainActivity extends BaseGameActivity {

    // The following constants will be used to define the width and height
    // of our game's camera view
    private static final String TAG = "SOAP Activity";
    
    private final int            SCREEN_WIDTH = 1920;
    private final int            SCREEN_HEIGHT = 1080;
    private Camera               mCamera;
    private Scene                mScene;

    @Override
    public EngineOptions onCreateEngineOptions() {
        Log.v(TAG, "CONSTRUCT 5");
        mCamera = new Camera(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        // Declare & Define our engine options to be applied to our Engine object
        EngineOptions engineOptions = new EngineOptions(true,
               ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(SCREEN_WIDTH, SCREEN_HEIGHT),
               mCamera);

        engineOptions.setWakeLockOptions(WakeLockOptions.SCREEN_ON);

        return engineOptions;
    }

    @Override
    public void onCreateResources(
           OnCreateResourcesCallback pOnCreateResourcesCallback) {
        pOnCreateResourcesCallback.onCreateResourcesFinished();
    }

    @Override
    public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) {
        this.mEngine.registerUpdateHandler(new FPSLogger());
        mScene = new Scene();
        mScene.setBackground(new Background(0.09804f, 0.6274f, 0.8784f));

        pOnCreateSceneCallback.onCreateSceneFinished(mScene);
    }

    @Override
    public void onPopulateScene(Scene pScene,
           OnPopulateSceneCallback pOnPopulateSceneCallback) {
        pOnPopulateSceneCallback.onPopulateSceneFinished();
    }
}