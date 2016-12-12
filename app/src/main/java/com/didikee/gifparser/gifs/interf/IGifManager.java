package com.didikee.gifparser.gifs.interf;

import android.graphics.Bitmap;

import java.io.File;

/**
 * Created by didik on 2016/12/1.
 */

public interface IGifManager {
    Bitmap getGifFrame(int index);
    void parserGif2Frame(File target,int quality);
    void parserGif2Frame(String targetPath,int quality);
    int getGifCount();
}
