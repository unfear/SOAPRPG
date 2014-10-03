package com.example.soaprpg;

import org.andengine.engine.Engine;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.TiledTextureRegion;

import android.content.Context;

public class ResourceManager {

    public TiledTextureRegion               mCharacterTextureRegion;

    private static ResourceManager          INSTANCE;
    private BuildableBitmapTextureAtlas     mBitmapTextureAtlas;

    ResourceManager(){
        // The constructor is of no use to us
    }

    public synchronized static ResourceManager getInstance(){
        if(INSTANCE == null){
            INSTANCE = new ResourceManager();
        }
        return INSTANCE;
    }

    public synchronized void loadGameTextures(Engine pEngine, Context pContext){
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");

        mBitmapTextureAtlas = new BuildableBitmapTextureAtlas(pEngine.getTextureManager(), 1920, 1080, 
                                                            TextureOptions.BILINEAR);

        mCharacterTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mBitmapTextureAtlas, pContext,
                                                                                            "bronann_walk.png", 6, 4);
        try {
            mBitmapTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 0));
        } catch (TextureAtlasBuilderException e) {
            e.printStackTrace();
        }

        mBitmapTextureAtlas.load();
    }

    public synchronized void unloadGameTextures(){
        // call unload to remove the corresponding texture atlas from memory
        // FIXME: check whether all textures have been unloaded or not
        mBitmapTextureAtlas.unload();
        System.gc();
    }

}
