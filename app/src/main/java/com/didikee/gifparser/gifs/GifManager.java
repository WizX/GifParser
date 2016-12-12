package com.didikee.gifparser.gifs;

import android.graphics.Bitmap;
import android.support.annotation.IntegerRes;

import com.didikee.gifparser.gifs.gif.GifDecoder;
import com.didikee.gifparser.gifs.interf.IGifManager;

import java.io.File;
import java.io.InputStream;

/**
 * Created by didik on 2016/12/1.
 */

public class GifManager implements IGifManager {

    private InputStream gifInputStream;
    private int frameCount;
    private GifDecoder gifDecoder;

    public GifManager(@IntegerRes int gifRawId) {

    }

    public GifManager(InputStream gifInputStream){
        this.gifInputStream=gifInputStream;
        initGifDecoder();
    }

    private void initGifDecoder() {
        gifDecoder = new GifDecoder();
        gifDecoder.read(gifInputStream, 10240);
        frameCount = gifDecoder.getFrameCount();
    }

    @Override
    public Bitmap getGifFrame(int index) {
        return null;
    }

    @Override
    public void parserGif2Frame(File target, int quality) {

    }

    @Override
    public void parserGif2Frame(String targetPath, int quality) {

    }

    @Override
    public int getGifCount() {
        return frameCount;
    }
}
