package com.collinsrj;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntConsumer;

/**
 * Created by collinsrj on 10/05/2017.
 */
public class FlatMapTest {

    /**
     *
     * @param objects
     * @return
     * @throws StackOverflowError if nesting is too deep
     */
    public void flatten(final List<Object> result, final Object[] objects) {
        for (Object o : objects) {
            if (!(o  instanceof Array)) {
                result.add(o);

            } else {
                flatten(result, (Object[]) o);
            }
        }
    }

    @Test
    public void testNoNesting() throws Exception {
        Object[] input = new Object[]{1, 2, 3};
    }

}
