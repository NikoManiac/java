package com.cultivation.javaBasic.util;

public class NestedDerivedClassWithName extends DerivedFromBaseClassWithName {
    @Override
    public String getName() {
//        this.getClass().getSuperclass().getSuperclass().getMethods();
        return "NestedDerivedClassWithName";
    }
}
