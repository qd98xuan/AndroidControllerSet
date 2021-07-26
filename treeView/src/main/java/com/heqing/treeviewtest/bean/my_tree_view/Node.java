package com.heqing.treeviewtest.bean.my_tree_view;
import com.heqing.treeviewtest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HQOCSHheqing on 2016/8/2.
 *
 * @description  �ڵ�����ࣨ����T��Ҫ�ǿ��ǵ�ID��parentID�п�����int��Ҳ�п�����String��
 * ��������Դ���Integer����String������ʲô����������ָ��
����Ϊ���������ͱȽ��Ƿ���ȵķ�ʽ��ͬ��һ������ ��==����һ������  equals() ������
 */
public abstract class Node<T> {

    private int _level = -1;//��ǰ�ڵ�Ĳ㼶����ʼֵ-1 ����ὲ��
    private List<Node> _childrenList = new ArrayList<>();//���еĺ��ӽڵ�
    private Node _parent;//���׽ڵ�
    private int _icon;//ͼ����ԴID
    private boolean isExpand = false;//��ǰ״̬�Ƿ�չ��


    public abstract T get_id();//�õ���ǰ�ڵ�ID
    public abstract T get_parentId();//�õ���ǰ�ڵ�ĸ�ID
    public abstract String get_label();//Ҫ��ʾ������
    public abstract boolean parent(Node dest);//�жϵ�ǰ�ڵ��Ƿ���dest�ĸ��׽ڵ�
    public abstract boolean child(Node dest);//�жϵ�ǰ�ڵ��Ƿ���dest�ĺ��ӽڵ�


    public int get_level() {
        if (_level == -1){//����� -1 �Ļ��͵ݹ��ȡ
            //��Ϊ�����νṹ�����Դ˴���Ҫ�õ���ǰ�ڵ�Ĳ㼶
            //������ݹ���ã����ǵݹ�Ч�ʵ��£����ÿ�ζ��ݹ��ȡ������Ӱ�����ܣ��������ǰѵ�һ��
            //�õ��Ľ��������������ÿ�εݹ��ȡ
            int level = _parent == null ? 1 : _parent.get_level()+1;
            _level = level;
            return _level;
        }
        return _level;
    }

    public void set_level(int _level) {
        this._level = _level;
    }

    public List<Node> get_childrenList() {
        return _childrenList;
    }

    public void set_childrenList(List<Node> _childrenList) {
        this._childrenList = _childrenList;
    }

    public Node get_parent() {
        return _parent;
    }

    public void set_parent(Node _parent) {
        this._parent = _parent;
    }

    public int get_icon() {
        return _icon;
    }

    public void set_icon(int _icon) {
        this._icon = _icon;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setIsExpand(boolean isExpand) {
        this.isExpand = isExpand;
        if (isExpand){
            _icon = R.mipmap.collapse;
        }else{
            _icon = R.mipmap.expand;
        }
    }

    public boolean isRoot(){
        return _parent == null;
    }

    public boolean isLeaf(){
        return _childrenList.size() <= 0;
    }

}
