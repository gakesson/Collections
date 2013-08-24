package gakesson.util.collections;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class decorates a {@link TreeMap} with lazy creation, meaning that the
 * actual {@link TreeMap} will not be created until needed (e.g. when putting an
 * element).
 * 
 * Besides the lazy creation, this class behaves exactly as {@link TreeMap},
 * using the natural orderings of the keys.
 * 
 * Note that {@link Serializable} and {@link Cloneable} are not supported by
 * this class. In case that kind of behavior is required, it is possible to
 * extract the backing {@link Map} using the {@link #getLazilyCreatedMap()}
 * method.
 * 
 */
public final class LazyTreeMap<K, V> extends AbstractLazyMap<K, V> {

	private LazyTreeMap() {
		// Nothing
	}

	/**
	 * Creates a new {@link LazyTreeMap} instance.
	 * 
	 * @return a new {@link LazyTreeMap}.
	 */
	public static <K, V> Map<K, V> newLazyTreeMap() {
		return new LazyTreeMap<K, V>();
	}

	@Override
	Map<K, V> createMap() {
		return new TreeMap<>();
	}
}
