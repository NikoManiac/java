package com.cultivation.javaBasic.util;

public class CloneTest implements Cloneable{
    public int test;
    public String testString;
    public Object object;

    public CloneTest(int test, String testString, Object object) {
        this.test = test;
        this.testString = testString;
        this.object = object;
    }

    @Override
    public CloneTest clone() throws CloneNotSupportedException {
        return (CloneTest) super.clone();
    }
}
