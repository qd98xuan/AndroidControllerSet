package com.me.testlistmodule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.me.list_module.entitys.CommonKeyValueEntity
import com.me.list_module.list_delete.RecycleListHasDelete
import com.me.testlistmodule.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.activity_main,null,false)
        setContentView(binding.root)
        val dataList = ArrayList<CommonKeyValueEntity>()
        dataList.add(CommonKeyValueEntity("001","测试1"))
        dataList.add(CommonKeyValueEntity("002","测试2"))
        dataList.add(CommonKeyValueEntity("003","测试3"))
        dataList.add(CommonKeyValueEntity("004","测试4"))
        val recycleListHasDelete = RecycleListHasDelete(binding.prDelete,this)
        recycleListHasDelete.setAdapter(dataList)
        binding.btOk.setOnClickListener {
            Toast.makeText(this,recycleListHasDelete.dataList?.size.toString(),Toast.LENGTH_SHORT).show()
        }
        binding.btAdd.setOnClickListener {
            val dataList = ArrayList<CommonKeyValueEntity>()
            dataList.add(CommonKeyValueEntity("001","测试1"))
            dataList.add(CommonKeyValueEntity("002","测试2"))
            dataList.add(CommonKeyValueEntity("003","测试3"))
            dataList.add(CommonKeyValueEntity("004","测试4"))
            recycleListHasDelete.addData(dataList)
        }

    }
}