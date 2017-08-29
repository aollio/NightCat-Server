package com.yemao.common;

public class Tuple<One, Two> {

    private One one;
    private Two two;

    public Tuple() {
    }

    public Tuple(One one, Two two) {
        this.one = one;
        this.two = two;
    }

    public One getOne() {
        return one;
    }

    public void setOne(One one) {
        this.one = one;
    }

    public Two getTwo() {
        return two;
    }

    public void setTwo(Two two) {
        this.two = two;
    }
}
