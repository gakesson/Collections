package gakesson.util.collections;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This class decorates a {@link LinkedHashMap} with lazy creation, meaning that
 * the actual {@link LinkedHashMap} will not be created until needed (e.g. when
 * putting an element).
 * 
 * Besides the lazy creation, this class behaves exactly as
 * {@link LinkedHashMap} except that {@link Serializable} and {@link Cloneable}
 * are not supported by this class. In case that kind of behavior is required,
 * it is possible to extract the backing {@link Map} using the
 * {@link #getLazilyCreatedMap()} method.
 */
public final class LazyLinkedHashMap<K, V> extends AbstractLazyMap<K, V> {

	private LazyLinkedHashMap() {
		// Nothing
	}

	/**
	 * Creates a new {@link LazyHashMap} instance.
	 * 
	 * @return a new {@link LazyHashMap}.
	 */
	public static <K, V> Map<K, V> newLazyLinkedHashMap() {
		return new LazyLinkedHashMap<K, V>();
	}

	@Override
	Map<K, V> createMap() {
		return new LinkedHashMap<>();
	}
}
