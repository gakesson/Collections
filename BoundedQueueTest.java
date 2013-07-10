package gakesson.util.collections;

import static org.fest.assertions.Assertions.assertThat;
import static org.testng.Assert.fail;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.testng.annotations.Test;

public class BoundedQueueTest
{
    private static final int DEFAULT_QUEUE_CAPACITY = 100;

    @Test
    public void shouldReturnQueueCapacity()
    {
        int capacity = 200;
        BoundedQueue<Element> queue = (BoundedQueue<Element>) createBoundedQueue(capacity);

        assertThat(queue.getCapacity()).isEqualTo(capacity);
    }

    @Test
    public void shouldBeEmptyQueue()
    {
        Queue<Element> queue = createDefaultBoundedQueue();

        assertThat(queue).isEmpty();
    }

    @Test
    public void shouldNotBeEmptyQueue()
    {
        Queue<Element> queue = createDefaultBoundedQueue();
        queue.offer(new Element());

        assertThat(queue).isNotEmpty();
    }

    @Test
    public void shouldReturnZeroSizeWhenQueueIsEmpty()
    {
        Queue<Element> queue = createDefaultBoundedQueue();

        assertThat(queue).hasSize(0);
    }

    @Test
    public void shouldReturnCorrectSizeOfQueue()
    {
        Queue<Element> queue = createDefaultBoundedQueue();
        queue.offer(new Element());

        assertThat(queue).hasSize(1);
    }

    @Test
    public void shouldOfferOneElement()
    {
        Queue<Element> queue = createDefaultBoundedQueue();
        Element element = new Element();

        queue.offer(element);

        assertThat(queue.poll()).isSameAs(element);
    }

    @Test
    public void shouldOfferMaximumCapacityNumberOfElements()
    {
        int capacity = 200;
        Queue<Element> queue = createBoundedQueue(capacity);
        verifyAndOfferElements(queue, capacity);

        assertThat(queue).hasSize(capacity);
    }

    @Test
    public void shouldNotOfferMoreThanCapacityNumberOfElements()
    {
        int capacity = 200;
        Queue<Element> queue = createBoundedQueue(capacity);
        verifyAndOfferElements(queue, capacity);

        assertThat(queue).hasSize(capacity);

        boolean inserted = queue.offer(new Element());

        assertThat(inserted).isFalse();
        assertThat(queue).hasSize(capacity);
    }

    @Test
    public void shouldPollNullWhenEmptyQueue()
    {
        Queue<Element> queue = createDefaultBoundedQueue();

        Element polledElement = queue.poll();

        assertThat(polledElement).isNull();
    }

    @Test
    public void shouldPollAllElementsFromQueue()
    {
        int capacity = 200;
        Queue<Element> queue = createBoundedQueue(capacity);
        verifyAndOfferElements(queue, capacity);
        int pollCounter = 0;

        while (!queue.isEmpty())
        {
            Element polledElement = queue.poll();
            pollCounter++;

            assertThat(polledElement).isNotNull();
        }

        assertThat(pollCounter).isEqualTo(capacity);
    }

    @Test
    public void shouldPollElementsInFIFO()
    {
        int capacity = 200;
        Queue<Element> queue = createBoundedQueue(capacity);
        List<Element> insertedElements = verifyAndOfferElements(queue, capacity);
        int pollCounter = 0;

        while (!queue.isEmpty())
        {
            Element polledElement = queue.poll();
            pollCounter++;

            assertThat(polledElement).isNotNull();
            assertThat(polledElement).isSameAs(insertedElements.get(pollCounter - 1));
        }

        assertThat(pollCounter).isEqualTo(capacity);
    }

    @Test
    public void shouldAddAll()
    {
        int capacity = 200;
        Queue<Element> queue = createBoundedQueue(capacity);
        Queue<Element> anotherQueue = createBoundedQueue(capacity);
        verifyAndOfferElements(anotherQueue, capacity);

        queue.addAll(anotherQueue);

        assertThat(queue).hasSize(anotherQueue.size());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldNotAllowToAddAllWhenThisAndProvidedCollectionAreSame()
    {
        Queue<Element> queue = createDefaultBoundedQueue();

        queue.addAll(queue);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldNotAllowToAddAllWhenBackingQueueAndProvidedCollectionAreSame()
    {
        Queue<Element> backingQueue = new LinkedList<Element>();
        Queue<Element> queue = new BoundedQueue<Element>(backingQueue, DEFAULT_QUEUE_CAPACITY);

        queue.addAll(backingQueue);
    }

    @Test
    public void shouldNotAddAllWhenCapacityIsNotEnoughForElementsFromProvidedCollection()
    {
        int capacity = 200;
        Queue<Element> queue = createBoundedQueue(capacity);
        queue.add(new Element());
        Queue<Element> anotherQueue = createBoundedQueue(capacity);
        verifyAndOfferElements(anotherQueue, capacity);

        try
        {
            queue.addAll(anotherQueue);
            fail("Should not have allowed to add elements");
        } 
        catch (IllegalStateException e)
        {
            // Expected
        }

        assertThat(queue).hasSize(1);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenProvidingNullCollectionWhenAddingAll()
    {
        Queue<Element> queue = createDefaultBoundedQueue();
        queue.addAll(null);
    }

    @Test
    public void shouldReturnTrueWhenQueueIsModifiedAfterAddingAll()
    {
        int capacity = 200;
        Queue<Element> queue = createBoundedQueue(capacity);
        Queue<Element> anotherQueue = createBoundedQueue(capacity);
        anotherQueue.offer(new Element());

        boolean modified = queue.addAll(anotherQueue);

        assertThat(modified).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenQueueWasNotModifiedAfterAddingAll()
    {
        int capacity = 200;
        Queue<Element> queue = createBoundedQueue(capacity);
        Queue<Element> anotherQueue = createBoundedQueue(capacity);

        boolean modified = queue.addAll(anotherQueue);

        assertThat(modified).isFalse();
    }

    @Test
    public void shouldPeekHeadOfQueue()
    {
        int capacity = 200;
        Queue<Element> queue = createBoundedQueue(capacity);
        List<Element> insertedElements = verifyAndOfferElements(queue, capacity);
        int peekCounter = 0;

        while (!queue.isEmpty())
        {
            Element polledElement = queue.peek();
            queue.poll();
            peekCounter++;

            assertThat(polledElement).isNotNull();
            assertThat(polledElement).isSameAs(insertedElements.get(peekCounter - 1));
        }

        assertThat(peekCounter).isEqualTo(capacity);
    }

    @Test
    public void shouldReturnIteratorInFIFO()
    {
        int capacity = 200;
        Queue<Element> queue = createBoundedQueue(capacity);
        List<Element> insertedElements = verifyAndOfferElements(queue, capacity);
        Iterator<Element> queueElements = queue.iterator();
        int iterationsCounter = 0;

        while (queueElements.hasNext())
        {
            Element polledElement = queueElements.next();
            iterationsCounter++;

            assertThat(polledElement).isNotNull();
            assertThat(polledElement).isSameAs(insertedElements.get(iterationsCounter - 1));
        }

        assertThat(iterationsCounter).isEqualTo(capacity);
    }

    /**
     * Adds the specified number of elements to the provided queue. This method
     * will also fail in case a single element failed to be inserted into the
     * queue.
     * 
     * @param queue
     * @param numberOfElements
     * @return
     */
    private List<Element> verifyAndOfferElements(Queue<Element> queue, int numberOfElements)
    {
        List<Element> createdElements = new ArrayList<>(numberOfElements);

        for (int i = 0; i < numberOfElements; ++i)
        {
            Element element = new Element();
            boolean inserted = queue.offer(element);

            if (!inserted)
            {
                fail("Should have inserted element");
            }

            createdElements.add(element);
        }

        return createdElements;
    }

    /**
     * Creates a {@link BoundedQueue} with the {@link #DEFAULT_QUEUE_CAPACITY}.
     * 
     * @return
     */
    private Queue<Element> createDefaultBoundedQueue()
    {
        return createBoundedQueue(DEFAULT_QUEUE_CAPACITY);
    }

    /**
     * Creates a {@link BoundedQueue} qith the specified capacity
     * 
     * @param capacity
     * @return
     */
    private Queue<Element> createBoundedQueue(int capacity)
    {
        return new BoundedQueue<Element>(new LinkedList<Element>(), capacity);
    }

    /**
     * A type to be stored in the {@link BoundedQueue}.
     * 
     */
    private static class Element
    {
        // Nothing
    }
}
