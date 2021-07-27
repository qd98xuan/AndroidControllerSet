package com.heqing.treeviewtest.bean.my_tree_view

import com.heqing.treeviewtest.R
import java.util.ArrayList

/**
 * Created by HQOCSHheqing on 2016/8/2.
 *
 * @description  节点抽象类（泛型T主要是考虑到ID和parentID有可能是int型也有可能是String型
 * 即这里可以传入Integer或者String，具体什么类型由子类指定
 * ，因为这两种类型比较是否相等的方式不同：一个是用 “==”，一个是用  equals() 函数）
 */
abstract class Node<T> {
    private var _level = -1 //当前节点的层级，初始值-1 后面会讲到
    private var _childrenList: ArrayList<Node<*>> = ArrayList() //所有的孩子节点
    private var _parent //父亲节点
            : Node<*>? = null
    private var _icon //图标资源ID
            = 0
    var isExpand = false //当前状态是否展开
        set(isExpand) {
            field = isExpand
            _icon = if (isExpand) {
                R.mipmap.collapse
            } else {
                R.mipmap.expand
            }
        }

    abstract fun get_id(): T //得到当前节点ID
    abstract fun get_parentId(): T //得到当前节点的父ID
    abstract fun get_label(): String? //要显示的内容
    abstract fun parent(dest: Node<*>?): Boolean //判断当前节点是否是dest的父亲节点
    abstract fun child(dest: Node<*>?): Boolean //判断当前节点是否是dest的孩子节点
    fun get_level(): Int {
        if (_level == -1) { //如果是 -1 的话就递归获取
            //因为是树形结构，所以此处想要得到当前节点的层级
            //，必须递归调用，但是递归效率低下，如果每次都递归获取会严重影响性能，所以我们把第一次
            //得到的结果保存起来避免每次递归获取
            val level = if (_parent == null) 1 else _parent!!.get_level() + 1
            _level = level
            return _level
        }
        return _level
    }

    fun set_level(_level: Int) {
        this._level = _level
    }

    fun get_childrenList(): ArrayList<Node<*>> {
        return _childrenList
    }

    fun set_childrenList(_childrenList: ArrayList<Node<*>>) {
        this._childrenList = _childrenList
    }

    fun get_parent(): Node<*>? {
        return _parent
    }

    fun set_parent(_parent: Node<*>?) {
        this._parent = _parent
    }

    fun get_icon(): Int {
        return _icon
    }

    fun set_icon(_icon: Int) {
        this._icon = _icon
    }

    val isRoot: Boolean
        get() = _parent == null
    val isLeaf: Boolean
        get() = _childrenList.size <= 0
}