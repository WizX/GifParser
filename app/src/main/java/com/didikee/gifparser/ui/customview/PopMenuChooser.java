package com.didikee.gifparser.ui.customview;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.PopupWindow;

import com.didikee.gifparser.R;
import com.didikee.gifparser.utils.UIUtil;
import com.didikee.uilibs.views.MaxHeightListView;

/**
 * Created by didik 
 * Created time 2016/12/16
 * Description: 
 */

public class PopMenuChooser extends PopupWindow{
    private final Activity activity;
    private View anchor;
    private Pair<Integer, Integer> windowPixels;
    private MaxHeightListView listView;

    public PopMenuChooser(@NonNull Activity activity) {
        this.activity = activity;
        initPop();
    }

    public void setAnchor(View target){
        this.anchor=target;
    }

    private String[] data = { "Apple", "Banana", "Orange"};
    private void initPop() {
        windowPixels = UIUtil.getWindowPixels(activity);
        Log.e("test","width: "+ windowPixels.first +"    height: "+ windowPixels.second);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                activity, android.R.layout.simple_list_item_1, data);

        listView = new MaxHeightListView(activity);
        listView.setAdapter(adapter);

//        FrameLayout frameLayout=new FrameLayout(activity);
//        FrameLayout.LayoutParams params=new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtil.dp2px(activity,50));
//        frameLayout.setLayoutParams(params);
//        frameLayout.addView(listView, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
//        frameLayout.setBackgroundColor(Color.BLUE);
//        listView.setBackgroundColor(Color.BLUE);
        this.setWidth((int) (windowPixels.first* 0.8));
//        this.setHeight(DisplayUtil.dp2px(activity,100));
        listView.setBackgroundResource(R.drawable.shape_cricle_white_small);

        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.setContentView(listView);
        this.setFocusable(true);
        this.setOutsideTouchable(true);

//        this.setAnimationStyle();
    }

   public void showPop(){
        listView.measure(View.MeasureSpec.EXACTLY,View.MeasureSpec.UNSPECIFIED);
//        int height = listView.getHeight();
        int height = listView.getMeasuredHeight();

        Log.e("test","height: "+height);
        this.showAtLocation(activity.getWindow().getDecorView(), Gravity.CENTER,0,0);
    }
}
