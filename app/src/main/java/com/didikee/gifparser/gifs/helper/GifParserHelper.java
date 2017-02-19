package com.didikee.gifparser.gifs.helper;

import com.didikee.gifparser.gifs.AsyncGifParser;
import com.didikee.gifparser.gifs.interf.OnProgressChangeListener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by didik 
 * Created time 2016/12/16
 * Description: 
 */

public class GifParserHelper extends AsyncGifParser {

    private int total = 0;
    private OnProgressChangeListener progressChangeListener;

    public void setProgressChangeListener(OnProgressChangeListener progressChangeListener) {
        this.progressChangeListener = progressChangeListener;
    }

    /**
     * 需要解析的文件路径
     * @param parserPath 此路径需要你提前检验
     */
    public GifParserHelper(String parserPath) {
        super(parserPath);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        int progress = 0;
        int percent = 0;
        if (values.length == 2) {
            total = values[0];
            if (progressChangeListener!=null){
                progressChangeListener.prepare(total);
            }
        } else {
            try {
                progress = values[0];
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (total != 0) {
                percent = (int) (((progress * 1.0f) / total) * 100);
            }
        }
        if (progressChangeListener != null) {
            progressChangeListener.onProgressChange(progress, total, percent);
        }
        if (percent > 99 && progressChangeListener != null) {
            progressChangeListener.onFinish(false);
        }
    }

    /**
     * 开始解析
     */
    public void parser() {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(parserPath);
        } catch (FileNotFoundException e) {
            if (progressChangeListener != null) {
                progressChangeListener.onFinish(true);
            }
        }
        if (inputStream == null) {
            // Toast.makeText(context,"is is null",Toast.LENGTH_SHORT).show();
            if (progressChangeListener != null) {
                progressChangeListener.onFinish(true);
            }
            return;
        }
        execute(inputStream);
    }

    public String getSaveFileInfo(){
        if (targetFile!=null){
            return targetFile.getAbsolutePath()+ "/";
        }
        return "";
    }

}
