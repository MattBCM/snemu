package com.stoozy.snemu.system;

interface FunctionPointer {
    byte func();
}

public class Instruction {
    byte opcode;
    String symbol;
    FunctionPointer operate;
    FunctionPointer addrMode;
    byte cycles;
}
