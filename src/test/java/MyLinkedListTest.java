import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class MyLinkedListTest {
    @Test
    void testSizeEmptyList() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        assertEquals(0, list.size());
    }

    @Test
    void testSizeMultipleElements() {
        MyLinkedList<String> list = createMyLinkedListWithThreeStrings();
        assertEquals(3, list.size());
    }

    @Test
    void testContainsNullPointerException() {
        MyLinkedList<Integer> list = createMyLinkedListWithThreeIntegers();
        NullPointerException npe = assertThrows(NullPointerException.class, () -> list.contains(null));
        assertEquals("Please enter a valid data", npe.getMessage());
    }

    @Test
    void testContainsEmptyList() {
        MyLinkedList<String> list = new MyLinkedList<>();
        assertEquals(0, list.size());
        assertFalse(list.contains("one"));
    }

    @Test
    void testContainsMultipleElements() {
        MyLinkedList<String> list = createMyLinkedListWithThreeStrings();
        assertEquals(3, list.size());
        assertTrue(list.contains("one"));
        assertTrue(list.contains("two"));
        assertTrue(list.contains("three"));
        assertFalse(list.contains("four"));
    }

    @Test
    void testSetNullPointerException() {
        MyLinkedList<String> list = createMyLinkedListWithThreeStrings();
        NullPointerException npe = assertThrows(NullPointerException.class, () -> list.set(1, null));
        assertEquals("Please enter a valid data", npe.getMessage());
    }

    @Test
    void testSetIndexOutOfBoundsException() {
        MyLinkedList<String> list = createMyLinkedListWithThreeStrings();
        assertEquals(3, list.size());
        IndexOutOfBoundsException iob1 = assertThrows(IndexOutOfBoundsException.class, () -> list.set(3, "four"));
        assertEquals("There is no such an index in the list", iob1.getMessage());
        IndexOutOfBoundsException iob2 = assertThrows(IndexOutOfBoundsException.class, () -> list.set(-1, "four"));
        assertEquals("There is no such an index in the list", iob2.getMessage());
    }

    @Test
    void testSetIndexOutOfBoundsExceptionWithEmptyList() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        assertEquals(0, list.size());
        IndexOutOfBoundsException iobe = assertThrows(IndexOutOfBoundsException.class, () -> list.set(0, 1));
        assertEquals("There is no such an index in the list", iobe.getMessage());
    }

    @Test
    void testSetMultipleElement() {
        MyLinkedList<String> list = createMyLinkedListWithThreeStrings();
        assertEquals(3, list.size());
        assertEquals("two", list.get(1));
        list.set(1, "four");
        assertEquals("four", list.get(1));
        assertEquals("one", list.get(0));
        list.set(0, "five");
        assertEquals("five", list.get(0));
        assertEquals("three", list.get(2));
        list.set(2, "six");
        assertEquals("six", list.get(2));
    }

    @Test
    void testAddWitIndexNullPointerException() {
        MyLinkedList<String> list = createMyLinkedListWithThreeStrings();
        NullPointerException npe = assertThrows(NullPointerException.class, () -> list.add(1, null));
        assertEquals("Please enter a valid data", npe.getMessage());
    }

    @Test
    void testAddWitIndexIndexOutOfBoundsException() {
        MyLinkedList<String> list = createMyLinkedListWithThreeStrings();
        assertEquals(3, list.size());
        IndexOutOfBoundsException iob1 = assertThrows(IndexOutOfBoundsException.class, () -> list.add(3, "four"));
        assertEquals("There is no such an index in the list", iob1.getMessage());
        IndexOutOfBoundsException iob2 = assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, "four"));
        assertEquals("There is no such an index in the list", iob2.getMessage());
    }

    @Test
    void testAddWithIndexMultipleElement() {
        MyLinkedList<String> list = createMyLinkedListWithThreeStrings();
        assertEquals(3, list.size());
        assertEquals("two", list.get(1));
        list.add(1, "four");
        assertEquals(4, list.size());
        assertEquals("four", list.get(1));
        assertEquals("two", list.get(2));
        assertEquals("one", list.get(0));
    }

    @Test
    void testAddNullPointerException() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        NullPointerException npe = assertThrows(NullPointerException.class, () -> list.add(null));
        assertEquals("Please enter a valid data", npe.getMessage());
    }

    @Test
    void testAddEmptyList() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        assertEquals(0, list.size());
        assertNull(list.getHead());
        assertNull(list.getTail());
        list.add(1);
        assertEquals(1, list.size());
        assertEquals(list.getHead(), list.getTail());
        assertNotNull(list.getHead());
        assertEquals(1, list.getHead().getValue());
    }

    @Test
    void testAddMultipleElements() {
        MyLinkedList<String> list = createMyLinkedListWithThreeStrings();
        assertEquals(3, list.size());
        assertEquals("three", list.getTail().getValue());
        list.add("four");
        assertEquals(4, list.size());
        assertEquals("four", list.getTail().getValue());
        assertEquals("three", list.getTail().getPrevious().getValue());
    }

    @Test
    void testRemoveIndexOutOfBoundsException() {
        MyLinkedList<Integer> list = createMyLinkedListWithThreeIntegers();
        assertEquals(3, list.size());
        IndexOutOfBoundsException ioobe1 = assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
        IndexOutOfBoundsException ioobe2 = assertThrows(IndexOutOfBoundsException.class, () -> list.remove(3));
        assertEquals("There is no such an index in the list", ioobe1.getMessage());
        assertEquals("There is no such an index in the list", ioobe2.getMessage());
    }

    @Test
    void testRemoveEmptyListIndexOutOfBoundsException() {
        MyLinkedList<String> list = new MyLinkedList<>();
        assertEquals(0, list.size());
        IndexOutOfBoundsException ioobe = assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
        assertEquals("There is no such an index in the list", ioobe.getMessage());
    }

    @Test
    void testRemoveFirstElementFromMultiElementList() {
        MyLinkedList<String> list = createMyLinkedListWithThreeStrings();
        assertEquals(3, list.size());
        assertEquals("one", list.get(0));
        list.remove(0);
        assertEquals(2, list.size());
        assertEquals("two", list.get(0));
    }

    @Test
    void testRemoveLastElementFromMultiElementList() {
        MyLinkedList<String> list = createMyLinkedListWithThreeStrings();
        assertEquals(3, list.size());
        assertEquals("three", list.get(2));
        list.remove(2);
        assertEquals(2, list.size());
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(2));
        assertNotEquals("three", list.get(1));
    }

    @Test
    void testRemoveMidlElementFromMultiElementList() {
        MyLinkedList<String> list = createMyLinkedListWithThreeStrings();
        assertEquals(3, list.size());
        assertEquals("two", list.get(1));
        list.remove(1);
        assertEquals(2, list.size());
        assertNotEquals("two", list.get(1));
        assertEquals("three", list.get(1));
    }

    @Test
    void testGetIndexOutOfBoundsExceptionMultiElementList() {
        MyLinkedList<Integer> list = createMyLinkedListWithThreeIntegers();
        assertEquals(3, list.size());
        IndexOutOfBoundsException ioobe1 = assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        IndexOutOfBoundsException ioobe2 = assertThrows(IndexOutOfBoundsException.class, () -> list.get(3));
        assertEquals("There is no such an index in the list", ioobe1.getMessage());
        assertEquals("There is no such an index in the list", ioobe2.getMessage());
    }

    @Test
    void testGetIndexOutOfBoundsExceptionEmptyList() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        assertEquals(0, list.size());
        IndexOutOfBoundsException ioobe = assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
        assertEquals("There is no such an index in the list", ioobe.getMessage());
    }
    @Test
    void testIterator(){
        MyLinkedList<String> list = createMyLinkedListWithThreeStrings();
        Iterator<String> iterator = list.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("one", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("two", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("three", iterator.next());
        assertFalse(iterator.hasNext());
    }
    @Test
    void testAddFirstEmptyList(){
        MyLinkedList<String> list = new MyLinkedList<>();
        assertEquals(0, list.size());
        list.addFirst("one");
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
    }
    @Test
    void testAddFirstMultipleList(){
        MyLinkedList<String> list = createMyLinkedListWithThreeStrings();
        assertEquals(3, list.size());
        list.addFirst("four");
        assertEquals(4, list.size());
        assertEquals("four", list.get(0));
        assertEquals("one", list.get(1));
    }

    @Test
    void testAddLastEmptyList(){
        MyLinkedList<String> list = new MyLinkedList<>();
        assertEquals(0, list.size());
        list.addLast("one");
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
    }
    @Test
    void testAddLastMultipleList(){
        MyLinkedList<String> list = createMyLinkedListWithThreeStrings();
        assertEquals(3, list.size());
        list.addLast("four");
        assertEquals(4, list.size());
        assertEquals("four", list.get(3));
        assertEquals("three", list.get(2));
    }
    @Test
    void testGetFirstEmptyList(){
        MyLinkedList<Integer> list = new MyLinkedList<>();
        assertEquals(0, list.size());
        NoSuchElementException nsee = assertThrows(NoSuchElementException.class, list::getFirst);
        assertEquals("List is empty", nsee.getMessage());
    }
    @Test
    void testGetFirstMultiElementList(){
        MyLinkedList<String> list = createMyLinkedListWithThreeStrings();
        assertEquals(3, list.size());
        assertEquals("one", list.get(0));
        assertEquals("one", list.getFirst());
    }
    @Test
    void testGetLastEmptyList(){
        MyLinkedList<Integer> list = new MyLinkedList<>();
        assertEquals(0, list.size());
        NoSuchElementException nsee = assertThrows(NoSuchElementException.class, list::getLast);
        assertEquals("List is empty", nsee.getMessage());
    }
    @Test
    void testGetLastMultiElementList(){
        MyLinkedList<String> list = createMyLinkedListWithThreeStrings();
        assertEquals(3, list.size());
        assertEquals("three", list.get(2));
        assertEquals("three", list.getLast());
    }
    @Test
    void testRemoveFirstEmptyList(){
        MyLinkedList<Integer> list = new MyLinkedList<>();
        assertEquals(0, list.size());
        NoSuchElementException nsee = assertThrows(NoSuchElementException.class, list::removeFirst);
        assertEquals("List is empty", nsee.getMessage());
    }
    @Test
    void testRemoveFirstMultiElementList(){
        MyLinkedList<String> list = createMyLinkedListWithThreeStrings();
        assertEquals(3, list.size());
        assertEquals("one", list.getFirst());
        assertEquals("one", list.removeFirst());
        assertEquals(2,list.size());
        assertEquals("two", list.getFirst());
    }
    @Test
    void testRemoveLastEmptyList(){
        MyLinkedList<Integer> list = new MyLinkedList<>();
        assertEquals(0, list.size());
        NoSuchElementException nsee = assertThrows(NoSuchElementException.class, list::removeLast);
        assertEquals("List is empty", nsee.getMessage());
    }
    @Test
    void testRemoveLastMultiElementList(){
        MyLinkedList<String> list = createMyLinkedListWithThreeStrings();
        assertEquals(3, list.size());
        assertEquals("three", list.getLast());
        assertEquals("three", list.removeLast());
        assertEquals(2,list.size());
        assertEquals("two", list.getLast());
    }


    @Test
    void testToStringEmptyList() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        assertEquals("[]", list.toString());
    }

    @Test
    void testToStringSingleElement() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(1);
        assertEquals("[1]", list.toString());
    }

    @Test
    void testToStringMultipleElement() {
        MyLinkedList<Integer> integerList = createMyLinkedListWithThreeIntegers();
        MyLinkedList<String> stringList = createMyLinkedListWithThreeStrings();
        assertEquals("[1, 2, 3]", integerList.toString());
        assertEquals("[one, two, three]", stringList.toString());

    }

    private MyLinkedList<Integer> createMyLinkedListWithThreeIntegers() {
        MyLinkedList<Integer> linkedList = new MyLinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        return linkedList;
    }

    private MyLinkedList<String> createMyLinkedListWithThreeStrings() {
        MyLinkedList<String> linkedList = new MyLinkedList<>();
        linkedList.add("one");
        linkedList.add("two");
        linkedList.add("three");
        return linkedList;
    }

}