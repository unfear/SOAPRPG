package com.example.soaprpg;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.util.FPSLogger;
import org.andengine.input.touch.TouchEvent;
import org.andengine.ui.activity.BaseGameActivity;

import com.example.soaprpg.ObjectFactory;
import com.example.soaprpg.ObjectFactory.CharacterObject;

import android.util.Log;

public class MainActivity extends BaseGameActivity implements IOnSceneTouchListener {

    private static final String TAG = "SOAP Activity";
    
    private final int            SCREEN_WIDTH = 1920;
    private final int            SCREEN_HEIGHT = 1080;
    private Camera               mCamera;
    private Scene                mScene;
    
    private CharacterObject      mCharacterObject;
    
    @Override
    public EngineOptions onCreateEngineOptions() {
        mCamera = new Camera(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        EngineOptions engineOptions = new EngineOptions(true,
               ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(SCREEN_WIDTH, SCREEN_HEIGHT),
               mCamera);

        engineOptions.setWakeLockOptions(WakeLockOptions.SCREEN_ON);

        return engineOptions;
    }

    @Override
    public void onCreateResources(
           OnCreateResourcesCallback pOnCreateResourcesCallback) {
        ResourceManager.getInstance().loadGameTextures(mEngine, this);
 
        pOnCreateResourcesCallback.onCreateResourcesFinished();
    }

    @Override
    public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) {
        this.mEngine.registerUpdateHandler(new FPSLogger());
        mScene = new Scene();
        mScene.setBackground(new Background(0.09804f, 0.6274f, 0.8784f));
        mScene.setOnSceneTouchListener(this);

        pOnCreateSceneCallback.onCreateSceneFinished(mScene);
    }

    @Override
    public void onPopulateScene(Scene pScene,
           OnPopulateSceneCallback pOnPopulateSceneCallback) {
        mCharacterObject = ObjectFactory.createCharacterObject(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, mEngine);
        mScene.registerTouchArea(mCharacterObject.mCharacterSprite);
        mScene.attachChild(mCharacterObject.mCharacterSprite);
        
        pOnPopulateSceneCallback.onPopulateSceneFinished();
    }

    // Implements touch listener for main screen touch
    @Override
    public boolean onSceneTouchEvent(Scene scene, TouchEvent touchEvent) {
        if (touchEvent.isActionDown()) {
            mCharacterObject.move(touchEvent);
        }
        return false;
    }

}
