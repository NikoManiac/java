package com.cultivation.javaBasic;

import com.cultivation.javaBasic.showYourIntelligence.StackFrameHelper;
import com.cultivation.javaBasic.util.*;
import com.cultivation.javaBasic.showYourIntelligence.StringFormatException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ExceptionTest {
    @Test
    void should_customize_exception() {
        String expectedErrorMessage = "message error";
        Object expectedCauseNull = null;

        try {
            throw new StringFormatException("message error");
        } catch (StringFormatException e) {
            assertEquals(expectedErrorMessage, e.getMessage());
            assertEquals(expectedCauseNull, e.getCause());
        }
    }

    @Test
    void should_customize_exception_continued() {
        Exception innerError = new Exception("inner error");

        try {
            throw new StringFormatException("the message", innerError);
        } catch (StringFormatException error) {
            assertEquals("the message", error.getMessage());
            assertEquals(innerError, error.getCause());
        }
    }

    @Test
    void should_be_careful_of_the_order_of_finally_block() {
        int confusedResult = confuse(2);

        // TODO: please modify the following code to pass the test
        // <--start
        final int expectedResult = 0;
        // --end-->

        assertEquals(expectedResult, confusedResult);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_use_the_try_pattern() {
        ClosableStateReference closableStateReference = new ClosableStateReference();

        try (MyClosableType closable = new MyClosableType(closableStateReference)) {
            assertFalse(closable.isClosed());
            throw new RuntimeException();
        } catch (RuntimeException e) {
            assertEquals(true, closableStateReference.isClosed());
        }
        // TODO: please modify the following code to pass the test
        // <--start
        final Optional<Boolean> expected = Optional.of(true);
        // --end-->

        assertEquals(expected.get(), closableStateReference.isClosed());
    }

    @SuppressWarnings({"EmptyTryBlock", "unused"})
    @Test
    void should_call_closing_even_if_exception_throws() throws Exception {
        ArrayList<String> logger = new ArrayList<>();
        try (AutoCloseable withoutThrow = new ClosableWithoutException(logger);
             AutoCloseable withThrow = new ClosableWithException(logger)) {
        }

        try {
        } catch (Exception e) {
            // It is okay!
        }

        // TODO: please modify the following code to pass the test
        // <--start
        final String[] expected = {"ClosableWithException.close", "ClosableWithoutException.close"};
        // --end-->

        assertArrayEquals(
            expected,
            logger.toArray());
    }

    @Test
    void should_get_method_name_in_stack_frame() {
        String methodName = StackFrameHelper.getCurrentMethodName();

        assertEquals(
            "com.cultivation.javaBasic.ExceptionTest.should_get_method_name_in_stack_frame",
            methodName);
    }

    @Test
    void should_not_use_close_when_throw() {
        ArrayList<String> logger = new ArrayList<>();
        try {
            AutoCloseable withoutThrow = new ClosableWithoutException(logger);
            AutoCloseable withThrow = new ClosableWithException(logger);
        } finally {
            logger.add("finally");
        }
        String[] expected = {"finally"};
        assertArrayEquals(
                expected,
                logger.toArray());

    }

    @Test
    void should_return_a_method() {

    }
    @Test
    void aMethod() {
        bMethod();
        assertTrue(true);
    }

    @Test
    void should_override() {
        ExceptionOverTest exceptionOverTest = new ExceptionOverTest();
        ExceptionTestChild exceptionTestChild = new ExceptionTestChild();
        ExceptionOverRuntime exceptionOverRuntime = new ExceptionOverRuntime();
    }

    private void bMethod(){
        throw new Error();
    }

    private void cMethod() {
    }

    // test throws for throwable

    private void testSignature () {

    }

//    private void testSignature() throws Exception {
//
//    }

//    private void testSignature() throws RuntimeException {
//
//    }

    @SuppressWarnings({"ReturnInsideFinallyBlock", "SameParameterValue"})
    private int confuse(int value) {
        try {
            throw new RuntimeException();
        } catch (RuntimeException e){
            return 1;
        } finally {
            return 2;
        }
    }
}

/*
 * - Please draw the hibachi of `Throwable` and explain the main purpose for each type.
 * - When do you have to declare a exception in the method signature.
 * - When you declare a class A in package PA, and A contains a method
 *   `callMeToDeath() throw FileNotFoundException`. Package PB imports PA and uses
 *   `PA.A.callMeToDeath()`. Both PA and PB are built and deployed. Now PA is updated and
 *   the method `callMeToDeath()` throws another exception. Can we just build and replace
 *   PA?
 * - Can you declare a method throws unchecked exceptions?
 * - If a super class method throws no checked exception, could a derived class override its
 *   method and throw checked exceptions?
 * - Which constructor do you want to implement if you provide your own exception.
 */
