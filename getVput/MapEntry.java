/**
 * Created By: Jason Wehran
 * Date Created: June 27, 2023
 */
public class MapEntry<K, V> {
    private K key;
    private V value;

    /**
     * Default constructor
     * @param k
     * @param v
     */
    public MapEntry(K k, V v) {
        key = k;
        value = v;
    }

    /**
     * Getter for key
     * @return
     */
    public K getKey() {
        return key;
    }

    /**
     * Getter for value
     * @return
     */
    public V getValue() {
        return value;
    }

    /**
     * setter for key
     * @param k
     */
    public void setKey(K k) {
        key = k;
    }

    /**
     * setter for value
     * @param v
     */
    public void setValue(V v) {
        value = v;
    }

    /**
     * Returns string representation
     */
    public String toString() {
        return "(" + key + ", " + value + ")";
    }
}