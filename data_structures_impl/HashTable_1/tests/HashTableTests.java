import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import structures.HashTable;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

/**
 * @author Ruslan Bessarab
 * @version 1.0
 */
public class HashTableTests {
    public static final int ADDING_TEST = 20;//for adding tests
    public static final int EXPECTED = 20; //for adding tests
    private HashTable<Integer> table;

    /**
     * Creates a new empty table before each test
     */
    @Before
    public void setup()
    {
        table = new HashTable<>();
    }

    /**
     * Tests add method
     */
    @Test
    public void addTest() {
        for (int i = 0; i < ADDING_TEST; i++) {
            table.add(i);
        }
        Assert.assertEquals("size() return incorrect amount of elements.", EXPECTED, table.size());

        for (int i = 0; i < ADDING_TEST; i++) {
            Assert.assertTrue("element is not in the table after adding it into table", table.contains(i));
        }
    }

    /**
     * adding duplicates
     */
    @Test (expected = IllegalArgumentException.class)
    public void addDuplicates() {
        for (int i = 0; i < ADDING_TEST; i++) {
            table.add(i);
        }

        table.add(10);
    }

    /**
     * Tests resizing when adding more than 250% load factor
     */
    @Test
    public void resizeTest() {
        for (int i = 0; i < ADDING_TEST; i++) {
            table.add(i);
        }
        Assert.assertEquals("Size return incorrect amount of elements", table.size(), ADDING_TEST);

        for (int i = ADDING_TEST; i < (ADDING_TEST + 6); i++) {
            table.add(i);
        }
        Assert.assertEquals("Size was not change", table.size(), (ADDING_TEST + 6));
    }

    /**
     * Tests remove
     */
    @Test
    public void removeTest() {
        for (int i = 0; i < ADDING_TEST; i++) {
            table.add(i);
        }
        table.remove(10);

        Assert.assertFalse("Element wasn't removed", table.contains(10));
    }

    /**
     * Tests remove the element that was removed before
     */
    @Test (expected = NoSuchElementException.class)
    public void notInTableRemove() {
        for (int i = 0; i < ADDING_TEST; i++) {
            table.add(i);
        }
        table.remove(10);
        table.remove(10);
    }

    /**
     * Tests contains
     */
    @Test
    public void testContains() {
        for (int i = 0; i < ADDING_TEST; i++) {
            table.add(i);
        }

        Assert.assertTrue("Contains returns false when element is in the table", table.contains(10));

        //contains after removal
        table.remove(10);
        Assert.assertFalse("Contains returns true when element is not in the table", table.contains(10));
    }

    /**
     * Size, isEmpty and clear test
     */
    @Test
    public void isEmptyClearTest() {
        for (int i = 0; i < ADDING_TEST; i++) {
            table.add(i);
        }

        Assert.assertFalse("isEmpty returns true when the table is not empty", table.isEmpty());

        table.clear();
        Assert.assertTrue("isEmpty returns false when the table is empty", table.isEmpty());
        Assert.assertEquals("After clear() called size wasn't changed", 0, table.size());
    }

    /**
     * Concurrent modification test
     */
    @Test (expected = ConcurrentModificationException.class)
    public void concurrentModTest() {
        for (int i = 0; i < ADDING_TEST; i++) {
            table.add(i);
        }

        for (int n : table) {
            table.add(-5);
        }
    }
}
