package com.didikee.gifparser.ui.frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by didik 
 * Created time 2016/12/12
 * Description: 
 */

public abstract class BaseFragment extends Fragment implements IBaseFragment{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!getIntentData()){
            Log.d(getClass().getSimpleName(),"intent data is illega!");
            getActivity().finish();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View contentView = inflater.inflate(getLayoutId(), container, false);
        initView(contentView,savedInstanceState);
        return contentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        registerListener();
        startFlow();
    }
    protected abstract void startFlow();

    @Override
    public boolean getIntentData() {
        return true;
    }

}
