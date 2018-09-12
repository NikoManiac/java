package com.cultivation.javaBasic;

import com.cultivation.javaBasic.util.*;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InnerClassTest {
    @SuppressWarnings("ConstantConditions")
    @Test
    void should_access_instance_field_of_parent_class() {
        InnerClassUpdateField innerClassUpdateField = new InnerClassUpdateField(2018);
        innerClassUpdateField.new YearTest(2);

        assertEquals(2020, innerClassUpdateField.getYear());
    }

    @Test
    void should_access_add_by_inner_class() {
        InnerClassUpdateField innerClassUpdateField = new InnerClassUpdateField(2018);
        innerClassUpdateField.new YearTest(2).addYear();

        assertEquals(2020, innerClassUpdateField.getYear());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_refer_inner_class_from_outside() {
        InnerClassUpdateField innerClassUpdateField = new InnerClassUpdateField(2018);

        innerClassUpdateField.new YearTest(2018).add();

        assertEquals(2020, innerClassUpdateField.getYear());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_update_field_using_local_class() {
        LocalClassUpdateField instance = new LocalClassUpdateField();
        instance.somethingHappen();

        // TODO: please modify the following code to pass the test
        // <--start
        final Optional<Integer> expected = Optional.of(2019);
        // --end-->

        assertEquals(expected.get().intValue(), instance.getYear());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_update_field_using_anonymous_class() {
        AnonymousClassUpdateField instance = new AnonymousClassUpdateField();
        instance.somethingHappen();

        // TODO: please modify the following code to pass the test
        // <--start
        final Optional<Integer> expected = Optional.of(2019);
        // --end-->

        assertEquals(expected.get().intValue(), instance.getYear());
    }

    @Test
    void should_create_instance_for_static_inner_class() {
        StaticInnerClass instance = new StaticInnerClass();
        StaticInnerClass.Inner inner = instance.createInner();
        StaticInnerClass.Inner inners = new StaticInnerClass.Inner("dd");

        // TODO: please modify the following code to pass the test
        // <--start
        final String expected = "Hello";
        // --end-->

        assertEquals(expected, inner.getName());
    }

//    @Test
//    void should_use_parent_method() {
//       InnerClassUpdateFieldChild innerClassUpdateFieldChild = new InnerClassUpdateFieldChild();
//       innerClassUpdateFieldChild.somethingHappen();
//
//        final Optional<Integer> expected = Optional.of(2019);
//        // --end-->
//
//        assertEquals(expected.get().intValue(), innerClassUpdateFieldChild.getYear());
//    }


//    @Test
//    void should_can_use_inner_inner_class() {
//        InnerClassUpdateFieldChild innerClassUpdateFieldChild = new InnerClassUpdateFieldChild();
//
//        String innerStrClassName = InnerClassUpdateFieldChild.InnerClassParent.InnerClassChild.class.getName();
//
//        String expected = "com.cultivation.javaBasic.util.InnerClassUpdateFieldChild$InnerClassParent$InnerClassChild";
//
//        assertEquals(expected, innerStrClassName);
//    }
}
