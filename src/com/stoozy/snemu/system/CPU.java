package com.stoozy.snemu.system;

public class CPU {
    // Registers
    short pc; // program counter
    byte ac; // accumulator
    byte x, y;
    byte sr;
    byte sp;
    byte srflags; // see enum in statusflags.java

    Instruction[] instructions;

    public CPU() {
    }

}
