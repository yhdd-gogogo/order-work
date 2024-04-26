package com.tjj.zj.tjjwork.util.ModelTree;

/**
 * @author zxf
 */
public class RootNode implements Tree {

    private String nodeId;

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    @Override
    public String fetchCurrentNodeId() {
        return nodeId;
    }

    @Override
    public String fetchParentNodeId() {
        return null;
    }
}
