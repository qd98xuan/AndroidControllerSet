package com.heqing.treeviewtest.bean.my_tree_view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.heqing.treeviewtest.R;

import java.util.LinkedList;

/**
 * Created by HQOCSHheqing on 2016/8/4.
 *
 * @description
 */
public class TreeTestActivity extends Activity{

    private ListView mListView;
    private NodeTreeAdapter mAdapter;
    private LinkedList<Node> mLinkedList = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_dept_layout);
        mListView = (ListView)findViewById(R.id.id_tree);
        mAdapter = new NodeTreeAdapter(this, mListView, mLinkedList, new OnTreeClickCallback() {
            @Override
            public void onItemClickListener(String id, String parentId) {
                Toast.makeText(TreeTestActivity.this, id+","+parentId, Toast.LENGTH_SHORT).show();
            }
        });
        mListView.setAdapter(mAdapter);
    }
}
