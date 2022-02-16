package structures;

import helpers.KeyValuePair;
import interfaces.ICollection;
import interfaces.IMap;
import interfaces.ISet;

import java.util.Iterator;

/**
 * @author Ruslan Bessarab
 * @version 1.0
 *
 * Represents Map which stores key-value pairs, can manipulate pair objects;
 * can manipulate both keys and values
 *
 * @param <K> key
 * @param <V> value
 */
public class Map<K, V> implements IMap<K, V>
{
    private HashTable<KeyValuePair<K, V>> map;

    /**
     * Default constructor
     */
    public Map() {
        map = new HashTable<>();
    }

    @Override
    public void add(K key, V value)
    {
        KeyValuePair<K, V> pair = new KeyValuePair<>(key, value);
        if(map.contains(pair)) {
            map.remove(pair);
        }
        map.add(pair);
    }

    @Override
    public void remove(K key)
    {
        KeyValuePair<K, V> pair = new KeyValuePair<>(key, null);
        if(map.contains(pair)) {
            map.remove(pair);
        }
    }

    @Override
    public V get(K key)
    {
        KeyValuePair<K, V> pair = new KeyValuePair<>(key, null);
        if(map.contains(pair)) {
            return map.get(pair).getValue();
        }
        return null;
    }

    @Override
    public boolean keyExists(K key)
    {
        KeyValuePair<K, V> pair = new KeyValuePair<>(key, null);
        return map.contains(pair);
    }

    @Override
    public boolean valueExists(V value)
    {
        for (KeyValuePair<K, V> element : map) {
            if(element.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size()
    {
        return map.size();
    }

    @Override
    public boolean isEmpty()
    {
        return map.isEmpty();
    }

    @Override
    public void clear()
    {
        map.clear();
    }

    @Override
    public Iterator<KeyValuePair<K, V>> iterator()
    {
        return map.iterator();
    }

    @Override
    public ISet<K> keyset()
    {
        ISet<K> set = new Set<>();
        for (KeyValuePair<K, V> element : map) {
            set.add(element.getKey());
        }
        return set;
    }

    @Override
    public ICollection<V> values()
    {
        ICollection<V> collection = new HashTable<>();
        for (KeyValuePair<K, V> element : map) {
            collection.add(element.getValue());
        }
        return collection;
    }

    @Override
    public String toString() {
        return "Map{" +
                "map=" + map +
                '}';
    }
}
