package com.stoozy.snemu.system;

interface FunctionPointer {
    byte func();
}

public class Instruction {
    String symbol;
    FunctionPointer operate;
    AddressModes addrMode;
    byte cycles;

    public Instruction(String sym, FunctionPointer op, AddressModes addrMode, byte cycles) {
        this.symbol = sym;
        this.operate = op;
        this.addrMode = addrMode;
        this.cycles = cycles;
    }

}
