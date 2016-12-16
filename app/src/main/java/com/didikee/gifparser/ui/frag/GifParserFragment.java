package com.didikee.gifparser.ui.frag;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.didikee.gifparser.R;
import com.didikee.gifparser.files.FileUtil;
import com.didikee.gifparser.files.Uri2Path;
import com.didikee.gifparser.helper.GifParserHelper;

import java.math.BigInteger;

/**
 * Created by didik 
 * Created time 2016/12/12
 * Description: 
 */

public class GifParserFragment extends BaseFragment implements View.OnClickListener {

    private FrameLayout fl_container;
    private Button bt_parser;
    private String gifPath="";
    private GifParserHelper helper;

    @Override
    public int getLayoutId() {
        return R.layout.frag_gif_parser;
    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        fl_container = ((FrameLayout) inflateView.findViewById(R.id.fl_container));
        bt_parser = ((Button) inflateView.findViewById(R.id.bt_parser));
        helper = new GifParserHelper(this,fl_container);

//        inflateView.findViewById(R.id.bt_open_file).setOnClickListener(new View.OnClickListener
// () {
//            @Override
//            public void onClick(View v) {
////                boolean b = openFileAccessFileManager(testPath2);
////                if (b){
////                    Log.e("test","yes is open");
////                }else {
////                    Log.e("test","no! some error come out!");
////                }
//                openFolder(testPath);
//            }
//        });

        helper.setDefaultFlContainer();
        Log.e("test","int: "+Integer.MAX_VALUE+"big int: "+ BigInteger.ONE);
    }

    @Override
    public void registerListener() {
//        fl_container.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Toast.makeText(getContext(),"切换",Toast.LENGTH_SHORT).show();
////                showPopMenu();
////                popMenuChooser.showPop();
//
//
//            }
//        });

        bt_parser.setOnClickListener(this);
    }

    @Override
    protected void startFlow() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_add_file:
                showPop2GotoFileChooser();
                break;
            case R.id.bt_parser:
                Toast.makeText(getContext(), "bt_parser", Toast.LENGTH_SHORT)
                        .show();
                break;
            default:
                Toast.makeText(getContext(), "can not find avialable id", Toast.LENGTH_SHORT)
                        .show();
                break;
        }
    }

    private void showPop2GotoFileChooser(){
        final String[] haha = new String[]{
                "One",
                "Two"
        };

        AlertDialog dialog = new AlertDialog.Builder(getContext())
                .setItems(haha, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(),haha[which],Toast.LENGTH_LONG).show();
                        helper.start2ChooseFile();
                    }
                })
                .setTitle("Title is Me")
                .setCancelable(true)
                .create();
        dialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {//是否选择，没选择就不会继续
            Uri uri = data.getData();//得到uri，后面就是将uri转化成file的过程
            String finalPath = "";
            int sdkInt = Build.VERSION.SDK_INT;
            if (sdkInt >= 19) {
                finalPath = Uri2Path.getRealPathFromURI_API19(getContext(), uri);
            } else if (sdkInt >= 11 && sdkInt < 19) {
                finalPath = Uri2Path.getRealPathFromURI_API11to18(getContext(), uri);
            } else {
                finalPath = Uri2Path.getRealPathFromURI_BelowAPI11(getContext(), uri);
            }
            Toast.makeText(getContext(), finalPath, Toast.LENGTH_SHORT).show();
            Boolean check = FileUtil.checkFileType(finalPath);
            if (check == null) {
                //do nothing
                Log.e("test", "null");
                Toast.makeText(getContext(), getResources().getString(R.string.toast_is_not_gif), Toast.LENGTH_SHORT).show();
            } else if (check) {
                //gif
                Log.e("test", "gif");
                helper.displayGifFromSDCard(finalPath);
            } else {
                //img
                Log.e("test", "img");
                helper.displayImgFromSDCard(finalPath);
            }

        }
    }

}
