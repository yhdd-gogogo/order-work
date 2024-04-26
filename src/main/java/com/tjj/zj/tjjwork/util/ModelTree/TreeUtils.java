package com.tjj.zj.tjjwork.util.ModelTree;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import org.apache.commons.lang3.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zxf
 * 将实现{@link Tree}接口的对象构建为一棵树
 */
public class TreeUtils {

    public static <T extends Tree> TreeNode buildTree(List<T> nodes){
        return buildTree(nodes, "-1");
    }

    /**
     * 根据指定根节点值构建
     * @param rootNodeId 根节点ID
     * @param nodes
     * @return
     */
    public static <T extends Tree> TreeNode buildTree(List<T> nodes, String rootNodeId) {
        TreeNode rootTree = new TreeNode();
        if(nodes == null || nodes.isEmpty()){
            return rootTree;
        }

        rootNodeId = "".equals(rootNodeId) || null == rootNodeId ? "-1" : rootNodeId;
        // 过滤空节点
        nodes = nodes.stream().filter(node -> !isEmptyNode(node)).collect(Collectors.toList());
        // 查找根节点
        Tree rootNode = findRootNode(rootNodeId, nodes);
        mountChildNodes(rootTree, rootNode, nodes);
        return rootTree.getChildren().get(0);
    }

    public static <T extends Tree> void mountChildNodes(TreeNode treeNode, Tree parentNode, List<T> nodes) {
        String currentNodeId = parentNode.fetchCurrentNodeId();
        TreeNode currentNode = transformToTreNode(parentNode);
        List<T> childNodes = nodes.stream()
                .filter(node -> currentNodeId.equals(node.fetchParentNodeId()))
                .filter(node -> !currentNodeId.equals(node.fetchCurrentNodeId()))
                .collect(Collectors.toList());
        for (T childNode : childNodes) {
            mountChildNodes(currentNode, childNode, nodes);
        }

        currentNode.put("isLeaf", childNodes.isEmpty());
        treeNode.appendChild(currentNode);
    }

    public static <T> List<T> flatTree(TreeNode treeNode, Class<T> clazz) {
        List<T> models = new ArrayList<>();
        flatTreeNode(treeNode, clazz, models);
        return models;
    }

    public static <T> List<T> flatTree(List<TreeNode> treeNodes, Class<T> clazz) {
        List<T> models = new ArrayList<>();
        treeNodes.forEach(treeNode -> flatTreeNode(treeNode, clazz, models));
        return models;
    }

    private static <T> void flatTreeNode(Map<String, Object> node, Class<T> clazz, List<T> models){
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
        filter.getExcludes().add("children");
        SimplePropertyPreFilter[] filters = new SimplePropertyPreFilter[]{filter};
        T model = JSONObject.parseObject(JSONObject.toJSONString(node, filters, SerializerFeature.WriteMapNullValue), clazz);
        models.add(model);

        Object children = node.get("children");
        List<Map<String, Object>> childNodes = new ArrayList<>();
        if(ObjectUtils.isNotEmpty(children)) {
            childNodes = (List<Map<String, Object>>) children;
        }

        for (Map<String, Object> child : childNodes) {
            flatTreeNode(child, clazz, models);
        }
    }

    private static <T extends Tree> TreeNode transformToTreNode(T node) {
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
        // 过滤接口get属性
        filter.getExcludes().addAll(Arrays.asList("currentNodeId", "parentNodeId"));
        SimplePropertyPreFilter[] filters = new SimplePropertyPreFilter[]{filter};
        return JSON.parseObject(JSON.toJSONString(node, filters, SerializerFeature.WriteMapNullValue), TreeNode.class);
    }

    private static <T extends Tree> Tree findRootNode(String rootId, List<T> nodes) {
        Optional<T> result = nodes.stream().filter(node -> rootId.equals(node.fetchCurrentNodeId())).findFirst();
        if(!result.isPresent()) {
            Optional<T> parent = nodes.stream().filter(node -> node.fetchParentNodeId().equals(rootId)).findFirst();
            if(parent.isPresent()){
                RootNode vn = new RootNode();
                vn.setNodeId(rootId);
                return vn;
            }
            throw new RuntimeException("Not found this root node, node id: " + rootId);
        }
        return result.get();
    }

    private static boolean isEmptyNode(Tree node) {
        return "".equals(node.fetchCurrentNodeId()) || null == node.fetchCurrentNodeId();
    }
}
