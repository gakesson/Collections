package gakesson.util.collections;

import java.util.Map;
import java.util.TreeMap;

import org.testng.annotations.Test;

@Test
public class LazyTreeMapTest extends AbstractLazyMapTest {
	
	@Override
	protected Map<Object, Object> createLazyMap() {
		return LazyTreeMap.newLazyTreeMap();
	}

	@Override
	protected Map<Object, Object> createBackingMap() {
		return new TreeMap<>();
	}
}
