package com.didikee.gifparser.files;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by didik 
 * Created time 2016/12/12
 * Description: 
 */

public class FileUtil {


    public static Boolean checkFileType(String filePath) {
        Log.e("test", "filePath: " + filePath);
        if (TextUtils.isEmpty(filePath) || !filePath.contains(".")) {
            return null;
        }
        String[] split = filePath.split("\\.");
        Log.e("test", "1: " + split[0] + "     2: " + split[1]);
        String ext = split[1];
        if (TextUtils.isEmpty(ext)) {
            return null;
        }
        if (ext.equalsIgnoreCase("gif")) {
            return true;
        }

        if (ext.equalsIgnoreCase("png") || ext.equalsIgnoreCase("jpg") || ext.equalsIgnoreCase
                ("jpeg")) {
            return false;
        }

        return null;
    }
}
