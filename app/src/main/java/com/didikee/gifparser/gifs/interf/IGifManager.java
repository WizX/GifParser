package com.didikee.gifparser.gifs.interf;

import android.graphics.Bitmap;
import android.support.annotation.IntegerRes;

import java.io.File;
import java.io.InputStream;

/**
 * Created by didik on 2016/12/1.
 */

public interface IGifManager {
    Bitmap getGifFrame(int index);
    void setDataResource(InputStream inputStream);
    void setDataResource(@IntegerRes int resId);
    void parserGif2Frame(File target,int quality);
    void parserGif2Frame(String targetPath,int quality);
}
