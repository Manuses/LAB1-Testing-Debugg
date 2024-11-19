import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class SetTest {
    Set set;
    Set set2;

    @BeforeEach
    void setUp() {
        set = new Set();
        set2 = new Set();
    }

    @Test
    void test_insert_inOrder(){
        set.insert(1);
        set.insert(2);
        set.insert(3);
        int[] setarray = set.toArray();
        assertEquals(setarray.length, 3);
        assertEquals(setarray[0], 1);
        assertEquals(setarray[1], 2);
        assertEquals(setarray[2], 3);
    }
    @Test
    void test_insert_notInOrder()
    {
        set.insert(3);
        set.insert(2);
        set.insert(1);
        int[] setarray = set.toArray();
        assertEquals(setarray.length, 3);
        assertEquals(setarray[0], 1);
        assertEquals(setarray[1], 2);
        assertEquals(setarray[2], 3);
    }
    @Test
    void test_insert_noDuplicates()
    {
        set.insert(1);
        set.insert(1);
        int[] setarray = set.toArray();
        assertEquals(setarray.length, 1);
    }

    @Test
    void test_member_true()
    {
        set.insert(1);
        set.insert(2);
        set.insert(3);
        assertTrue(set.member(1));
        assertTrue(set.member(2));
        assertTrue(set.member(3));
    }

    @Test
    void test_member_false()
    {
        set.insert(1);
        set.insert(2);
        set.insert(3);
        assertFalse(set.member(4));
    }

    @Test
    void test_member_empty()
    {
        assertFalse(set.member(2));
    }
    @Test
    void test_intersect_removing()
    {
        set.insert(1);
        set.insert(2);
        set.insert(3);
        set.insert(4);
        set2.insert(3);
        set2.insert(2);
        set.intersect(set2);
        int[] setarray = set.toArray();
        assertEquals(setarray.length, 2);
        assertEquals(setarray[0], 2);
        assertEquals(setarray[1], 3);
    }
    @Test
    void test_intersect_nothingToRemove()
    {
        set.insert(1);
        set.insert(2);
        set.insert(3);
        set2.insert(1);
        set2.insert(2);
        set2.insert(3);
        set.intersect(set2);
        int[] setarray = set.toArray();
        assertEquals(setarray.length, 3);
        assertEquals(setarray[0], 1);
        assertEquals(setarray[1], 2);
        assertEquals(setarray[2], 3);
    }
    @Test
    void test_intersect_empty()
    {
        set.insert(1);
        set.insert(2);
        set.insert(3);
        set.intersect(set2);
        int[] setarray = set.toArray();
        assertEquals(setarray.length, 0);
    }
    @Test
    void test_distinctClosed_false_addition()
    {
        set.insert(1);
        set.insert(2);
        set.insert(3);
        assertFalse(set.distinctClosed((a, b) -> a + b));
    }
    @Test
    void test_distinctClosed_false_subtraction()
    {
        set.insert(1);
        set.insert(2);
        set.insert(3);
        assertFalse(set.distinctClosed((a, b) -> a - b));
    }
    @Test
    void test_distinctClosed_true_addition_emptySet()
    {
        assertTrue(set.distinctClosed((a, b) -> a - b));
    }
    @Test
    void test_distinctClosed_true_singleElement() {
        set.insert(1);
        assertTrue(set.distinctClosed((a, b) -> a + b));
    }

    @Test
    void test_distinctClosed_false_multiplication() {
        set.insert(1);
        set.insert(2);
        set.insert(3);
        assertFalse(set.distinctClosed((a, b) -> a * b));
    }

}
