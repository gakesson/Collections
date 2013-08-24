package gakesson.util.collections;

import java.io.Serializable;
import java.util.IdentityHashMap;
import java.util.Map;

/**
 * This class decorates an {@link IdentityHashMap} with lazy creation, meaning
 * that the actual {@link IdentityHashMap} will not be created until needed
 * (e.g. when putting an element).
 * 
 * Besides the lazy creation, this class behaves exactly as
 * {@link IdentityHashMap} except that {@link Serializable} and
 * {@link Cloneable} are not supported by this class. In case that kind of
 * behavior is required, it is possible to extract the backing {@link Map} using
 * the {@link #getLazilyCreatedMap()} method.
 * 
 */
public final class LazyIdentityHashMap<K, V> extends AbstractLazyMap<K, V> {

	private LazyIdentityHashMap() {
		// Nothing
	}

	/**
	 * Creates a new {@link LazyIdentityHashMap} instance.
	 * 
	 * @return a new {@link LazyIdentityHashMap}.
	 */
	public static <K, V> Map<K, V> newLazyIdentityHashMap() {
		return new LazyIdentityHashMap<K, V>();
	}

	@Override
	Map<K, V> createMap() {
		return new IdentityHashMap<>();
	}
}
