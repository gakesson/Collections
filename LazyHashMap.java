package gakesson.util.collections;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * This class decorates a {@link HashMap} with lazy creation, meaning that the
 * actual {@link HashMap} will not be created until needed (e.g. when putting an
 * element).
 * 
 * Besides the lazy creation, this class behaves exactly as {@link HashMap}
 * except that {@link Serializable} and {@link Cloneable} are not supported by
 * this class. In case that kind of behavior is required, it is possible to
 * extract the backing {@link Map} using the {@link #getLazilyCreatedMap()}
 * method.
 * 
 */
public final class LazyHashMap<K, V> extends AbstractLazyMap<K, V> {

	private LazyHashMap() {
		// Nothing
	}

	/**
	 * Creates a new {@link LazyHashMap} instance.
	 * 
	 * @return a new {@link LazyHashMap}.
	 */
	public static <K, V> Map<K, V> newLazyHashMap() {
		return new LazyHashMap<K, V>();
	}

	@Override
	Map<K, V> createMap() {
		return new HashMap<>();
	}
}
