package com.cultivation.javaBasicExtended.reverseString;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

class StringReverser {
    @SuppressWarnings({"WeakerAccess", "unused"})
    public static String[] reverse(String input) {
        // TODO: please implement the method to pass all the tests.
        // <--start
        if (input == null) {
            throw new IllegalArgumentException();
        }
        if (input.trim().equals("")) {
            return new String[0];
        }

        String[] inputSplitArrayBySpace = input.trim().split(" ");
        List<String> stringList = Arrays.asList(inputSplitArrayBySpace);
        Collections.reverse(stringList);

        return (String[]) stringList.toArray();
        // --end-->
    }
}
