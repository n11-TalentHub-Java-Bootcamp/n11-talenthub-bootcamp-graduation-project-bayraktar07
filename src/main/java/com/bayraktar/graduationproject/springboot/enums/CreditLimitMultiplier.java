package com.bayraktar.graduationproject.springboot.enums;

public enum CreditLimitMultiplier {

    MULTIPLIER(4);

    private int value;

    CreditLimitMultiplier(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
