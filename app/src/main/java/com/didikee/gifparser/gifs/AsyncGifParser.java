package com.didikee.gifparser.gifs;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;

import com.didikee.gifparser.utils.FileUtil;
import com.didikee.gifparser.gifs.gif.GifDecoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by didik on 2016/12/1.
 */

public class AsyncGifParser extends AsyncTask<InputStream, Integer, Void> {

    protected File targetFile;
    protected final String parserPath;
    private int quality = 100;


    public AsyncGifParser(String parserPath) {
        this.parserPath = parserPath;
        makeSaveFile();
    }

    private void makeSaveFile() {
        Pair<String, String> filePathDetail = FileUtil.getFileAndDirName(parserPath);
        String targetStringPath = filePathDetail.first + File.separatorChar + filePathDetail
                .second + File.separatorChar;
        File file = new File(targetStringPath);
        if (!file.exists()) {
            boolean mkdirs = file.mkdirs();
            if (!mkdirs) {
                Log.e("test", "失败了,创建文件夹");
            }
        }
        targetFile = file;
    }

    public void setQuality(int quality) {
        if (quality > 100 || quality <= 0) return;
        this.quality = quality;
    }

    @Override
    protected Void doInBackground(InputStream... inputStreams) {
        if (inputStreams == null || inputStreams.length == 0) return null;
        parserGif2Frame(inputStreams[0]);
        return null;
    }

    private void parserGif2Frame(InputStream target) {
        GifDecoder gifDecoder = new GifDecoder();
        gifDecoder.read(target, 10240);
        int frameCount = gifDecoder.getFrameCount();
        publishProgress(new Integer[]{frameCount, 0});

        Bitmap tmpBitmap;
        for (int i = 0; i < frameCount; i++) {
            gifDecoder.setFrameIndex(i);
            publishProgress(i + 1);
            int currentFrameIndex = gifDecoder.getCurrentFrameIndex();
            Log.d("test", "currentFrameIndex: " + currentFrameIndex);
            tmpBitmap = gifDecoder.getNextFrame();
            saveBitmap(tmpBitmap, currentFrameIndex);
        }
    }

    private void saveBitmap(Bitmap bm, int index) {
        if (bm == null) return;
        File f = new File(targetFile, fixNum(index + 1) + ".png");
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG, quality, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("test", "error: " + e.toString());
        }
    }

    /**
     * 文件名统一为 01 02 11 12 ...
     * @param index 没处理前 1,处理后 01;
     * @return 11
     */
    private String fixNum(int index) {
        if (index < 10) {
            return "0" + index;
        } else {
            return index + "";
        }
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected void onCancelled(Void aVoid) {
        super.onCancelled(aVoid);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
}
