package com.tjj.zj.tjjwork.util.ModelTree;

import java.io.Serializable;

/**
 * @author zxf
 * 描述具有层级关系的对象
 *
 */
public interface Tree extends Serializable {

    String fetchCurrentNodeId();

    String fetchParentNodeId();
}
