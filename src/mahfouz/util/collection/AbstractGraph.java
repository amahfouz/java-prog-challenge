/*
 * CONFIDENTIAL
 * Copyright 2013 Webalo, Inc.  All rights reserved.
 */

package mahfouz.util.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Abstract base class for a graph.
 */
public abstract class AbstractGraph<T> {

    private final List<GNode> nodes = new ArrayList();
    private final Map<T,GNode> nodeByData = new HashMap<T,GNode>();

    public void add(GNode<T> node) {
        if (node == null)
            throw new IllegalArgumentException();
        nodes.add(node);
        nodeByData.put(node.getData(), node);
    }

    public int getNumNodes() {
        return nodes.size();
    }

    public GNode getNodeByData(T data) {
        return nodeByData.get(data);
    }

    public void resetFlags() {
        for (GNode node : nodes) {
            node.setVisited(false);
            node.setEnqueued(false);
        }
    }
}
