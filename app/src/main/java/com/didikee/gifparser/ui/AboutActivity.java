package com.didikee.gifparser.ui;

import android.widget.TextView;

import com.didikee.gifparser.R;
import com.didikee.gifparser.ui.base.BaseActivity;
import com.didikee.gifparser.utils.OSUtil;

public class AboutActivity extends BaseActivity {

    private TextView tv_version;
    private TextView tv_description;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void startFlow() {
        setTitle(getResources().getString(R.string.action_about));
        tv_version = ((TextView) findViewById(R.id.tv_version));
        tv_description = ((TextView) findViewById(R.id.tv_description));
        tv_version.setText(getResources().getString(R.string.text_version)+": "+OSUtil.getVersionName(this));
        tv_description.setText(getResources().getString(R.string.long_text_about));
    }
}
