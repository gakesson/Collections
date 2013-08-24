package gakesson.util.collections;

import gakesson.util.misc.Stack;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * The {@link ArrayStack} class represents a last-in-first-out (LIFO) stack of
 * objects. All the classic stack operations are provided. The implementation is
 * array-based and the capacity is unbounded.
 * 
 * This class is not thread-safe.
 * 
 */
public class ArrayStack<E> implements Stack<E>
{
    private static final int DEFAULT_INITIAL_SIZE = 10;
    private static final int MAX_STACK_SIZE = Integer.MAX_VALUE - 8;

    private E[] myElements;
    private int mySize;

    /**
     * Creates a new {@link ArrayStack} instance with the default initial size.
     * 
     */
    public ArrayStack()
    {
        this(DEFAULT_INITIAL_SIZE);
    }

    /**
     * Creates a new {@link ArrayStack} with the provided initial size.
     * 
     * @param initialSize
     */
    public ArrayStack(int initialSize)
    {
        myElements = (E[]) new Object[initialSize];
    }

    @Override
    public void push(E e)
    {
        ensureStackCapacity(mySize + 1);
        myElements[mySize++] = e;
    }

    @Override
    public E pop()
    {
        if (isEmpty())
        {
            throw new EmptyStackException();
        }

        E e = myElements[mySize - 1];
        myElements[--mySize] = null;
        return e;
    }

    @Override
    public E peek()
    {
        if (isEmpty())
        {
            throw new EmptyStackException();
        }

        return myElements[mySize - 1];
    }

    @Override
    public boolean isEmpty()
    {
        return size() == 0;
    }

    @Override
    public int size()
    {
        return mySize;
    }

    /**
     * Ensures that the capacity of the internal array is at least the provided
     * capacity.
     * 
     * @param requiredCapacity
     */
    private void ensureStackCapacity(int requiredCapacity)
    {
        if (requiredCapacity > myElements.length)
        {
            myElements = growStack(myElements, requiredCapacity);
        }
    }

    /**
     * Grows the provided array by at least the specified capacity. This method
     * is static and takes the stack state as arguments.
     * 
     * @param requiredCapacity
     */
    private static <E> E[] growStack(E[] stackElements, int requiredCapacity)
    {
        int oldCapacity = stackElements.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);

        if (newCapacity - requiredCapacity < 0)
        {
            newCapacity = requiredCapacity;
        }

        if (newCapacity - MAX_STACK_SIZE > 0)
        {
            newCapacity = hugeStackCapacity(requiredCapacity);
        }

        return Arrays.copyOf(stackElements, newCapacity);
    }

    /**
     * Returns the biggest possible capacity for a stack. This method is static
     * and takes the stack state as arguments.
     * 
     * @param requiredCapacity
     * @return
     */
    private static int hugeStackCapacity(int requiredCapacity)
    {
        if (requiredCapacity < 0)
        {
            throw new OutOfMemoryError();
        }

        return (requiredCapacity > MAX_STACK_SIZE) ? Integer.MAX_VALUE
                : MAX_STACK_SIZE;
    }
}
