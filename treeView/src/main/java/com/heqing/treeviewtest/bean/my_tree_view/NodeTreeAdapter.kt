package com.heqing.treeviewtest.bean.my_tree_view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import com.heqing.treeviewtest.R
import java.util.*

/**
 * Created by HQOCSHheqing on 2016/8/3.
 *
 * @description 适配器类，就是listview最常见的适配器写法
 */
class NodeTreeAdapter(
    context: Context,
    listView: ListView,
    linkedList: LinkedList<Node<*>>,
    onTreeClickCallback: OnTreeClickCallback
) :
    BaseAdapter() {
    //大家经常用ArrayList，但是这里为什么要使用LinkedList
    // ，后面大家会发现因为这个list会随着用户展开、收缩某一项而频繁的进行增加、删除元素操作，
    // 因为ArrayList是数组实现的，频繁的增删性能低下，而LinkedList是链表实现的，对于频繁的增删
    //操作性能要比ArrayList好。
    private val nodeLinkedList: LinkedList<Node<*>>
    private val inflater: LayoutInflater
    private val retract //缩进值
            : Int
    private val context: Context
    private val onTreeClickCallback //选中的回调
            : OnTreeClickCallback
    private var itemBackgroundColor = R.color.white //选项的背景颜色
    private var itemTextColor = R.color.black //选项的字体颜色

    /**
     * 展开或收缩用户点击的条目
     * @param position
     */
    private fun expandOrCollapse(position: Int) {
        val node = nodeLinkedList[position]
        if (node != null && !node.isLeaf) {
            val old = node.isExpand
            if (old) {
                val nodeList = node.get_childrenList()
                val size = nodeList.size
                var tmp: Node<*>? = null
                for (i in 0 until size) {
                    tmp = nodeList[i]
                    if (tmp.isExpand) {
                        collapse(tmp, position + 1)
                    }
                    nodeLinkedList.removeAt(position + 1)
                }
            } else {
                nodeLinkedList.addAll(position + 1, node.get_childrenList())
            }
            node.isExpand=!old
            notifyDataSetChanged()
        }
    }

    /**
     * 递归收缩用户点击的条目
     * 因为此中实现思路是：当用户展开某一条时，就将该条对应的所有子节点加入到nodeLinkedList
     * ，同时控制缩进，当用户收缩某一条时，就将该条所对应的子节点全部删除，而当用户跨级缩进时
     * ，就需要递归缩进其所有的孩子节点，这样才能保持整个nodeLinkedList的正确性，同时这种实
     * 现方式避免了每次对所有数据进行处理然后插入到一个list，最后显示出来，当数据量一大，就会卡顿，
     * 所以这种只改变局部数据的方式性能大大提高。
     * @param position
     */
    private fun collapse(node: Node<*>?, position: Int) {
        node?.isExpand=false
        val nodes = node!!.get_childrenList()
        val size = nodes.size
        var tmp: Node<*>? = null
        for (i in 0 until size) {
            tmp = nodes[i]
            if (tmp.isExpand) {
                collapse(tmp, position + 1)
            }
            nodeLinkedList.removeAt(position + 1)
        }
    }

    override fun getCount(): Int {
        return nodeLinkedList.size
    }

    override fun getItem(position: Int): Any {
        return nodeLinkedList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var convertView = convertView
        val holder: ViewHolder
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.tree_listview_item, null)
            convertView.setBackgroundColor(context.resources.getColor(itemBackgroundColor))
            holder = ViewHolder()
            holder.imageView = convertView.findViewById<View>(R.id.id_treenode_icon) as ImageView
            holder.label = convertView.findViewById<View>(R.id.id_treenode_label) as TextView
            holder.label!!.setTextColor(context.resources.getColor(itemTextColor))
            holder.confirm = convertView.findViewById<View>(R.id.id_confirm) as LinearLayout
            convertView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }
        val node = nodeLinkedList[position]
        holder.label!!.text = node.get_label()
        if (node.get_icon() == -1) {
            holder.imageView!!.visibility = View.INVISIBLE
        } else {
            holder.imageView!!.visibility = View.VISIBLE
            holder.imageView!!.setImageResource(node.get_icon())
        }
        holder.confirm!!.tag = position
        holder.confirm!!.setOnClickListener {
            onTreeClickCallback.onItemClickListener(
                node.get_id().toString(),
                node.get_parentId().toString()
            )
            //                Toast.makeText(context,"选中:"+v.getTag(),Toast.LENGTH_SHORT).show();
        }
        convertView?.setPadding(node.get_level() * retract, 5, 5, 5)
        return convertView
    }

    fun setItemBackgroundColor(color: Int) {
        itemBackgroundColor = color
    }

    fun setItemTextColor(color: Int) {
        itemTextColor = color
    }

    internal class ViewHolder {
        var imageView: ImageView? = null
        var label: TextView? = null
        var confirm: LinearLayout? = null
    }

    init {
        inflater = LayoutInflater.from(context)
        this.context = context
        nodeLinkedList = linkedList
        this.onTreeClickCallback = onTreeClickCallback
        listView.onItemClickListener =
            OnItemClickListener { parent, view, position, id -> expandOrCollapse(position) }
        //缩进值，大家可以将它配置在资源文件中，从而实现适配
        retract = (context.resources.displayMetrics.density * 10 + 0.5f).toInt()
    }
}