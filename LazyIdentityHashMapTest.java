package gakesson.util.collections;

import java.util.IdentityHashMap;
import java.util.Map;

import org.testng.annotations.Test;

@Test
public class LazyIdentityHashMapTest extends AbstractLazyMapTest {

	@Override
	protected Map<Object, Object> createLazyMap() {
		return LazyIdentityHashMap.newLazyIdentityHashMap();
	}

	@Override
	protected Map<Object, Object> createBackingMap() {
		return new IdentityHashMap<>();
	}
}