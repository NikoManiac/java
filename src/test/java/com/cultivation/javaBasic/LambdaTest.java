package com.cultivation.javaBasic;

import com.cultivation.javaBasic.util.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LambdaTest {
    @Test
    void should_apply_to_interface_with_single_abstract_method() {
        StringFunc lambda = () -> "Hello";

        // TODO: please modify the following code to pass the test
        // <--start
        final String expect = "Hello";
        // --end-->

        assertEquals(expect, lambda.getString());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_bind_to_instance_method() {
        // TODO: please bind lambda to instanceMethod.
        // <--start
//        StringFunc lambda = this::instanceMethod;
        StringFunc lambda = new StringFunc() {
            @Override
            public String getString() {
                return LambdaTest.this.instanceMethod();
            }
        };
        // --end-->

        assertEquals("instanceMethod", lambda.getString());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_bind_to_static_method() {
        // TODO: please bind lambda to staticMethod
        // <--start
//        StringFunc lambda = LambdaTest::staticMethod;
        StringFunc lambda = () -> {
            return staticMethod();
        };
        // --end-->

        assertEquals("staticMethod", lambda.getString());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_bind_to_constructor() {
        // TODO: please bind lambda to constructor of ArrayList<Integer>
        // <--start
        GenericFunc<ArrayList<Integer>> lambda = () -> new ArrayList<>();
        // --end-->

        ArrayList<Integer> value = lambda.getValue();

        assertEquals(0, value.size());
    }

    @Test
    void should_capture_variable_in_a_closure() {
        // capture 捕获
        // closure 关闭 终止 结束
        int captured = 5;

        StringFunc lambda = () -> captured + " has been captured.";

        final String message = lambda.getString();

        // TODO: please modify the following code to pass the test
        // <--start
        final String expected = "5 has been captured.";
        // --end-->

        assertEquals(expected, message);
    }

    @Test
    void should_evaluate_captured_variable_when_executing() {
        // evaluate 评价
        // executing 执行
        ValueHolder<String> value = new ValueHolder<>();
        value.setValue("I am the King of the world!");

        StringFunc lambda = () -> "The length of captured value is: " + value.getValue().length();

        // TODO: please write down the expected string directly.
        // <--start
        final String expected = "The length of captured value is: 4";
        // --end-->
        // 执行时间?
        value.setValue("Blah");
        assertEquals(expected, lambda.getString());
    }

    @Test
    void should_extend_variable_scope() {
        // 闭包？？？
        StringFunc stringFunc = returnLambda();
        String message = stringFunc.getString();

        // TODO: please write down the expected string directly.
        // <--start
        final String expected = "In the year 2019";
        // --end-->

        assertEquals(expected, message);
    }

    @Test
    void should_capture_this_variable() {
        ThisInClosure instance = new ThisInClosure();
        StringFunc stringFunc = instance.getLambda();

        // TODO: please modify the following code to pass the test
        // <--start
        final String expected = "ThisInClosure";
        // --end-->

        assertEquals(expected, stringFunc.getString());
    }

    @Test
    void should_not_assign_lambda_to_object() {
//        Object object = () -> "ff";
//        Object object1 = () -> new Object();
//        int object2 = () -> 1;
    }

    @Test
    void should_return_int_by_lambda() {
        IntSuppliers lambda = () -> 1;

        int expectedInt = 1;
        assertEquals(expectedInt, lambda.getAsInt());
    }

    @Test
    void should_return_char_by_lambda() {
        CharSupplier lambda = () -> 'a';

        char expectedChar = 'a';
        assertEquals(expectedChar, lambda.getAsChar());
    }

    @Test
    void should_return_int_by_parameter_lambda() {
        IntFunction intFunction = (intNumber) -> intNumber;

        int expectedInt = 1;
        assertEquals(expectedInt, intFunction.apply(1));
    }

    @Test
    void should_return_int_by_two_parameter_lambda() {

        int expectedInt = 3;
        IntBiFuncition intBiFunction = (a, b) -> a + b;
        assertEquals(expectedInt, intBiFunction.apply(1, 2));
    }

    @Test
    void should_test_array_lambda() {
        int[] testArray3Item = new int[]{1, 2, 3};
        int[] testArray1Item = new int[]{1};
        int[] testArray2Item = new int[]{1, 2};
        Consumer consumer = (array) -> {
            if (array.length < 2) return;
            int temp = array[0];
            array[0] = array[1];
            array[1] = temp;
        };
        consumer.accept(testArray1Item);
        consumer.accept(testArray2Item);
        consumer.accept(testArray3Item);
        assertArrayEquals(new int[]{1}, testArray1Item);
        assertArrayEquals(new int[]{2, 1}, testArray2Item);
        assertArrayEquals(new int[]{2, 1, 3}, testArray3Item);
    }

    @Test
    void should_get_summer_value_by_lambda() {

        NumberFunction sumFunction = (array) -> {
            if (array == null) {
                return 0;
            }

            return Arrays.stream(array).sum();
       };

        assertEquals(0, sumFunction.summer(null));
        assertEquals(2, sumFunction.summer(2));
        assertEquals(6, sumFunction.summer(1, 2, 3));
        assertEquals(10, sumFunction.summer(1, 2, 3, 4));
    }

    private static StringFunc returnLambda() {
        int year = 2019;
        return () -> "In the year " + year;
    }

    @SuppressWarnings("unused")
    private static String staticMethod() {
        return "staticMethod";
    }

    @SuppressWarnings("unused")
    private String instanceMethod() {
        return "instanceMethod";
    }
}

/*
 * - Do you think you can assign a lambda expression to an Object instance?
 */
