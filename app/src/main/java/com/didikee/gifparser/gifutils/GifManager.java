package com.didikee.gifparser.gifutils;

import android.graphics.Bitmap;
import android.support.annotation.IntegerRes;

import java.io.File;
import java.io.InputStream;

/**
 * Created by didik on 2016/12/1.
 */

public class GifManager implements IGifManager{

    @Override
    public Bitmap getGifFrame(int index) {
        return null;
    }

    @Override
    public void setDataResource(InputStream inputStream) {

    }

    @Override
    public void setDataResource(@IntegerRes int resId) {

    }

    @Override
    public void parserGif2Frame(File target, int quality) {

    }

    @Override
    public void parserGif2Frame(String targetPath, int quality) {

    }
}
