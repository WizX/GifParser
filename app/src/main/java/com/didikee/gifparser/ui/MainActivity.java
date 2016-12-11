package com.didikee.gifparser.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.didikee.gifparser.R;
import com.didikee.gifparser.files.Uri2Path;
import com.didikee.gifparser.gifs.GifImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private FrameLayout fl_container;
    private Button bt_parser;
    private String testPath="/storage/emulated/0/gif/";
    private String testPath2="/storage/emulated/0/ickeck/ac";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        init();

    }


    private void start2ChooseFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写
        //intent.setType(“audio/*”); //选择音频
        //intent.setType(“video/*”); //选择视频 （mp4 3gp 是android支持的视频格式）
        //intent.setType(“video/*;image/*”);//同时选择视频和图片
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, 1);
    }

    private void init() {
        initView();
    }

    private void initView() {
        fl_container = ((FrameLayout) findViewById(R.id.fl_container));
        bt_parser = ((Button) findViewById(R.id.bt_parser));

        bt_parser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (checkParams()) {
//                    parserGif2File();
//                }
                    start2ChooseFile();
            }
        });

        findViewById(R.id.bt_open_file).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                boolean b = openFileAccessFileManager(testPath2);
//                if (b){
//                    Log.e("test","yes is open");
//                }else {
//                    Log.e("test","no! some error come out!");
//                }
                openFolder(testPath);
            }
        });
    }

    private boolean checkParams() {
        return true;
    }

    private void parserGif2File() {

    }

    private boolean openFileAccessFileManager(String filePath){
        Log.e("test","open file: "+filePath);
        try {
            File file = new File(filePath);
            //获取父目录
            File parentFlie = new File(file.getParent());
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setDataAndType(Uri.fromFile(parentFlie), "*/*");
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public void openFolder(String filePath)
    {
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
        if (intent.resolveActivityInfo(getPackageManager(), 0) != null)
        {
            startActivity(intent);
        }
        else
        {
            // if you reach this place, it means there is no any file
            // explorer app installed on your device
            Log.e("test","wtf!!!");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {//是否选择，没选择就不会继续
            Uri uri = data.getData();//得到uri，后面就是将uri转化成file的过程
            String finalPath="";
            int sdkInt = Build.VERSION.SDK_INT;
            if (sdkInt >= 19){
                finalPath=Uri2Path.getRealPathFromURI_API19(this,uri);
            }else if (sdkInt >=11 && sdkInt <19){
                finalPath = Uri2Path.getRealPathFromURI_API11to18(this,uri);
            }else {
                finalPath = Uri2Path.getRealPathFromURI_BelowAPI11(this,uri);
            }
            Toast.makeText(MainActivity.this, finalPath, Toast.LENGTH_SHORT).show();
            Boolean check = checkFileType(finalPath);
            if (check == null){
                //do nothing
                Log.e("test","null");
            }else if (check){
                //gif
                Log.e("test","gif");
                displayGifFromSDCard(finalPath);
            }else {
                //img
                Log.e("test","img");
                displayImgFromSDCard(finalPath);
            }

        }
    }

    private Boolean checkFileType(String filePath){
        Log.e("test","filePath: "+filePath);
        if (TextUtils.isEmpty(filePath) || !filePath.contains(".")){
            return null;
        }
        String[] split = filePath.split("\\.");
        Log.e("test","1: "+split[0] + "     2: "+split[1]);
        String ext = split[1];
        if (TextUtils.isEmpty(ext)){
            return null;
        }
        if (ext.equalsIgnoreCase("gif")){
            return true;
        }

        if (ext.equalsIgnoreCase("png") || ext.equalsIgnoreCase("jpg") || ext.equalsIgnoreCase("jpeg")){
            return false;
        }

        return null;
    }


    private boolean displayImgFromSDCard(String imgPath){
        if (TextUtils.isEmpty(imgPath))return false;
        Bitmap bitmap = BitmapFactory.decodeFile(imgPath);
        if (bitmap == null)return false;

        //display image to fl_container
        ImageView imageView=new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setImageBitmap(bitmap);
        add2FlContainer(imageView);
        return true;
    }

    private boolean displayGifFromSDCard(String gifPath){
        if (TextUtils.isEmpty(gifPath))return false;
        GifImageView gifImageView=new GifImageView(this);
        gifImageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        InputStream inputStream = null;
        byte[] bytes = null;
        try {
            inputStream = new FileInputStream(new File(gifPath));
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int byteRead = 0;
            while((byteRead = inputStream.read(b)) != -1) {
                baos.write(b, 0, byteRead);
            }
            bytes = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (bytes == null){
            Toast.makeText(this,"error",Toast.LENGTH_SHORT).show();
            return false;
        }
        gifImageView.setBytes(bytes);
        add2FlContainer(gifImageView);
        return true;
    }

    private void add2FlContainer(ImageView imageView){
        fl_container.removeAllViews();
        FrameLayout.LayoutParams params=new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity= Gravity.CENTER;
        fl_container.addView(imageView,params);
        if (imageView instanceof GifImageView){
            ((GifImageView) imageView).startAnimation();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
