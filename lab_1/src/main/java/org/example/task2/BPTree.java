package org.example.task2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class BPTree<V> {
    public static final int MAX_KEYS = 6;

    private Node<V> root = new LeafNode<>();

    public Optional<V> search(int key) {
        return search(key, null);
    }

    public Optional<V> search(int key, List<String> trace) {
        addTrace(trace, "SEARCH_START");

        Node<V> current = root;
        while (!current.isLeaf()) {
            addTrace(trace, "SEARCH_DESCEND_INTERNAL");
            InternalNode<V> internal = (InternalNode<V>) current;
            int childIndex = findChildIndex(internal.keys, key);
            addTrace(trace, "SEARCH_CHILD_" + childIndex);
            current = internal.children.get(childIndex);
        }

        addTrace(trace, "SEARCH_IN_LEAF");
        LeafNode<V> leaf = (LeafNode<V>) current;
        int pos = Collections.binarySearch(leaf.keys, key);
        if (pos >= 0) {
            addTrace(trace, "SEARCH_FOUND");
            return Optional.ofNullable(leaf.values.get(pos));
        }

        addTrace(trace, "SEARCH_NOT_FOUND");
        return Optional.empty();
    }

    public void insert(int key, V value) {
        insert(key, value, null);
    }

    public void insert(int key, V value, List<String> trace) {
        Objects.requireNonNull(value, "Value cannot be null");
        addTrace(trace, "INSERT_START");

        SplitResult<V> split = insertRecursive(root, key, value, trace);

        if (split != null) {
            addTrace(trace, "CREATE_NEW_ROOT");
            InternalNode<V> newRoot = new InternalNode<>();
            newRoot.keys.add(split.promotedKey);
            newRoot.children.add(split.left);
            newRoot.children.add(split.right);
            root = newRoot;
        }
    }

    public int height() {
        int h = 0;
        Node<V> current = root;
        while (!current.isLeaf()) {
            h++;
            current = ((InternalNode<V>) current).children.getFirst();
        }
        return h + 1;
    }

    public boolean isEmpty() {
        LeafNode<V> leaf = getFirstLeaf();
        return leaf.keys.isEmpty();
    }

    public List<Integer> getAllKeysInOrder() {
        List<Integer> result = new ArrayList<>();
        LeafNode<V> current = getFirstLeaf();
        while (current != null) {
            result.addAll(current.keys);
            current = current.next;
        }
        return result;
    }

    private SplitResult<V> insertRecursive(Node<V> node, int key, V value, List<String> trace) {
        if (node.isLeaf()) {
            if (node == root) {
                addTrace(trace, "ROOT_IS_LEAF");
            }

            LeafNode<V> leaf = (LeafNode<V>) node;
            addTrace(trace, "INSERT_INTO_LEAF");

            int pos = Collections.binarySearch(leaf.keys, key);
            if (pos >= 0) {
                addTrace(trace, "UPDATE_EXISTING_KEY");
                leaf.values.set(pos, value);
                addTrace(trace, "LEAF_OK");
                return null;
            }

            int insertPos = -pos - 1;
            leaf.keys.add(insertPos, key);
            leaf.values.add(insertPos, value);

            if (leaf.keys.size() <= MAX_KEYS) {
                addTrace(trace, "LEAF_OK");
                return null;
            }

            addTrace(trace, "LEAF_SPLIT");
            return splitLeaf(leaf);
        }

        addTrace(trace, "DESCEND_INTERNAL");
        InternalNode<V> internal = (InternalNode<V>) node;
        int childIndex = findChildIndex(internal.keys, key);
        addTrace(trace, "CHOOSE_CHILD_" + childIndex);

        SplitResult<V> childSplit = insertRecursive(internal.children.get(childIndex), key, value, trace);

        if (childSplit == null) {
            addTrace(trace, "INTERNAL_OK");
            return null;
        }

        addTrace(trace, "INSERT_PROMOTED_TO_PARENT");
        internal.keys.add(childIndex, childSplit.promotedKey);
        internal.children.set(childIndex, childSplit.left);
        internal.children.add(childIndex + 1, childSplit.right);

        if (internal.keys.size() <= MAX_KEYS) {
            addTrace(trace, "INTERNAL_OK");
            return null;
        }

        addTrace(trace, "INTERNAL_SPLIT");
        return splitInternal(internal);
    }

    private SplitResult<V> splitLeaf(LeafNode<V> leaf) {
        int mid = (leaf.keys.size() + 1) / 2;

        LeafNode<V> right = new LeafNode<>();
        right.keys.addAll(leaf.keys.subList(mid, leaf.keys.size()));
        right.values.addAll(leaf.values.subList(mid, leaf.values.size()));

        leaf.keys.subList(mid, leaf.keys.size()).clear();
        leaf.values.subList(mid, leaf.values.size()).clear();

        right.next = leaf.next;
        leaf.next = right;

        int promotedKey = right.keys.getFirst();
        return new SplitResult<>(promotedKey, leaf, right);
    }

    private SplitResult<V> splitInternal(InternalNode<V> internal) {
        int midIndex = internal.keys.size() / 2;
        int promotedKey = internal.keys.get(midIndex);

        InternalNode<V> right = new InternalNode<>();

        right.keys.addAll(internal.keys.subList(midIndex + 1, internal.keys.size()));
        right.children.addAll(internal.children.subList(midIndex + 1, internal.children.size()));

        internal.keys.subList(midIndex, internal.keys.size()).clear();
        internal.children.subList(midIndex + 1, internal.children.size()).clear();

        return new SplitResult<>(promotedKey, internal, right);
    }

    private int findChildIndex(List<Integer> keys, int key) {
        int i = 0;
        while (i < keys.size() && key >= keys.get(i)) {
            i++;
        }
        return i;
    }

    private LeafNode<V> getFirstLeaf() {
        Node<V> current = root;
        while (!current.isLeaf()) {
            current = ((InternalNode<V>) current).children.getFirst();
        }
        return (LeafNode<V>) current;
    }

    private void addTrace(List<String> trace, String point) {
        if (trace != null) {
            trace.add(point);
        }
    }

    private abstract static class Node<V> {
        protected final List<Integer> keys = new ArrayList<>();

        abstract boolean isLeaf();
    }

    private static final class InternalNode<V> extends Node<V> {
        private final List<Node<V>> children = new ArrayList<>();

        @Override
        boolean isLeaf() {
            return false;
        }
    }

    private static final class LeafNode<V> extends Node<V> {
        private final List<V> values = new ArrayList<>();
        private LeafNode<V> next;

        @Override
        boolean isLeaf() {
            return true;
        }
    }

    private record SplitResult<V>(int promotedKey, Node<V> left, Node<V> right) {
    }
}