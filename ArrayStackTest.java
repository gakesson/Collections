package gakesson.util.collections;

import static org.fest.assertions.Assertions.assertThat;
import gakesson.util.misc.Stack;

import org.testng.annotations.Test;

public class ArrayStackTest
{
    @Test
    public void shouldPushAndPopElementsInLIFO()
    {
        int numberOfElements = 100;
        Stack<Object> stack = new ArrayStack<Object>();
        java.util.Stack<Object> javaStack = new java.util.Stack<Object>();

        for (int i = 0; i < numberOfElements; ++i)
        {
            Object object = new Object();
            stack.push(object);
            javaStack.push(object);
        }

        for (int i = 0; i < numberOfElements; ++i)
        {
            Object object = stack.pop();
            Object javaObject = javaStack.pop();

            assertThat(object).isSameAs(javaObject);
        }
    }

    @Test
    public void shouldPeekHeadOfStack()
    {
        int numberOfElements = 100;
        Stack<Object> stack = new ArrayStack<Object>();
        java.util.Stack<Object> javaStack = new java.util.Stack<Object>();

        for (int i = 0; i < numberOfElements; ++i)
        {
            Object object = new Object();
            stack.push(object);
            javaStack.push(object);
        }

        for (int i = 0; i < numberOfElements / 2; ++i)
        {
            stack.pop();
            javaStack.pop();
        }

        Object object = stack.peek();
        Object javaObject = javaStack.peek();

        assertThat(object).isSameAs(javaObject);
        assertThat(stack.isEmpty()).isFalse();
    }

    @Test
    public void shouldBeEmptyStack()
    {
        Stack<Object> stack = new ArrayStack<Object>();

        assertThat(stack.isEmpty()).isTrue();
    }

    @Test
    public void shouldReturnCorrectSizeOfStack()
    {
        int numberOfElements = 100;
        Stack<Object> stack = new ArrayStack<Object>();
        java.util.Stack<Object> javaStack = new java.util.Stack<Object>();

        for (int i = 0; i < numberOfElements; ++i)
        {
            Object object = new Object();
            stack.push(object);
            javaStack.push(object);
        }

        assertThat(javaStack).hasSize(stack.size());

        for (int i = 0; i < numberOfElements; ++i)
        {
            stack.pop();
            javaStack.pop();
        }

        assertThat(javaStack).hasSize(0);
        assertThat(stack.size()).isZero();
    }
}
