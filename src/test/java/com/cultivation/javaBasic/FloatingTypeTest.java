package com.cultivation.javaBasic;

import org.junit.jupiter.api.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static org.junit.jupiter.api.Assertions.*;

class FloatingTypeTest {
    @Test
    void should_not_get_rounded_result_if_convert_floating_number_to_integer() {
        final float floatingPointNumber = 2.75f;
        final int integer = (int)floatingPointNumber;

        // TODO: Please change the result to pass the test.
        // <!--start
        final int expected = 2;
        // --end-->

        assertEquals(expected, integer);
    }

    @Test
    void should_return_convert_data_type() {
        byte byteNumber = 1;
        short shortNumber = 1;
        int intNumber = 1;
        long longNumber = 1L;
        float floatNumber = 1F;
        double doubleNumber = 1D;

//        byteNumber = intNumber;
//        byteNumber = shortNumber;
//        byteNumber = longNumber;
//        byteNumber = floatNumber;
//        byteNumber = doubleNumber;

        shortNumber = byteNumber;
//        shortNumber = intNumber;
//        shortNumber = longNumber;
//        shortNumber = floatNumber;
//        floatNumber = doubleNumber;

        intNumber = byteNumber;
        intNumber = shortNumber;
//        intNumber = longNumber;
//        intNumber = floatNumber;
//        intNumber = doubleNumber;

        longNumber = byteNumber;
        longNumber = shortNumber;
        longNumber = intNumber;
//        longNumber = floatNumber;
//        longNumber = doubleNumber;

        floatNumber = byteNumber;
        floatNumber = shortNumber;
        floatNumber = intNumber;
        floatNumber = longNumber;
//        floatNumber = doubleNumber;

        doubleNumber = byteNumber;
        doubleNumber = shortNumber;
        doubleNumber = intNumber;
        doubleNumber = longNumber;
        doubleNumber = floatNumber;





//        longNumber = shortNumber
// ;
//        shortNumber = longNumber;

//        short doubleToShortNumber = (short) doubleNumber;
//        int doubleToIntNumber = (int) doubleNumber;
//        int longToIntNumber = (int) longNumber;
//        long intToLongNumber = intNumber;
//        long doubleToLongNumber = (long) doubleNumber;
//        int byteToIntNumber = byteNumber;
//        float byteToFloatNumber = byteNumber;
//        long floatToFloatNumber = (long)floatNumber;
//        byte shortToByte = (byte) shortNumber;
    }

    @SuppressWarnings({"divzero", "NumericOverflow"})
    @Test
    void should_judge_special_double_cases() {
        assertTrue(isInfinity(1d / 0d));
        assertTrue(isInfinity(-1d / 0d));
        assertFalse(isInfinity(2d));
        assertFalse(isInfinity(Double.NaN));

        assertTrue(isNan(0d / 0d));
        assertFalse(isNan(Double.NEGATIVE_INFINITY));
        assertFalse(isNan(Double.POSITIVE_INFINITY));
    }

    @Test
    void should_not_round_number_when_convert_to_integer() {
        final float floatingPointNumber = 2.75f;
        final int integer = (int)floatingPointNumber;

        // TODO: Please change the result to pass the test.
        // <!--start
        final int expected = 2;
        // --end-->

        assertEquals(expected, integer);
    }

    @SuppressWarnings("unused")
    @Test
    void should_round_number() {
        final double floatingPointNumber = 2.75;
        // TODO: Please call some method to round the floating point number.
        // <!--start
        final long rounded = Math.round(floatingPointNumber);
        // --end-->

        assertEquals(3L, rounded);
    }

    @SuppressWarnings("unused")
    private boolean isNan(double realNumber) {
        // TODO: please implement the method to pass the test.
        return Double.isNaN(realNumber);
//        throw new NotImplementedException();
    }

    @SuppressWarnings("unused")
    private boolean isInfinity(double realNumber) {
        // TODO: please implement the method to pass the test.
        return Double.isInfinite(realNumber);
//        throw new NotImplementedException();
    }
    /*
     * The coach should ask the following questions for the correspond test method:
     *
     * - Can we compare NaN using == directly?
     * - Can we compare XXX_INFINITY using == directly?
     */
}
