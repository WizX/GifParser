package com.didikee.gifparser.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.didikee.gifparser.R;
import com.didikee.gifparser.adapter.MainFragmentPagerAdapter;
import com.didikee.gifparser.ui.frag.GifParserFragment;
import com.didikee.gifparser.ui.frag.HistoryFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private TabLayout tab_layout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }



    private void init() {
        tab_layout = ((TabLayout) findViewById(R.id.tab_layout));
        viewPager = ((ViewPager) findViewById(R.id.viewpager));

        MainFragmentPagerAdapter pagerAdapter=new MainFragmentPagerAdapter(getSupportFragmentManager());
        List<Fragment> fragments=new ArrayList<>();
        fragments.add(new GifParserFragment());
        fragments.add(new HistoryFragment());
        pagerAdapter.setFragments(fragments);
        viewPager.setAdapter(pagerAdapter);
        tab_layout.setupWithViewPager(viewPager);
        tab_layout.setTabMode(TabLayout.MODE_FIXED);
//        initView();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
