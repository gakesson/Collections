package gakesson.util.collections;

import java.util.LinkedHashMap;
import java.util.Map;

import org.testng.annotations.Test;

@Test
public class LazyLinkedHashMapTest extends AbstractLazyMapTest {

	@Override
	protected Map<Object, Object> createLazyMap() {
		return LazyLinkedHashMap.newLazyLinkedHashMap();
	}

	@Override
	protected Map<Object, Object> createBackingMap() {
		return new LinkedHashMap<>();
	}
}
