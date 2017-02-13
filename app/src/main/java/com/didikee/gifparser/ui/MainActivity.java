package com.didikee.gifparser.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.didikee.gifparser.R;
import com.didikee.gifparser.adapter.MainFragmentPagerAdapter;
import com.didikee.gifparser.ui.frag.GifParserFragment;
import com.didikee.gifparser.ui.frag.HistoryFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private TabLayout tab_layout;
    private ViewPager viewPager;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }


    private void init() {
        initToolBar();
        tab_layout = ((TabLayout) findViewById(R.id.tab_layout));
        viewPager = ((ViewPager) findViewById(R.id.viewpager));


        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new GifParserFragment());
        fragments.add(new HistoryFragment());
        MainFragmentPagerAdapter pagerAdapter = new MainFragmentPagerAdapter
                (getSupportFragmentManager(),fragments,getResources().getStringArray(R.array.gif_parser_tab));
        viewPager.setAdapter(pagerAdapter);
        tab_layout.setupWithViewPager(viewPager);
        tab_layout.setTabMode(TabLayout.MODE_FIXED);
        tab_layout.setTabGravity(TabLayout.GRAVITY_FILL);
    }

    private void initToolBar() {
        toolbar = ((Toolbar) findViewById(R.id.toolbar));
        setSupportActionBar(toolbar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean result;
        switch (item.getItemId()){
            case R.id.action_settings:
                Toast.makeText(this,getResources().getString(R.string.author),Toast.LENGTH_SHORT).show();
                result=true;
                break;
            case R.id.action_about:
                startActivity(new Intent(this,AboutActivity.class));
                result=true;
                break;
            /*case R.id.action_help:
                Toast.makeText(this,"cooming soon! =.= ",Toast.LENGTH_SHORT).show();
                result=true;
                break;*/
            default:
                result=super.onOptionsItemSelected(item);
                break;
        }
        return result;
    }
}
