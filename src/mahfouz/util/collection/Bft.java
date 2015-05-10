/*
 * CONFIDENTIAL
 * Copyright 2013 Webalo, Inc.  All rights reserved.
 */

package mahfouz.util.collection;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Breadth-first traversal.
 */
public final class Bft<T> {

    private final LinkedList<GNode<T>> toVisit = new LinkedList<GNode<T>>();
    private final Bft.Receiver<T> receiver;

    public Bft(GNode<T> start, Bft.Receiver<T> receiver) {
        if (start == null || receiver == null)
            throw new IllegalArgumentException();

        this.receiver = receiver;

        enqueue(start);
    }

    private void enqueue(GNode<T> node) {
        if (node.isEnqueued())
            return;

        toVisit.add(node);
        node.setEnqueued(true);
    }

    public void traverse() {
        while (! toVisit.isEmpty()) {
            GNode node = toVisit.remove();
            boolean continueTraversal = receiver.handleNode(node);
            node.setVisited(true);

            if (! continueTraversal)
                return;

            ArrayList<GNode> nexts = node.next;
            for (GNode next : nexts)
                enqueue(next);
        }
    }

//        private static final class QueueSet<T> {
//
//            private final LinkedList<Node<T>> list = new LinkedList<Node<T>>();
//            private final Set<Node<T>> set = new HashSet<Node<T>>();
//
//            public boolean isEmpty() {
//                return list.isEmpty();
//            }
//
//            public Node<T> remove() {
//                Node<T> node = list.remove();
//                set.remove(node);
//
//                return node;
//            }
//
//            public void add(Node<T> node) {
//                // only add node if not already in set
//                if (! set.add(node))
//                    list.add(node);
//            }
//        }

    /**
     * Handler for nodes being visited during traversal.
     */
    public static interface Receiver<T> {

        /**
         * Returns true if traversal is to continue.
         */
        boolean handleNode(GNode<T> node);
    }
}