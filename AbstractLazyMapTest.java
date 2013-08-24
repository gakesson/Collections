package gakesson.util.collections;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Map;

import org.testng.annotations.Test;

public abstract class AbstractLazyMapTest {

	@Test
	public void shouldReturnSizeOfLazyMap() {
		Map<Object, Object> lazyMap = createLazyMap();

		assertThat(lazyMap).hasSize(0);

		lazyMap.put(new Integer(3211), new Integer(3211));

		assertThat(lazyMap).hasSize(1);
	}

	@Test
	public void shouldReturnIsEmptyOfLazyMap() {
		Map<Object, Object> lazyMap = createLazyMap();

		assertThat(lazyMap).isEmpty();

		lazyMap.put(new Integer(3211), new Integer(3211));

		assertThat(lazyMap).isNotEmpty();
	}

	@Test
	public void shouldContainKeyInLazyMap() {
		Map<Object, Object> lazyMap = createLazyMap();
		Object object = new Integer(3211);

		assertThat(lazyMap.containsKey(object)).isFalse();

		lazyMap.put(object, object);

		assertThat(lazyMap.containsKey(object)).isTrue();
	}

	@Test
	public void shouldContainValueInLazyMap() {
		Map<Object, Object> lazyMap = createLazyMap();
		Object object = new Integer(3211);

		assertThat(lazyMap.containsValue(object)).isFalse();

		lazyMap.put(object, object);

		assertThat(lazyMap.containsValue(object)).isTrue();
	}

	@Test
	public void shouldGetValueInLazyMap() {
		Map<Object, Object> lazyMap = createLazyMap();
		Object object = new Integer(3211);

		assertThat(lazyMap.get(object)).isNull();

		lazyMap.put(object, object);

		assertThat(lazyMap.get(object)).isNotNull();
	}

	@Test
	public void shouldRemoveValueInLazyMap() {
		Map<Object, Object> lazyMap = createLazyMap();
		Object object = new Integer(3211);

		assertThat(lazyMap.remove(object)).isNull();

		lazyMap.put(object, object);

		assertThat(lazyMap.remove(object)).isNotNull();
	}

	@Test
	public void shouldClearValuesInLazyMap() {
		Map<Object, Object> lazyMap = createLazyMap();
		Object object = new Integer(3211);

		lazyMap.clear();
		assertThat(lazyMap).isEmpty();

		lazyMap.put(object, object);

		assertThat(lazyMap).isNotEmpty();
		lazyMap.clear();
		assertThat(lazyMap).isEmpty();
	}

	@Test
	public void shouldReturnKeySetInLazyMap() {
		Map<Object, Object> lazyMap = createLazyMap();
		Object object = new Integer(3211);

		assertThat(lazyMap.keySet()).isEmpty();

		lazyMap.put(object, object);

		assertThat(lazyMap.keySet()).isNotEmpty();
	}

	@Test
	public void shouldReturnValuesInLazyMap() {
		Map<Object, Object> lazyMap = createLazyMap();
		Object object = new Integer(3211);

		assertThat(lazyMap.values()).isEmpty();

		lazyMap.put(object, object);

		assertThat(lazyMap.values()).isNotEmpty();
	}

	@Test
	public void shouldReturnEntrySetInLazyMap() {
		Map<Object, Object> lazyMap = createLazyMap();
		Object object = new Integer(3211);

		assertThat(lazyMap.entrySet()).isEmpty();

		lazyMap.put(object, object);

		assertThat(lazyMap.entrySet()).isNotEmpty();
	}

	@Test
	public void shouldReturnHashCodeFromLazyMap() {
		Map<Object, Object> lazyMap = createLazyMap();

		assertThat(lazyMap.hashCode()).isEqualTo(createBackingMap().hashCode());

		lazyMap.put(new Integer(32), new Integer(3211));

		assertThat(lazyMap.hashCode()).isNotEqualTo(
				createBackingMap().hashCode());
	}

	@Test
	public void shouldReturnEqualsFromLazyMap() {
		Map<Object, Object> lazyMap = createLazyMap();
		Object object = new Integer(3211);

		assertThat(lazyMap).isEqualTo(createBackingMap());

		lazyMap.put(object, object);

		assertThat(lazyMap).isNotEqualTo(createBackingMap());
	}

	@Test
	public void shouldPutElement() {
		Map<Object, Object> lazyMap = createLazyMap();

		Object object = new Integer(3211);
		lazyMap.put(object, object);

		assertThat(lazyMap.get(object)).isNotNull();
	}

	@Test
	public void shouldPutAllElements() {
		Map<Object, Object> lazyMap = createLazyMap();
		Map<Object, Object> map = createBackingMap();
		Object object = new Integer(3211);
		map.put(object, object);

		lazyMap.putAll(map);

		assertThat(lazyMap.get(object)).isNotNull();
	}

	protected abstract Map<Object, Object> createLazyMap();

	protected abstract Map<Object, Object> createBackingMap();
}
