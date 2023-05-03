package com.stoozy.snemu.system;

public interface Instructions {

    // Operations

    byte XXX(); // unknown

    byte ADC(); // add with carry

    byte AND(); // and (with accumulator)

    byte ASL(); // arithmetic shift left

    byte BCC(); // branch on carry clear

    byte BCS(); // branch on carry set

    byte BEQ(); // branch on equal (zero set)

    byte BIT(); // bit test

    byte BMI(); // branch on minus (negative set)

    byte BNE(); // branch on not equal (zero clear)

    byte BPL(); // branch on plus (negative clear)

    byte BRK(); // break / interrupt

    byte BVC(); // branch on overflow clear

    byte BVS(); // branch on overflow set

    byte CLC(); // clear carry

    byte CLD(); // clear decimal

    byte CLI(); // clear interrupt disable

    byte CLV(); // clear overflow

    byte CMP(); // compare (with accumulator)

    byte CPX(); // compare with X

    byte CPY(); // compare with Y

    byte DEC(); // decrement

    byte DEX(); // decrement X

    byte DEY(); // decrement Y

    byte EOR(); // exclusive or (with accumulator)

    byte INC(); // increment

    byte INX(); // increment X

    byte INY(); // increment Y

    byte JMP(); // jump

    byte JSR(); // jump subroutine

    byte LDA(); // load accumulator

    byte LDX(); // load X

    byte LDY(); // load Y

    byte LSR(); // logical shift right

    byte NOP(); // no operation

    byte ORA(); // or with accumulator

    byte PHA(); // push accumulator

    byte PHP(); // push processor status (SR)

    byte PLA(); // pull accumulator

    byte PLP(); // pull processor status (SR)

    byte ROL(); // rotate left

    byte ROR(); // rotate right

    byte RTI(); // return from interrupt

    byte RTS(); // return from subroutine

    byte SBC(); // subtract with carry

    byte SEC(); // set carry

    byte SED(); // set decimal

    byte SEI(); // set interrupt disable

    byte STA(); // store accumulator

    byte STX(); // store X

    byte STY(); // store Y

    byte TAX(); // transfer accumulator to X

    byte TAY(); // transfer accumulator to Y

    byte TSX(); // transfer stack pointer to X

    byte TXA(); // transfer X to accumulator

    byte TXS(); // transfer X to stack pointer

    byte TYA(); // transfer Y to accumulator

    // Addressing modes

    byte ABS(); // Absolute

    byte ABX(); // Absolute X-indexed

    byte ABY(); // Absolute Y-indexed

    byte IMM(); // Immediate

    byte IMP(); // Implied

    byte IND(); // Indirect

    byte IZX(); // Indirect X-indexed

    byte IZY(); // Indirect Y-indexed

    byte REL(); // Relative

    byte ZP0(); // Zero Page

    byte ZPX(); // Zero page X-indexed

    byte ZPY(); // Zero page Y-indexed

}
