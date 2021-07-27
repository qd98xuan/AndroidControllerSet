package com.heqing.treeviewtest.bean.my_tree_view

/**
 * Created by HQOCSHheqing on 2016/8/2.
 *
 * @description 部门类（继承Node），此处的泛型Integer是因为ID和parentID都为int
 * ，如果为String传入泛型String即可
 */
class Dept : Node<String?> {
    var id //部门ID
            : String? = null
    var parentId //父亲节点ID
            : String? = null
    var name //部门名称
            : String? = null

    constructor() {}
    constructor(id: String?, parentId: String?, name: String?) {
        this.id = id
        this.parentId = parentId
        this.name = name
    }

    /**
     * 此处返回节点ID
     * @return
     */
    override fun get_id(): String {
        return id!!
    }

    /**
     * 此处返回父亲节点ID
     * @return
     */
    override fun get_parentId(): String {
        return parentId!!
    }

    override fun get_label(): String {
        return name!!
    }

    override fun parent(dest: Node<*>?): Boolean {
        return if (id?.equals(dest?.get_parentId()) == true){
            true
        }else false
    }

    override fun child(dest: Node<*>?): Boolean {
        return if (parentId?.equals(dest?.get_id()) == true) {
            true
        } else false
    }
}