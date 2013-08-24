package gakesson.util.collections;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

@Test
public class LazyHashMapTest extends AbstractLazyMapTest {

	@Override
	protected Map<Object, Object> createLazyMap() {
		return LazyHashMap.newLazyHashMap();
	}

	@Override
	protected Map<Object, Object> createBackingMap() {
		return new HashMap<>();
	}
}