package com.example.soaprpg;

import org.andengine.engine.Engine;
import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.input.touch.TouchEvent;

import android.util.Log;

import com.example.soaprpg.ResourceManager;

public class ObjectFactory {

    private static final String TAG = "SOAP Activity";
    
    // Return a new CharacterObject with the defined 'x' and 'y' position.
    public static CharacterObject createCharacterObject(final int pX, final int pY, Engine pEngine){
        return new CharacterObject(pX, pY, pEngine);
    }

    // CharacterObject class
    public static class CharacterObject {
        public AnimatedSprite         mCharacterSprite;
        private PhysicsHandler        mPhysicsHandler;
        private float                 mMoveToX;
        private float                 mMoveToY;
        
        public CharacterObject(int pX, int pY, Engine pEngine) {

            mCharacterSprite = new AnimatedSprite(pX, pY, ResourceManager.getInstance().mCharacterTextureRegion, pEngine.getVertexBufferObjectManager())
            {
                @Override
                protected void onManagedUpdate(final float pSecondsElapsed) {
                    Log.d(TAG, "This mX:" + this.mX + " This mY:" + this.mY);
                    if(Math.abs(this.mX - mMoveToX) < 5f && Math.abs(this.mY - mMoveToY) < 5f) {
                        mPhysicsHandler.setVelocity(0, 0);
                        mCharacterSprite.stopAnimation(0);
                    }

                    super.onManagedUpdate(pSecondsElapsed);
                }
            };

            mPhysicsHandler = new PhysicsHandler(mCharacterSprite);
            mCharacterSprite.registerUpdateHandler(mPhysicsHandler);
            mCharacterSprite.setScale(4);
        }

        public void move(TouchEvent touchEvent)
        {
            mCharacterSprite.animate(new long[] { 100, 100, 100, 100, 100, 100 }, 0, 5, true);
            Log.d(TAG, "Velocity! X:" + touchEvent.getX() + " Y:" + touchEvent.getY());
            mMoveToX = touchEvent.getX();
            mMoveToY = touchEvent.getY();
            float x0 = (float) ((mMoveToX - mCharacterSprite.getX()) / (Math.pow(Math.pow(mMoveToX - mCharacterSprite.getX(), 2.0) +  Math.pow(mMoveToY - mCharacterSprite.getY(), 2.0), 0.5)));
            float y0 = (float) ((mMoveToY - mCharacterSprite.getY()) / (Math.pow(Math.pow(mMoveToX - mCharacterSprite.getX(), 2.0) +  Math.pow(mMoveToY - mCharacterSprite.getY(), 2.0), 0.5)));
            x0 *= 500;
            y0 *= 500;
            Log.d(TAG, "Velocity! X0:" + x0 + " Y0:" + y0);

            mPhysicsHandler.setVelocity(x0, y0);
        }
    }
}
