package com.didikee.gifparser.ui.frag;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.didikee.gifparser.R;
import com.didikee.gifparser.files.FileUtil;
import com.didikee.gifparser.files.Uri2Path;
import com.didikee.gifparser.helper.GifParserHelper;

/**
 * Created by didik 
 * Created time 2016/12/12
 * Description: 
 */

public class GifParserFragment extends BaseFragment implements View.OnClickListener {

    private FrameLayout fl_container;
    private Button bt_parser;
    private String testPath = "/storage/emulated/0/gif/";
    private String testPath2 = "/storage/emulated/0/ickeck/ac";
    private String parserPath="";//需要解析的gif图片的路径
    private String preParserPath="";
    private GifParserHelper helper;
    private AlertDialog dialog;

    @Override
    public int getLayoutId() {
        return R.layout.frag_gif_parser;
    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        fl_container = ((FrameLayout) inflateView.findViewById(R.id.fl_container));
        bt_parser = ((Button) inflateView.findViewById(R.id.bt_parser));
        helper = new GifParserHelper(this,fl_container);
        helper.setDefaultFlContainer();
    }

    @Override
    public void registerListener() {
        fl_container.setOnClickListener(this);
        bt_parser.setOnClickListener(this);
        View.generateViewId();
    }

    @Override
    protected void startFlow() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_add_file:
                showChooserDialog();
                break;
            case R.id.bt_parser:
                Toast.makeText(getContext(), "bt_parser: "+ parserPath, Toast.LENGTH_SHORT)
                        .show();
                if (preParserPath.equalsIgnoreCase(parserPath)){
                    Toast.makeText(getContext(), "已经解析过了 =V= ", Toast.LENGTH_SHORT)
                            .show();
                }else {
                    helper.parserGif2File(parserPath);
                    preParserPath=parserPath;
                }

                break;
            default:
                Toast.makeText(getContext(), "can not find avialable id", Toast.LENGTH_SHORT)
                        .show();
                break;
        }
    }

    private void showChooserDialog(){
        final String[] tab=getResources().getStringArray(R.array.gif_parser_tab);
        if (dialog==null){
            dialog = new AlertDialog.Builder(getContext())
                    .setItems(tab, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            helper.start2ChooseFile();
                            Toast.makeText(getContext(),tab[which],Toast.LENGTH_LONG).show();
                        }
                    })
                    .setTitle(getResources().getString(R.string.title_choose))
                    .setCancelable(true)
                    .create();
        }
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
                parserPath="";
            } else if (check) {
                //gif
                Log.e("test", "gif");
                helper.displayGifFromSDCard(finalPath);
                parserPath=finalPath;
            } else {
                //img
                Log.e("test", "img");
                helper.displayImgFromSDCard(finalPath);
                parserPath="";
            }

        }
    }

}
