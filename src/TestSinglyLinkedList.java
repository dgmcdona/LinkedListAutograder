import java.util.Iterator;
import static org.junit.Assert.*;
import org.junit.*;

public class TestSinglyLinkedList{

    public SinglyLinkedList<Integer> myIntList;
    public SinglyLinkedList<String> myStringList;

    @Before
    public void setUp(){
        myIntList = new SinglyLinkedList<>();                 
        myStringList = new SinglyLinkedList<>();
    }

    /*
     * Test that the size() method exists and returns 0 for newly constructed SinglyLinked Lists.
     */
    @Test
    public void testSize(){
        assertEquals(0, myIntList.size());
        assertEquals(0, myStringList.size());
    }

    /*
     * Test that items are correctly added to the SinglyLinkedList.
     */
    @Test
    public void testAdd(){
        myIntList.add(1);
        myIntList.add(2);
        myIntList.add(3);
        myStringList.add("one");
        myStringList.add("two");
        myStringList.add("three");
        assertEquals(3, myIntList.size());
        assertEquals(3, myStringList.size());
    }

    /* 
     * Test that items are correctly removed from the SinglyLinkedList, and the size is updated.
     */
    @Test
    public void testRemove(){
        myIntList.add(1);
        myIntList.add(2);
        myIntList.add(3);
        myIntList.add(4);
        myStringList.add("one");
        myStringList.add("two");
        myStringList.add("three");
        myStringList.add("four");
        myIntList.remove(1);
        myIntList.remove(4);
        assertEquals(2, myIntList.size());
        myStringList.remove("one");
        myStringList.remove("four");
        assertEquals(2, myStringList.size());
        assertEquals(2, myIntList.size());
        assertEquals(2, myStringList.size());
    }

    /*
     * Test that the getNthFromFirst correctly retrieves elements at specified position, and 
     * throws IndexOutOfBoundsException for invalid indices.
     */
    @Test
    public void testGetNthFromFirst(){
        myIntList.add(1);
        myIntList.add(2);
        myIntList.add(3);
        assertTrue(1 == myIntList.getNthFromFirst(0));
        assertTrue(2 == myIntList.getNthFromFirst(1));
        assertTrue(3 == myIntList.getNthFromFirst(2));
        myStringList.add("one");
        myStringList.add("two");
        myStringList.add("three");
        assertTrue("one".equals(myStringList.getNthFromFirst(0)));
        assertTrue("two".equals(myStringList.getNthFromFirst(1)));
        assertTrue("three".equals(myStringList.getNthFromFirst(2)));
        assertThrows(IndexOutOfBoundsException.class,
                        () -> myIntList.getNthFromFirst(3));
        assertThrows(IndexOutOfBoundsException.class,
                        () -> myStringList.getNthFromFirst(3));
    }

    /*
     * Test that the getNthFromLast correctly retrieves elements at specified position, and 
     * throws IndexOutOfBoundsException for invalid indices.
     */
    @Test
    public void testGetNthFromLast(){
        myIntList.add(1);
        myIntList.add(2);
        myIntList.add(3);
        assertTrue(1 == myIntList.getNthFromLast(2));
        assertTrue(2 == myIntList.getNthFromLast(1));
        assertTrue(3 == myIntList.getNthFromLast(0));
        myStringList.add("one");
        myStringList.add("two");
        myStringList.add("three");
        assertTrue("one".equals(myStringList.getNthFromLast(2)));
        assertTrue("two".equals(myStringList.getNthFromLast(1)));
        assertTrue("three".equals(myStringList.getNthFromLast(0)));
        assertThrows(IndexOutOfBoundsException.class,
                        () -> myIntList.getNthFromLast(3));
        assertThrows(IndexOutOfBoundsException.class,
                        () -> myStringList.getNthFromLast(3));
    }

    @Test
    public void testInsertAt(){
        myIntList.add(1);
        myIntList.add(2);
        myIntList.add(4);
        myIntList.add(5);
        myStringList.add("one");
        myStringList.add("two");
        myStringList.add("four");
        myStringList.add("five");
        myIntList.insertAt(0, 0);
        
    }

    /* 
     * Test that the clear() method correctly resets the list size and updates the head node.
     */
    @Test
    public void testClear(){
        myIntList.add(1);
        myIntList.add(2);
        myIntList.add(3);
        myIntList.add(4);
        myStringList.add("one");
        myStringList.add("two");
        myStringList.add("three");
        myStringList.add("four");
        myIntList.clear();
        myStringList.clear();
        assertThrows(IndexOutOfBoundsException.class,
                        () -> myIntList.getNthFromFirst(0));
        assertThrows(IndexOutOfBoundsException.class,
                        () -> myStringList.getNthFromFirst(0));
    }

    /*
     * Test isEmpty() method and make sure that it returns properly following add(), insertAt(),
     * delete(), and clear()
     */
    @Test
    public void testIsEmpty(){
        myIntList.add(1);
        myIntList.add(2);
        myIntList.add(3);
        myIntList.add(4);
        myStringList.add("one");
        myStringList.add("two");
        myStringList.add("three");
        myStringList.add("four");
        myIntList.clear();
        myStringList.clear();
        assertTrue(myIntList.isEmpty());
        assertTrue(myStringList.isEmpty());
        myIntList.add(1);
        myIntList.add(2);
        myStringList.add("one");
        myStringList.add("two");
        myIntList.remove(1);
        myStringList.remove("one");
        assertTrue(!myIntList.isEmpty());
        assertTrue(!myStringList.isEmpty());
        myIntList.remove(2);
        myStringList.remove("two");
        assertTrue(myIntList.isEmpty());
        assertTrue(myStringList.isEmpty());
    }

    @Test
    public void testNext(){
        myIntList.add(1);
        myIntList.add(2);
        myIntList.add(3);
        myIntList.add(4);
        myStringList.add("one");
        myStringList.add("two");
        myStringList.add("three");
        myStringList.add("four");
        Iterator<Integer> intIterator = myIntList.iterator();
        Iterator<String> stringIterator = myStringList.iterator();
        assertTrue(1 == intIterator.next());
        assertTrue(2 == intIterator.next());
        assertTrue(3 == intIterator.next());
        assertTrue(4 == intIterator.next());
        assertEquals("one", stringIterator.next());
        assertEquals("two", stringIterator.next());
        assertEquals("three", stringIterator.next());
        assertEquals("four", stringIterator.next());
    }

    @Test
    public void testHasNext(){
        myIntList.add(1);
        myIntList.add(2);
        myIntList.add(3);
        myIntList.add(4);
        myStringList.add("one");
        myStringList.add("two");
        myStringList.add("three");
        myStringList.add("four");
        Iterator<Integer> intIterator = myIntList.iterator();
        Iterator<String> stringIterator = myStringList.iterator();
        assertTrue(intIterator.hasNext());
        intIterator.next();
        intIterator.next();
        intIterator.next();
        intIterator.next();
        assertTrue(!intIterator.hasNext());
        assertTrue(stringIterator.hasNext());
        stringIterator.next();
        stringIterator.next();
        stringIterator.next();
        stringIterator.next();
        assertTrue(!stringIterator.hasNext());
    }

    @Test
    public void testIteratorRemove(){
        Iterator<Integer> iter = myIntList.iterator();
        Iterator<String> iter2 = myStringList.iterator();
        assertThrows(UnsupportedOperationException.class, 
                        () -> iter.remove());
        assertThrows(UnsupportedOperationException.class, 
                        () -> iter2.remove());
    }
}
