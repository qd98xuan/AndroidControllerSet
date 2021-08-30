package com.me.list_module.list_delete

import android.content.Context
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jessewu.library.SuperAdapter
import com.jessewu.library.view.ViewHolder
import com.me.list_module.R
import com.me.list_module.entitys.CommonKeyValueEntity

class RecycleListHasDelete {
    lateinit var recyclerView: RecyclerView
    lateinit var context:Context
    var dataList : ArrayList<CommonKeyValueEntity>?=null
    set(value) {
        field =value
    }
    get() {
        return field
    }
    constructor(recyclerView:RecyclerView,context: Context){
        this.recyclerView = recyclerView
        this.context = context
    }
    lateinit var mAdapter: SuperAdapter<CommonKeyValueEntity>
    fun setAdapter(dataList:ArrayList<CommonKeyValueEntity>){
        this.dataList = dataList
        if (!this::mAdapter.isInitialized){
            mAdapter = object : SuperAdapter<CommonKeyValueEntity>(R.layout.office_item){
                override fun bindView(viewHolder: ViewHolder?, entity: CommonKeyValueEntity?, p2: Int) {
                    viewHolder?.apply {
                        getView<TextView>(R.id.tv_name).setText(entity?.value)
                        getView<TextView>(R.id.tv_delete).setOnClickListener {
                            dataList.remove(entity)
                            notifyDataSetChanged()
                        }
                    }
                }
            }
        }
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        mAdapter.setData(dataList)
    }
    fun addData(dataList : ArrayList<CommonKeyValueEntity>){
        this.dataList?.addAll(dataList)
        mAdapter.notifyDataSetChanged()
    }
}