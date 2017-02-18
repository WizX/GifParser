package com.didikee.gifparser.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.didikee.gifparser.R;
import com.didikee.gifparser.constant.IntentKey;
import com.didikee.gifparser.ui.frag.GifParserFragment;

public class CommonParserActivity extends AppCompatActivity {

    private GifParserFragment gifFrag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntentData();
        setContentView(R.layout.activity_common_parser);
        init();
    }
    private void getIntentData() {
        gifFrag =new GifParserFragment();
        Intent intent = getIntent();
        if (intent == null){
            return;
        }
        Uri uri = intent.getData();
        if (uri == null){
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable(IntentKey.URI,uri);
        gifFrag.setArguments(bundle);
    }

    private void init() {
        initToolBar();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container,gifFrag,"gif").commit();
//        List<Fragment> fragments = new ArrayList<>();
//        fragments.add(new GifParserFragment());
////        fragments.add(new HistoryFragment());
//        MainFragmentPagerAdapter pagerAdapter = new MainFragmentPagerAdapter
//                (getSupportFragmentManager(),fragments,getResources().getStringArray(R.array.gif_parser_tab));
    }

    private void initToolBar() {
    }
}
