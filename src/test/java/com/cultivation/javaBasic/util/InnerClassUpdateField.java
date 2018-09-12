package com.cultivation.javaBasic.util;

public class InnerClassUpdateField {
    private int year;

    public int getYear() {
        return year;
    }

    public void add() {
        class YearTest{
            final static int test = 2;
        }
        this.new YearTest(2).addYear();
    }
    public InnerClassUpdateField(int year) {
        this.year = year;
    }

//    public void someThingHappen() {
//        new YearTest().add();
//    }

    public class YearTest {
        private int year;
        final static int teatInt = 2;

        public void addYear() {
            InnerClassUpdateField.this.year += this.year;
        }
        public YearTest(int year) {
//            InnerClassUpdateField.this.year = InnerClassUpdateField.this.year + year;
            this.year = year;
        }

        public void add() {
            year = year + 2;
        }
    }
}
