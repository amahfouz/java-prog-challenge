/*
 * CONFIDENTIAL
 * Copyright 2013 Webalo, Inc.  All rights reserved.
 */

package mahfouz.util.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Graph node.
 *
 * The set of "next" nodes holds all adjacent nodes in case of
 * an undirected graph and holds only the set of next nodes in
 * case of a directed graph.
 *
 * Data object must be suitable for use in a map (must implement
 * hashcode and equals).
 */
public final class GNode<T> {

    private final T data;
    final ArrayList<GNode<T>> next = new ArrayList<GNode<T>>();

    private boolean visited = false;
    private boolean enqueued = false;

    public GNode(T data) {
        if (data == null)
            throw new IllegalArgumentException();

        this.data = data;
    }

    public void setEnqueued(boolean isEnqueued) {
        this.enqueued = isEnqueued;
    }

    public T getData() {
        return data;
    }

    public boolean isVisited() {
        return visited;
    }

    public boolean isEnqueued() {
        return enqueued;
    }

    public List<GNode<T>> getNextNodes() {
        return Collections.unmodifiableList(next);
    }

    public void addNext(GNode<T> nextNode) {
        next.add(nextNode);
    }

    public void setVisited(boolean isVisited) {
        this.visited = isVisited;
    }

    public int hashCode() {
        return 31 + ((this.data == null) ? 0 : this.data.hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GNode other = (GNode)obj;
        if (this.data == null) {
            if (other.data != null)
                return false;
        }
        else if (! this.data.equals(other.data))
            return false;
        return true;
    }
}