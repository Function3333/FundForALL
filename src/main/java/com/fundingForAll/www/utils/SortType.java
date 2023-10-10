package com.fundingForAll.www.utils;

public enum SortType {
    REG_DATE(1),
    VIEWS(2),
    NONE(3);

    private final int value;

    SortType(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}
