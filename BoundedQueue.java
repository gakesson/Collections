package gakesson.util.collections;

import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

/**
 * This is an implementation which decorates a {@link Queue} with a bounded limit. This is useful when behavior 
 * to restrict excessive queue expansion is needed. Taking the bounded limit decoration aside, this implementation's 
 * behavior is completely dictated by the provided {@link Queue} implementation.
 * 
 * This class is not thread-safe.
 * 
 * @author Gustav Akesson - gustav.r.akesson@gmail.com
 * @param <E>
 *            The type of elements held in this collection
 */
public class BoundedQueue<E> extends AbstractQueue<E> 
{
	private final Queue<E> myBackingQueue;
	private final int myCapacity;
	
	/**
	 * Creates a new {@link Queue} instance using the provided backing queue and maximum capacity.
	 * 
	 * @param backingQueue
	 *            The queue to decorate.
	 * @param capacity
	 *            The maximum capacity.
	 */
	public BoundedQueue(Queue<E> backingQueue, int capacity)
	{
		myBackingQueue = backingQueue;
		myCapacity = capacity;
	}
	
	/**
	 * Returns the maximum capacity of this {@link BoundedQueue}.
	 * 
	 * @return The maximum capacity of this queue.
	 */
	public int getCapacity()
	{
		return myCapacity;
	}

    /**
     * Inserts the specified element into this queue if it is possible to do so immediately without violating capacity 
     * restrictions of this queue or the backing queue.
     *
	 * @param e
	 *            The element to add.
     * @return {@code true} if the element was added to the backing queue, else {@code false}.
     * @throws {@link NullPointerException} if the specified element is null and the backing queue does not permit null values.
     * @throws {@link IllegalArgumentException} if some property of this element prevents it from being added to this queue.
     */
	@Override
	public boolean offer(E e) 
	{
		boolean inserted = false;
		
		if (size() < myCapacity)
		{
			inserted = myBackingQueue.offer(e);
		}
		
		return inserted;
	}
	
    /**
     * Adds all of the elements in the specified collection to the backing queue.  Attempts to addAll of a queue to itself result 
     * in {@link IllegalArgumentException}. Further, the behavior of this operation is undefined if the specified collection 
     * is modified while the operation is in progress.
     *
     * This implementation iterates over the specified collection, and adds each element returned by the iterator to the backing
     * queue, in turn.  A runtime exception encountered while trying to add an element (including, in particular, a {@code null} 
     * element) may result in only some of the elements having been successfully added when the associated exception is thrown.
     *
     * In case the provided collection's size plus the current size of the backing queue exceeds the {@link BoundedQueue}'s capacity,
     * an {@link IllegalStateException} will be thrown. At that point, no elements have been inserted in the backing queue.
     *
     * @param collectionToAdd 
     *            The collection containing elements to be added to this queue.
     * @return {@code true} if this queue changed as a result of the call.
     * @throws {@link NullPointerException} If the specified collection contains a null element and the backing queue does not permit 
     *         null elements, or if the specified collection is null.
     * @throws {@link IllegalArgumentException} If some property of an element of the specified collection prevents it from being added to 
     * 		   this queue, or if the specified collection is this queue.
     * @throws {@link IllegalStateException} If not all or any of the elements can be added at this time due to insertion restrictions.
     * @see #add(Object)
     */
	@Override
	public boolean addAll(Collection<? extends E> collectionToAdd)
	{
		checkNotNull(collectionToAdd);
		checkNotSame(this, collectionToAdd);
		checkNotSame(myBackingQueue, collectionToAdd);
		boolean modified = false;
		
		if (size() + collectionToAdd.size() > myCapacity)
		{
			throw new IllegalStateException();
		}
		
        for (E e : collectionToAdd)
        {
        	if (add(e))
            {
            	modified = true;
            }
        }
                
        return modified;
	}

    /**
     * Retrieves and removes the head of the backing queue, or returns {@code null} if the queue is empty.
     *
     * @return The head of the queue, or {@code null} if the backing queue is empty.
     */
	@Override
	public E poll() 
	{
		return myBackingQueue.poll();
	}

    /**
     * Retrieves, but does not remove, the head of the backing queue, or returns {@code null} if the queue is empty.
     *
     * @return The head of this queue, or <tt>null</tt> if the backing queue is empty.
     */
	@Override
	public E peek() 
	{
		return myBackingQueue.peek();
	}

    /**
     * Returns an iterator over the elements contained in the backing queue.
     *
     * @return An iterator over the elements contained in this collection.
     */
	@Override
	public Iterator<E> iterator() 
	{
		return myBackingQueue.iterator();
	}

	/**
	 * Returns the size of the backing queue.
	 * 
	 * @return The size of the backing queue.
	 */
	@Override
	public int size() 
	{
		return myBackingQueue.size();
	}

    /**
     * Verifies that the provided object is not null, and if it is a {@link NullPointerException} is thrown.
     * 
     * @param object
     */
    private static void checkNotNull(Object object)
    {
        if (object == null)
        {
            throw new NullPointerException();
        }
    }

    /**
     * Verifies that the two references don't refer to the very same object, and if they do an {@link IllegalArgumentException} is thrown.
     * 
     * @param object
     */
    private static void checkNotSame(Object first, Object second)
    {
        if (first == second)
        {
            throw new IllegalArgumentException("Not allowed due to same object");
        }
    }
}
