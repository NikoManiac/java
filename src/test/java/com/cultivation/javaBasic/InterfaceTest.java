package com.cultivation.javaBasic;

import com.cultivation.javaBasic.showYourIntelligence.NameImpl;
import com.cultivation.javaBasic.util.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InterfaceTest {

    @Test
    void should_support_default_method() {
        // interface class's default what meaning
        InterfaceWithDefaultMethodImpl instance = new InterfaceWithDefaultMethodImpl();

        // TODO: please modify the following code to pass the test
        // <--start
        final String expected = "The truth of the universe is 42";
        // --end-->

        assertEquals(expected, instance.tellMeTheTruthOfTheUniverse());
    }

    @Test
    void should_choose_override_method() {
        InterfaceWithOverrideDefaultImpl instance = new InterfaceWithOverrideDefaultImpl();

        // TODO: please modify the following code to pass the test
        // <--start
        final String expected = "The truth of the universe is Anime";
        // --end-->

        assertEquals(expected, instance.tellMeTheTruthOfTheUniverse());
    }

    @Test
    void should_choose_override_method_continued() {
        InterfaceExtendsInterfaceWithDefaultMethod instance = new InterfaceExtendsInterfaceWithDefaultMethodImpl();

        // TODO: please modify the following code to pass the test
        // <--start
        final String expected = "The truth of the universe is Game";
        // --end-->

        assertEquals(expected, instance.tellMeTheTruthOfTheUniverse());
    }

    @Test
    void should_resolve_ambiguity_by_yourself() {
        // ambiguity: not clear
        NameImpl instance = new NameImpl();
        // its will wrong when not override any interface
        String name = instance.getName();

        assertEquals("Person", name);
    }

    @Test
    void should_clone_an_object_without_a_default_constructor() throws CloneNotSupportedException {
        CloneTest cloneTest = new CloneTest(1, "test", new Object());

        CloneTest afterCloneClass = cloneTest.clone();
        assertTrue(afterCloneClass != cloneTest);
        assertTrue(afterCloneClass.getClass() == cloneTest.getClass());
        assertFalse(afterCloneClass.equals(cloneTest));

        assertEquals(afterCloneClass.test, cloneTest.test);
        assertEquals(afterCloneClass.object, cloneTest.object);
    }
}

/*
 * - Can you clone an object without a default constructor?
 * Public Class A implements Cloneable {
 *      @Override
 *      public Object clone () {
 *          return new A();
 *      }
 * }
 *
 */
