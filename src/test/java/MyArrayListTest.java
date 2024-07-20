import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest {
    private MyArrayList<String> abc;

    @BeforeEach
    void setup() {
        abc = new MyArrayList<>();
    }


    @Test
    void size() {
        assertEquals(abc.size(), 0);
        abc.add("a");
        assertEquals(abc.size(), 1);
    }

    @Test
    void containsPositiveTest() {
        abc.add("a");
        abc.add("b");
        abc.add("c");
        Assertions.assertTrue(abc.contains("b"));
    }

    @Test
    void containsNegativeTest() {
        abc.add("a");
        abc.add("b");
        abc.add("c");
        Assertions.assertFalse(abc.contains("d"));
    }

    @Test
    void containsExceptionTest() {
        abc.add("a");
        abc.add("b");
        abc.add("c");
        NullPointerException npe = assertThrows(NullPointerException.class, () -> abc.contains(null));
        assertEquals("Please enter the correct value", npe.getMessage());
    }

    @Test
    void containsEmptyTest() {
        Assertions.assertFalse(abc.contains("d"));
    }

    @Test
    void setNormalTest() {
        abc.add("a");
        abc.add("b");
        abc.add("c");
        assertEquals("c", abc.get(2));
        abc.set(2, "d");
        assertEquals("d", abc.get(2));
    }

    @Test
    void setIndexOutOfBoundsTest() {
        abc.add("a");
        abc.add("b");
        abc.add("c");
        IndexOutOfBoundsException exception1 = assertThrows(IndexOutOfBoundsException.class, () -> abc.set(4, "d"));
        assertEquals("Index must be from 0 to 2", exception1.getMessage());
        IndexOutOfBoundsException exception2 = assertThrows(IndexOutOfBoundsException.class, () -> abc.set(-1, "d"));
        assertEquals("Index must be from 0 to 2", exception2.getMessage());
    }

    @Test
    void setNullPointerTest() {
        abc.add("a");
        abc.add("b");
        abc.add("c");
        NullPointerException exception = assertThrows( NullPointerException.class, () -> abc.set(2, null));
        assertEquals("Please enter the correct value", exception.getMessage());
    }

    @Test
    void addNormalTest() {
        assertEquals(0, abc.size());
        abc.add("a");
        assertEquals(1, abc.size());
    }

    @Test
    void addIncreaseCapacityTest() {
        assertEquals(0, abc.size());
        abc.add("a");
        abc.add("b");
        abc.add("c");
        abc.add("d");
        abc.add("e");
        assertEquals(5, abc.size());
        assertEquals("c", abc.get(2));
    }

    @Test
    void addWithIndexNormalTest() {
        abc.add("a");
        abc.add("b");
        assertEquals(2, abc.size());
        assertEquals("b", abc.get(1));
        abc.add(1, "c");
        assertEquals(3, abc.size());
        assertEquals("c", abc.get(1));
        assertEquals("b", abc.get(2));
    }

    @Test
    void addWithIndexIncreaseCapacityTest() {
        abc.add("a");
        abc.add("b");
        abc.add("c");
        assertEquals(3, abc.size());
        assertEquals("b", abc.get(1));
        abc.add(1, "c");
        abc.add(2, "e");
        assertEquals(5, abc.size());
        assertEquals("c", abc.get(1));
        assertEquals("e", abc.get(2));
    }

    @Test
    void addWithIndexIndexOutOfBoundsTest() {
        abc.add("a");
        abc.add("b");
        IndexOutOfBoundsException exception1 = assertThrows( IndexOutOfBoundsException.class, () -> abc.add(3, "e"));
        assertEquals("Index must be from 0 to 1", exception1.getMessage());
        IndexOutOfBoundsException exception2 = assertThrows( IndexOutOfBoundsException.class, () -> abc.add(-1, "e"));
        assertEquals("Index must be from 0 to 1", exception2.getMessage());
    }

    @Test
    void addWithIndexNullPointerExceptionTest() {
        abc.add("a");
        abc.add("b");
        NullPointerException exception = assertThrows(NullPointerException.class, () -> abc.add(1, null));
        assertEquals("Please enter the correct value", exception.getMessage());
    }

    @Test
    void removeNormalTest() {
        abc.add("a");
        abc.add("b");
        abc.add("c");
        assertEquals(3, abc.size());
        abc.remove(1);
        assertEquals(2, abc.size());
        assertEquals("c", abc.get(1));
        assertEquals("a", abc.get(0));
    }

    @Test
    void removeLastItemTest() {
        abc.add("a");
        abc.add("b");
        assertEquals(2, abc.size());
        abc.remove(1);
        assertEquals(1, abc.size());
    }

    @Test
    void removeExceptionTest() {
        abc.add("a");
        abc.add("b");
        assertEquals(2, abc.size());
        IndexOutOfBoundsException exception1 = assertThrows( IndexOutOfBoundsException.class, () -> abc.remove(2));
        assertEquals("Index must be from 0 to 1", exception1.getMessage());
        IndexOutOfBoundsException exception2 = assertThrows( IndexOutOfBoundsException.class, () -> abc.remove(-1));
        assertEquals("Index must be from 0 to 1", exception2.getMessage());
    }

    @Test
    void getNormalTest() {
        abc.add("a");
        abc.add("b");
        assertEquals("b", abc.get(1));
    }

    @Test
    void getExceptionTest() {
        abc.add("a");
        abc.add("b");
        IndexOutOfBoundsException exception1 = assertThrows(IndexOutOfBoundsException.class, () -> abc.get(2));
        assertEquals("Index must be from 0 to 1", exception1.getMessage());
        IndexOutOfBoundsException exception2 = assertThrows( IndexOutOfBoundsException.class, () -> abc.get(-1));
        assertEquals("Index must be from 0 to 1", exception2.getMessage());
    }

    @Test
    void testToString() {
        abc.add("a");
        abc.add("b");
        assertEquals("[a,b]", abc.toString());
    }

    @Test
    void iteratorNormalTest() {
        abc.add("a");
        abc.add("b");
        abc.add("c");
        Iterator<String> iterator = abc.iterator();
        Assertions.assertTrue(iterator.hasNext());
        assertEquals("a", iterator.next());
        Assertions.assertTrue(iterator.hasNext());
        assertEquals("b", iterator.next());
        Assertions.assertTrue(iterator.hasNext());
        assertEquals("c", iterator.next());
        Assertions.assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void IteratorNoSuchElementExceptionTest(){
        Iterator<String> iterator = abc.iterator();
        assertThrows(NoSuchElementException.class, iterator::next);
    }
}