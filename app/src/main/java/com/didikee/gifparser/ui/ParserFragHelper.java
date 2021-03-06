package com.didikee.gifparser.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.didikee.gifparser.R;
import com.didikee.gifparser.gifs.GifImageView;
import com.didikee.gifparser.ui.frag.GifParserFragment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by didik on 2017/2/18.
 */

public class ParserFragHelper {

    private final FrameLayout fl_container;
    private final GifParserFragment frag;
    private final Context context;
    private final Resources res;

    public ParserFragHelper(@NonNull GifParserFragment frag, @NonNull FrameLayout container) {
        this.frag = frag;
        this.fl_container = container;
        this.context = frag.getContext();
        res = frag.getResources();

    }

    public void setDefaultFlContainer() {
        fl_container.removeAllViews();
        ImageView imageView=new ImageView(frag.getContext());
        FrameLayout.LayoutParams params=new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity= Gravity.CENTER;
        imageView.setClickable(true);
        imageView.setBackgroundResource(R.drawable.md_ripple_white);
        imageView.setImageResource(R.drawable.ic_add_file);
        imageView.setId(R.id.id_add_file);
        imageView.setOnClickListener(frag);
        fl_container.addView(imageView,params);
    }

    private void add2FlContainer(ImageView imageView) {
        fl_container.removeAllViews();
        imageView.setId(R.id.id_add_file);
        imageView.setOnClickListener(frag);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams
                .MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        params.gravity = Gravity.CENTER;
        fl_container.addView(imageView, params);
        if (imageView instanceof GifImageView) {
            ((GifImageView) imageView).startAnimation();
        }
    }
    public void start2ChooseFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写
        //intent.setType(“audio/*”); //选择音频
        //intent.setType(“video/*”); //选择视频 （mp4 3gp 是android支持的视频格式）
        //intent.setType(“video/*;image/*”);//同时选择视频和图片
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        frag.startActivityForResult(intent, 1);
    }
    public boolean openFileAccessFileManager(String filePath) {
        Log.e("test", "open file: " + filePath);
        try {
            File file = new File(filePath);
            //获取父目录
            File parentFlie = new File(file.getParent());
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setDataAndType(Uri.fromFile(parentFlie), "*/*");
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            frag.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean displayImgFromSDCard(String imgPath) {
        if (TextUtils.isEmpty(imgPath)) return false;
        Bitmap bitmap = BitmapFactory.decodeFile(imgPath);
        if (bitmap == null) return false;

        //display image to fl_container
        ImageView imageView = new ImageView(frag.getContext());
//        imageView.setScaleType(ImageView.ScaleType.CENTER);
        imageView.setImageBitmap(bitmap);
        add2FlContainer(imageView);
        return true;
    }

    public boolean displayGifFromSDCard(String gifPath) {
        if (TextUtils.isEmpty(gifPath)) return false;
        GifImageView gifImageView = new GifImageView(frag.getContext());
        gifImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        InputStream inputStream;
        byte[] bytes = null;
        try {
            inputStream = new FileInputStream(new File(gifPath));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int byteRead = 0;
            while ((byteRead = inputStream.read(b)) != -1) {
                baos.write(b, 0, byteRead);
            }
            bytes = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (bytes == null) {
            Toast.makeText(frag.getContext(), "error", Toast.LENGTH_SHORT).show();
            return false;
        }
        gifImageView.setBytes(bytes);
        add2FlContainer(gifImageView);
        return true;
    }
    public void openFolder(String filePath) {
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        Uri uri = Uri.parse(filePath);
//        intent.setDataAndType(uri, "*/*");
//        startActivity(Intent.createChooser(intent, "Open folder"));
        Uri selectedUri = Uri.parse(filePath);
        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setDataAndType(selectedUri, "resource/folder");
//        intent.setDataAndType(selectedUri, "*/*");
//        intent.setDataAndType(selectedUri, "*/*");
        intent.setDataAndType(selectedUri, "image/gif");
        if (intent.resolveActivityInfo(frag.getContext().getPackageManager(), 0) != null) {
            frag.startActivity(intent);
        } else {
            // if you reach this place, it means there is no any file
            // explorer app installed on your device
            Log.e("test", "wtf!!!");
        }
    }
}
