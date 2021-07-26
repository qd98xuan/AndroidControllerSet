package com.me.testtreenode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.heqing.treeviewtest.bean.my_tree_view.*

import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var treeView:ListView
    lateinit var linkedList:LinkedList<Node<Dept>>
    var dataList = ArrayList<Node<Dept>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        treeView = findViewById(R.id.tree)
        var nodeTreeAdapter = NodeTreeAdapter(this,treeView,linkedList,object :OnTreeClickCallback{
            override fun onItemClickListener(id: String?, parentId: String?) {
                Toast.makeText(this@MainActivity,id+","+parentId,Toast.LENGTH_LONG).show()
            }
        })
        initData()
    }

    private fun initData() {
        linkedList.addAll(NodeHelper.sortNodes(dataList))
    }
}