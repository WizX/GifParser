package com.didikee.gifparser.files;

import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;

import java.io.File;

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

    /**
     * 获取文件名 与 文件所在目录
     * @param filePath 文件绝对路径
     * @return 文件名 与 文件所在目录的绝对路径
     */
    public static Pair<String,String> getFilePathDetail(String filePath){
        if (TextUtils.isEmpty(filePath) || !filePath.contains(".")) {
            return null;
        }
        int lastIndex = filePath.lastIndexOf(".");
        String first=filePath.substring(0,lastIndex);
        String second=filePath.substring(lastIndex+1,filePath.length());
        return new Pair<>(first,second);
    }

    /**
     * 获取文件路径名称:.../gif/demo/save
     * 获取文件名: demo.jpg --> demo
     * @param filePath
     * @return
     */
    public static Pair<String,String> getFileAndDirName(String filePath){
        File check=new File(filePath);
        if (check.isDirectory()){
            return new Pair<>(filePath,"");
        }
        String parent = check.getParent();
        String name = check.getName();

        if (name.contains(".")){
            int lastIndex = name.lastIndexOf(".");
            name=name.substring(0,lastIndex);
        }
        return new Pair<>(parent,name);
    }
}
