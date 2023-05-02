package com.stoozy.snemu.system;

public enum StatusFlags {
    C(1 << 0), // carry flag
    Z(1 << 1), // zero flag
    I(1 << 2), // interrupt flag
    D(1 << 3), // decimal mode
    B(1 << 4), // break
    U(1 << 5), // unused
    V(1 << 6), // overflow
    N(1 << 7); // negative

    public final int val;

    private StatusFlags(int x) {
        val = x;
    }
}
