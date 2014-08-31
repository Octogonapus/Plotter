package plotter;

/**
 * @author Octogonapus
 */

public class BetterPair2f<K, V>
{
    private K key;
    private V value;

    public BetterPair2f()
    {
        this.key = null;
        this.value = null;
    }

    public BetterPair2f(K key)
    {
        this.key = key;
        this.value = null;
    }

    public BetterPair2f(K key, V value)
    {
        this.key = key;
        this.value = value;
    }

    public K getKey()
    {
        return key;
    }

    public void setKey(K key)
    {
        this.key = key;
    }

    public V getValue()
    {
        return value;
    }

    public void setValue(V value)
    {
        this.value = value;
    }
}