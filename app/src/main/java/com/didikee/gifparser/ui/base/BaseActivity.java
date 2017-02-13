package com.didikee.gifparser.ui.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by didik on 2016/12/17.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntentData()){
            setContentView(setLayoutId());
        }else {
            finish();
        }
        startFlow();
    }
    @LayoutRes
    protected abstract int setLayoutId();

    protected boolean getIntentData(){
        return true;
    }

    protected abstract void startFlow();

}
