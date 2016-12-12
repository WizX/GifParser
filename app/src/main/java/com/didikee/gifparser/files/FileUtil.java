package com.didikee.gifparser.files;

import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;

/**
 * Created by didik 
 * Created time 2016/12/12
 * Description: 
 */

public class FileUtil {


    public static Boolean checkFileType(String filePath) {
        Log.e("test", "filePath: " + filePath);
        Pair<String, String> filePathDetail = getFilePathDetail(filePath);
        if (filePathDetail == null){
            return null;
        }
        Log.e("test", "first: " + filePathDetail.first + "  ||second: " + filePathDetail.second);
        String ext = filePathDetail.second;
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

    public static Pair<String,String> getFilePathDetail(String filePath){
        if (TextUtils.isEmpty(filePath) || !filePath.contains(".")) {
            return null;
        }
        int lastIndex = filePath.lastIndexOf(".");
        String first=filePath.substring(0,lastIndex+1);
        String second=filePath.substring(lastIndex+1,filePath.length());
        return new Pair<>(first,second);
    }
}
