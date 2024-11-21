import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.function.IntBinaryOperator;

import static org.junit.jupiter.api.Assertions.*;

class SetTest {

    public int[] insertHelper(int[] x) {
        Set Testing = new Set();

        for (int i=0; i < x.length; i++) {
            Testing.insert(x[i]);
        }
        return Testing.toArray();
    }

    public boolean memberHelper(int[] x, int y) {
        Set Testing = new Set();
        for (int i=0; i < x.length; i++) {
            Testing.insert(x[i]);
        }
        return Testing.member(y);
    }

    public int[] intersectHelper(int[] x, int[] y) {
        Set Testing = new Set();
        Set Testing2 = new Set();
        for (int i=0; i < x.length; i++) {
            Testing.insert(x[i]);
        }
        for (int i=0; i < y.length; i++) {
            Testing2.insert(y[i]);
        }
        Testing.intersect(Testing2);
        return Testing.toArray();
    }

    public boolean distinctHelper(int[] x, IntBinaryOperator y) {
        Set Testing = new Set();
        for (int i=0; i < x.length; i++) {
            Testing.insert(x[i]);
        }
        return Testing.distinctClosed(y);
    }
    @Test
    void insert() {
        assertAll(() -> assertArrayEquals(new int[]{4}, insertHelper(new int[]{4})),
                () -> assertArrayEquals(new int[]{4, 8}, insertHelper(new int[]{4, 8})),
                () -> assertArrayEquals(new int[]{4, 8}, insertHelper(new int[]{8, 4})),
                () -> assertArrayEquals(new int[]{4, 4}, insertHelper(new int[]{4, 4})));
    }

    @Test
    void member() {
        assertAll(() -> assertFalse(memberHelper(new int[]{1}, 5)),
                () -> assertFalse(memberHelper(new int[]{4, 8}, 6)),
                () -> assertTrue(memberHelper(new int[]{4, 8}, 8)));
    }

    @Test
    void intersect() {
        assertArrayEquals(new int[]{1}, intersectHelper(new int[]{1, 3, 5}, new int[]{1,2,6,7}));
    }

    @Test
    void distinctClosed() {
        //assertTrue(distinctHelper(new int[]{}, ));
    }

}