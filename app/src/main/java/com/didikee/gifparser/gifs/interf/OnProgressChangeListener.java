package com.didikee.gifparser.gifs.interf;

/**
 * Created by didik on 2017/2/18.
 */

public interface OnProgressChangeListener {

    void prepare(int total);
    /**
     * gif 分解时的进度回调
     * @param current 当前帧
     * @param total 总帧数
     * @param percent 百分比
     */
    void onProgressChange(int current,int total,int percent);

    void onFinish(boolean error);
}
