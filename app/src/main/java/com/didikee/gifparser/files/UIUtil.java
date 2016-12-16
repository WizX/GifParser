package com.didikee.gifparser.files;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.WindowManager;

/**
 * Created by didik 
 * Created time 2016/12/16
 * Description: 
 */

public class UIUtil {
    public static Pair<Integer, Integer> getWindowPixels(@NonNull Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics =new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        return new Pair<Integer,Integer>(displayMetrics.widthPixels,displayMetrics.heightPixels);
    }
}
