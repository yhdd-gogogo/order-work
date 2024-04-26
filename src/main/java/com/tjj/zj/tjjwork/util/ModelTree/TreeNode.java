package com.tjj.zj.tjjwork.util.ModelTree;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author zxf
 */
public class TreeNode extends LinkedHashMap<String, Object> {

    private List<TreeNode> children;

    public TreeNode(){
        children = new ArrayList<>();
        this.put("children", children);
    }

    public void appendChild(TreeNode child){
        children.add(child);
    }

    public boolean isEmptyNode(){
        return children.isEmpty();
    }

    public List<TreeNode> getChildren() {
        return children;
    }
}
