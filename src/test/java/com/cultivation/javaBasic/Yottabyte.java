package com.cultivation.javaBasic;

import java.util.Iterator;

public class Yottabyte implements Iterable<Double>{
    double pow;
    public Yottabyte(double pow) {
        this.pow = pow;
    }

    @Override
    public Iterator<Double> iterator() {
        return new YottabyteIterator(pow);
    }

}
class YottabyteIterator implements Iterator<Double> {
    private double pow;
    private double current = 1;
    YottabyteIterator(double pow) {
        this.pow = pow;
    }

    @Override
    public boolean hasNext() {
        return Math.pow(current, 10) < pow;
    }

    @Override
    public Double next() {
        return (double)Math.pow(current++, 10);
    }
}
