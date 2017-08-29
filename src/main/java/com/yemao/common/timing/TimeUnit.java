package com.yemao.common.timing;

public enum TimeUnit {
    SECOND(1000),
    MINUTES(60 * 1000),
    HOURS(60 * 60 * 1000),
    DAYS(60 * 60 * 24 * 1000);

    private int weight;

    TimeUnit(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }
}
