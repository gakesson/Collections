package gakesson.util.collections;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * This abstract class decorates a {@link Map} implementation with lazy
 * creation, meaning that the actual instance will not be created until needed
 * (e.g. when putting an element).
 * 
 * This class and it's subclasses are not thread-safe.
 * 
 */
abstract class AbstractLazyMap<K, V> implements Map<K, V> {

	private Map<K, V> myBackingMap = Collections.emptyMap();

	/**
	 * Creates a new {@link AbstractLazyMap} instance.
	 * 
	 * @return a new {@link AbstractLazyMap}.
	 */
	AbstractLazyMap() {
		// Nothing
	}

	@Override
	public int size() {
		return myBackingMap.size();
	}

	@Override
	public boolean isEmpty() {
		return myBackingMap.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return myBackingMap.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return myBackingMap.containsValue(value);
	}

	@Override
	public V get(Object key) {
		return myBackingMap.get(key);
	}

	@Override
	public V put(K key, V value) {
		return getLazyMap().put(key, value);
	}

	@Override
	public V remove(Object key) {
		return myBackingMap.remove(key);
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		getLazyMap().putAll(m);
	}

	@Override
	public void clear() {
		myBackingMap.clear();
	}

	@Override
	public Set<K> keySet() {
		return myBackingMap.keySet();
	}

	@Override
	public Collection<V> values() {
		return myBackingMap.values();
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		return myBackingMap.entrySet();
	}

	@Override
	public int hashCode() {
		return myBackingMap.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		return myBackingMap.equals(o);
	}

	/**
	 * Creates the {@link Map} to lazily use.
	 * 
	 * @return
	 */
	abstract Map<K, V> createMap();

	/**
	 * Returns the lazily created {@link Map}. Note that the {@link Map} will be
	 * created in case it wasn't present prior to calling this method.
	 * 
	 * @return
	 */
	public Map<K, V> getLazilyCreatedMap() {
		return getLazyMap();
	}

	/**
	 * Retrieves (and creates if not already created) the backing {@link Map}.
	 * 
	 * @return
	 */
	private Map<K, V> getLazyMap() {
		if (myBackingMap == Collections.emptyMap()) {
			myBackingMap = createMap();
		}
		return myBackingMap;
	}
}
