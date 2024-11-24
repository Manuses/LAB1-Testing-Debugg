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
    void test_insert_inOrder()
    {
        set.insert(1);
        set.insert(2);
        set.insert(3);
        int[] setarray = set.toArray();
        assertEquals(3, setarray.length);
        assertEquals(1, setarray[0]);
        assertEquals(2, setarray[1]);
        assertEquals(3, setarray[2]);
    }

    @Test
    void test_insert_notInOrder()
    {
        set.insert(3);
        set.insert(2);
        set.insert(1);
        int[] setarray = set.toArray();
        assertEquals(3, setarray.length);
        assertEquals(1, setarray[0]);
        assertEquals(2, setarray[1]);
        assertEquals(3, setarray[2]);
    }

    @Test
    void test_insert_noDuplicates()
    {
        set.insert(1);
        set.insert(1);
        int[] setarray = set.toArray();
        assertEquals(1, setarray.length);
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
        assertEquals(2, setarray.length);
        assertEquals(2, setarray[0]);
        assertEquals(3, setarray[1]);
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
        assertEquals(3, setarray.length);
        assertEquals(1, setarray[0]);
        assertEquals(2, setarray[1]);
        assertEquals(3, setarray[2]);
    }
    @Test
    void test_intersect_empty()
    {
        set.insert(1);
        set.insert(2);
        set.insert(3);
        set.intersect(set2);
        int[] setarray = set.toArray();
        assertEquals(0, setarray.length);
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
