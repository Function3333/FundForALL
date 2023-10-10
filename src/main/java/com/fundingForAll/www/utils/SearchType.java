package com.fundingForAll.www.utils;

public enum SearchType {
    ID(1),
    TITLE(2),
    NONE(3);

    private final int value;

    SearchType(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}
