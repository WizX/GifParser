package com.didikee.gifparser.ui.frag;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.didikee.gifparser.R;

/**
 * Created by didik 
 * Created time 2016/12/12
 * Description: 
 */

public class HistoryFragment extends BaseFragment{

    private Button bt_show_pop;

    @Override
    public int getLayoutId() {
        return R.layout.frag_history;
    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        bt_show_pop = ((Button) inflateView.findViewById(R.id.bt_show));
    }

    @Override
    public void registerListener() {
        bt_show_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPop();
            }
        });
    }

    private void showPop() {
        TextView textView=new TextView(getContext());
        textView.setText("你好啊,朋友!");
        textView.setBackgroundColor(Color.BLUE);
        textView.setGravity(Gravity.CENTER);
//        PopupWindow popupWindow=new PopupWindow(LayoutInflater.from(getContext()).inflate(R.layout.pop,null,false), ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        PopupWindow popupWindow=new PopupWindow(textView, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAsDropDown(bt_show_pop);
    }
}
