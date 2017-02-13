package com.didikee.gifparser.ui.frag;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.didikee.gifparser.R;
import com.didikee.gifparser.adapter.MyTestRvAdapter;

/**
 * Created by didik 
 * Created time 2016/12/12
 * Description: 
 */

public class HistoryFragment extends BaseFragment{

    private Button bt_show_pop;
    private RecyclerView listView;

    @Override
    public int getLayoutId() {
        return R.layout.frag_history;
    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
//        bt_show_pop = ((Button) inflateView.findViewById(R.id.bt_show));
        listView = ((RecyclerView) inflateView.findViewById(R.id.listView));
    }

    @Override
    public void registerListener() {
    }


    @Override
    protected void startFlow() {
        initListview();
    }

    private String[] data = {
            "       c",
            "       o",
            "       o",
            "       m",
            "       i",
            "       n",
            "       g",
            "        ",
            "       s",
            "       o",
            "       o",
            "       n",
            "       !",
            "     =.="
    };
    private void initListview() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getContext(), android.R.layout.simple_list_item_1, data);
        listView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        MyTestRvAdapter adapter1=new MyTestRvAdapter();
        adapter1.setData(data);
        listView.setAdapter(adapter1);
    }
}
