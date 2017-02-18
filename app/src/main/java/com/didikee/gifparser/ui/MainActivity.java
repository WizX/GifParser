package com.didikee.gifparser.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.didikee.gifparser.R;
import com.didikee.gifparser.constant.IntentKey;
import com.didikee.gifparser.ui.frag.GifParserFragment;

public class MainActivity extends AppCompatActivity {


    private Toolbar toolbar;

    private GifParserFragment gifFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 从app外来,会传来uri
        getIntentData();
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
    }

    private void initToolBar() {
        toolbar = ((Toolbar) findViewById(R.id.toolbar));
        setSupportActionBar(toolbar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
            case R.id.action_help:
                Toast.makeText(this,"cooming soon! =.= ",Toast.LENGTH_SHORT).show();
                result=true;
                break;
            default:
                result=super.onOptionsItemSelected(item);
                break;
        }
        return result;
    }
}
