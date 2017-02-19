package com.didikee.gifparser.ui.frag;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.didikee.gifparser.R;
import com.didikee.gifparser.constant.IntentKey;
import com.didikee.gifparser.gifs.helper.GifParserHelper;
import com.didikee.gifparser.gifs.interf.OnProgressChangeListener;
import com.didikee.gifparser.ui.ParserFragHelper;
import com.didikee.gifparser.utils.FileUtil;
import com.didikee.gifparser.utils.UriUtil;

/**
 * Created by didik 
 * Created time 2016/12/12
 * Description: 
 */

public class GifParserFragment extends BaseFragment implements View.OnClickListener {

    private FrameLayout fl_container;
    private Button bt_parser;
    private String parserPath="";//需要解析的gif图片的路径
    private String preParserPath="";
    private AlertDialog dialog;
    private ParserFragHelper fragHelper;
    private GifParserHelper gifParserHelper;

    @Override
    public int getLayoutId() {
        return R.layout.frag_gif_parser;
    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        fl_container = ((FrameLayout) inflateView.findViewById(R.id.fl_container));
        bt_parser = ((Button) inflateView.findViewById(R.id.bt_parser));
        fragHelper = new ParserFragHelper(this,fl_container);
        fragHelper.setDefaultFlContainer();
    }

    @Override
    public void registerListener() {
        fl_container.setOnClickListener(this);
        bt_parser.setOnClickListener(this);
        View.generateViewId();
    }

    @Override
    public boolean getIntentDataDelay() {
        Bundle arguments = getArguments();
        if (arguments != null ){
            Uri uri = arguments.getParcelable(IntentKey.URI);
            handleUri(uri);
        }
        return true;
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
                if (preParserPath.equalsIgnoreCase(parserPath)){
                    Toast.makeText(getContext(), getResources().getString(R.string.error_has_parse), Toast.LENGTH_SHORT)
                            .show();
                }else {
                    //check
                    if (TextUtils.isEmpty(parserPath)){
                        Toast.makeText(getContext(),"this is Not a gif or file path is EMPTY!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    final ProgressDialog progressDialog;
                    progressDialog = new ProgressDialog(getContext());
                    progressDialog.setMax(100);
                    progressDialog.setMessage(getContext().getResources().getString(R.string.title_gif_parsing));
                    progressDialog.setTitle(getContext().getResources().getString(R.string.content_wait));
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressDialog.show();

                    gifParserHelper =new GifParserHelper(parserPath);
                    gifParserHelper.setProgressChangeListener(new OnProgressChangeListener() {
                        @Override
                        public void prepare(int total) {
                            progressDialog.setMax(total);
                        }

                        @Override
                        public void onProgressChange(int current, int total, int percent) {
                            progressDialog.setProgress(current);
                        }

                        @Override
                        public void onFinish(boolean error) {
                            progressDialog.dismiss();
                            if (error){
                                Toast.makeText(getContext(),"解析失败",Toast.LENGTH_SHORT).show();
                            }else {
                                String saveFileInfo = gifParserHelper.getSaveFileInfo();
                                if (!TextUtils.isEmpty(saveFileInfo)){
                                    Toast.makeText(getContext(),"照片保存在 "  + saveFileInfo,Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    });
                    gifParserHelper.parser();
                    preParserPath=parserPath;
                }

                break;
            default:
                break;
        }
    }

    private void showChooserDialog(){
        final String[] tab=getResources().getStringArray(R.array.gif_parser_choose);
        if (dialog==null){
            dialog = new AlertDialog.Builder(getContext())
                    .setItems(tab, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            fragHelper.start2ChooseFile();
                            Toast.makeText(getContext(),tab[which],Toast.LENGTH_LONG).show();
                        }
                    })
                    .setTitle(getResources().getString(R.string.title_choose))
                    .setCancelable(true)
                    .create();
        }
        dialog.show();
    }

    private void handleUri(Uri uri){
        String finalPath = UriUtil.getPathFromUri(getContext(),uri);
        Toast.makeText(getContext(), finalPath, Toast.LENGTH_SHORT).show();
        Boolean check = FileUtil.checkFileType(finalPath);
        if (check == null) {
            //do nothing
            parserPath="";
        } else if (check) {
            //gif
            fragHelper.displayGifFromSDCard(finalPath);
            parserPath=finalPath;
        } else {
            //img
            fragHelper.displayImgFromSDCard(finalPath);
            parserPath="";
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {//是否选择，没选择就不会继续
            handleUri(data.getData());
        }
    }


}
