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
    lateinit var linkedList:LinkedList<Node<*>>
    var dataList = ArrayList<Node<*>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        treeView = findViewById(R.id.tree)
        linkedList = LinkedList()
        dataList.add(Dept("1","0","A"))
        dataList.add(Dept("2","1","B"))
        dataList.add(Dept("3","2","C"))
        linkedList.addAll(NodeHelper.sortNodes(dataList)!!)
        val nodeTreeAdapter = NodeTreeAdapter(this,treeView,linkedList,object : OnTreeClickCallback{
            override fun onItemClickListener(id: String?, parentId: String?) {
                Toast.makeText(this@MainActivity,id,Toast.LENGTH_LONG).show()
            }
        })
        treeView.adapter = nodeTreeAdapter
    }
}