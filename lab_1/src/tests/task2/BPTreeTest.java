package task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import task2.util.BPTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BPTreeTest {

    @Test
    void shouldInsertAndSearchSingleElement() {
        BPTree<String> tree = new BPTree<>();

        tree.insert(10, "ten");

        Optional<String> result = tree.search(10);
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals("ten", result.get());
    }

    @Test
    void shouldReturnEmptyForMissingKey() {
        BPTree<String> tree = new BPTree<>();
        tree.insert(10, "ten");
        tree.insert(20, "twenty");

        assertTrue(tree.search(99).isEmpty());
    }

    @Test
    void shouldKeepKeysSortedAcrossLeaves() {
        BPTree<String> tree = new BPTree<>();

        tree.insert(40, "forty");
        tree.insert(10, "ten");
        tree.insert(70, "seventy");
        tree.insert(20, "twenty");
        tree.insert(60, "sixty");
        tree.insert(30, "thirty");
        tree.insert(50, "fifty");

        assertEquals(List.of(10, 20, 30, 40, 50, 60, 70), tree.getAllKeysInOrder());
    }

    @Test
    void shouldSplitLeafWhenMoreThanSixKeysInserted() {
        BPTree<String> tree = new BPTree<>();

        tree.insert(10, "v10");
        tree.insert(20, "v20");
        tree.insert(30, "v30");
        tree.insert(40, "v40");
        tree.insert(50, "v50");
        tree.insert(60, "v60");

        assertEquals(1, tree.height());

        tree.insert(70, "v70");

        assertEquals(2, tree.height());
        assertEquals(List.of(10, 20, 30, 40, 50, 60, 70), tree.getAllKeysInOrder());
    }

    @Test
    void shouldUpdateValueForDuplicateKey() {
        BPTree<String> tree = new BPTree<>();

        tree.insert(10, "old");
        tree.insert(10, "new");

        assertEquals(Optional.of("new"), tree.search(10));
        assertEquals(List.of(10), tree.getAllKeysInOrder());
    }

    @Test
    void shouldSearchThroughInternalNodeAfterSplit() {
        BPTree<String> tree = new BPTree<>();

        for (int key : List.of(10, 20, 30, 40, 50, 60, 70, 80, 90)) {
            tree.insert(key, "v" + key);
        }

        assertEquals(Optional.of("v10"), tree.search(10));
        assertEquals(Optional.of("v50"), tree.search(50));
        assertEquals(Optional.of("v90"), tree.search(90));
    }

    @Test
    void shouldRejectNullValue() {
        BPTree<String> tree = new BPTree<>();

        Assertions.assertThrows(NullPointerException.class, () -> tree.insert(10, null));
    }

    @Test
    void shouldRecordTraceForSimpleInsertWithoutSplit() {
        BPTree<String> tree = new BPTree<>();
        List<String> trace = new ArrayList<>();

        tree.insert(10, "ten", trace);

        Assertions.assertEquals(
                List.of(
                        "INSERT_START",
                        "ROOT_IS_LEAF",
                        "INSERT_INTO_LEAF",
                        "LEAF_OK"
                ),
                trace
        );
    }

    @Test
    void shouldRecordTraceForLeafSplit() {
        BPTree<String> tree = new BPTree<>();

        for (int key : List.of(10, 20, 30, 40, 50, 60)) {
            tree.insert(key, "v" + key);
        }

        List<String> trace = new ArrayList<>();
        tree.insert(70, "v70", trace);

        Assertions.assertEquals(
                List.of(
                        "INSERT_START",
                        "ROOT_IS_LEAF",
                        "INSERT_INTO_LEAF",
                        "LEAF_SPLIT",
                        "CREATE_NEW_ROOT"
                ),
                trace
        );
    }

    @Test
    void shouldRecordTraceForInsertViaInternalNode() {
        BPTree<String> tree = new BPTree<>();

        for (int key : List.of(10, 20, 30, 40, 50, 60, 70)) {
            tree.insert(key, "v" + key);
        }

        List<String> trace = new ArrayList<>();
        tree.insert(80, "v80", trace);

        Assertions.assertEquals(
                List.of(
                        "INSERT_START",
                        "DESCEND_INTERNAL",
                        "CHOOSE_CHILD_1",
                        "INSERT_INTO_LEAF",
                        "LEAF_OK",
                        "INTERNAL_OK"
                ),
                trace
        );
    }

    @Test
    void shouldRecordTraceForSearchFound() {
        BPTree<String> tree = new BPTree<>();
        for (int key : List.of(10, 20, 30, 40, 50, 60, 70)) {
            tree.insert(key, "v" + key);
        }

        List<String> trace = new ArrayList<>();
        Optional<String> result = tree.search(70, trace);

        Assertions.assertEquals(Optional.of("v70"), result);
        Assertions.assertEquals(
                List.of(
                        "SEARCH_START",
                        "SEARCH_DESCEND_INTERNAL",
                        "SEARCH_CHILD_1",
                        "SEARCH_IN_LEAF",
                        "SEARCH_FOUND"
                ),
                trace
        );
    }

    @Test
    void shouldRecordTraceForSearchNotFound() {
        BPTree<String> tree = new BPTree<>();
        for (int key : List.of(10, 20, 30, 40, 50, 60, 70)) {
            tree.insert(key, "v" + key);
        }

        List<String> trace = new ArrayList<>();
        Optional<String> result = tree.search(35, trace);

        Assertions.assertTrue(result.isEmpty());
        Assertions.assertEquals(
                List.of(
                        "SEARCH_START",
                        "SEARCH_DESCEND_INTERNAL",
                        "SEARCH_CHILD_0",
                        "SEARCH_IN_LEAF",
                        "SEARCH_NOT_FOUND"
                ),
                trace
        );
    }
}