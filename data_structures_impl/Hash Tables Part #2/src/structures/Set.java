package structures;

import interfaces.ISet;

import java.util.Iterator;

/**
 * @author Ruslan Bessarab
 * @version 1.0
 *
 * Represents a set that stores elements. Doesn't allow duplicates and
 * allows us use set operations such as union, intersection, subset, difference
 * and disjoint.
 *
 * @param <T> type
 */
public class Set<T> implements ISet<T>
{
    private HashTable<T> set;

    /**
     * Default constructor
     */
    public Set() {
        set = new HashTable<>();
    }

    @Override
    public void add(T element)
    {
        try {
            set.add(element);
        } catch (IllegalArgumentException ignored) {
            //if there is exception was thrown, dont do anything
        }
    }

    @Override
    public void remove(T element)
    {
        set.remove(element);
    }

    @Override
    public boolean contains(T element)
    {
        return set.contains(element);
    }

    @Override
    public int size()
    {
        return set.size();
    }

    @Override
    public boolean isEmpty()
    {
        return set.isEmpty();
    }

    @Override
    public void clear()
    {
        set.clear();
    }

    @Override
    public T get(T element)
    {
        return set.get(element);
    }

    @Override
    public Iterator<T> iterator()
    {
        return set.iterator();
    }

    @Override
    public ISet<T> union(ISet<T> other)
    {
        ISet<T> unionSet = new Set<>();
        //adding elements from one set
        for(T element : set) {
            unionSet.add(element);
        }

        //add elements from other set
        for(T element : other) {
            unionSet.add(element);
        }
        return unionSet;
    }

    @Override
    public ISet<T> intersects(ISet<T> other)
    {
        ISet<T> intersectSet = new Set<>();
        for(T element : set) {
            if(other.contains(element)) {
                intersectSet.add(element);
            }
        }

        return intersectSet;
    }

    @Override
    public ISet<T> difference(ISet<T> other)
    {
        ISet<T> difference = new Set<>();
        for(T element : set) {
            if(!other.contains(element)) {
                difference.add(element);
            }
        }

        return difference;
    }

    @Override
    public boolean isSubset(ISet<T> other)
    {
        int counter = 0;
        for (T element : other) {
            if(set.contains(element)) {
                counter++;
            }
        }

        return counter == other.size();
    }

    @Override
    public boolean isDisjoint(ISet<T> other)
    {
        int counter = 0;
        for (T element : set) {
            if(other.contains(element)) {
                counter++;
            }
        }

        return counter == 0;
    }

    @Override
    public boolean isEmptySet()
    {
        return set.isEmpty();
    }

    @Override
    public String toString() {
        return "Set{" +
                "set=" + set +
                '}';
    }
}
