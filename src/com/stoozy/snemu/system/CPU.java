package com.stoozy.snemu.system;

import java.util.ArrayList;

public class CPU implements Instructions {
    // Registers
    short pc; // program counter
    byte ac; // accumulator
    byte x, y;
    byte sr;
    byte sp;
    byte srflags; // see enum in statusflags.java

    Instruction[] instructions;

    public CPU() {
        instructions = new Instruction[] {
                new Instruction("BRK", this::BRK, AddressModes.IMM, (byte) 7),
                new Instruction("ORA", this::ORA, AddressModes.IZX, (byte) 6),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 2),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 8),
                new Instruction("???", this::NOP, AddressModes.IMP, (byte) 3),
                new Instruction("ORA", this::ORA, AddressModes.ZP0, (byte) 3),
                new Instruction("ASL", this::ASL, AddressModes.ZP0, (byte) 5),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 5),
                new Instruction("PHP", this::PHP, AddressModes.IMP, (byte) 3),
                new Instruction("ORA", this::ORA, AddressModes.IMM, (byte) 2),
                new Instruction("ASL", this::ASL, AddressModes.IMP, (byte) 2),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 2),
                new Instruction("???", this::NOP, AddressModes.IMP, (byte) 4),
                new Instruction("ORA", this::ORA, AddressModes.ABS, (byte) 4),
                new Instruction("ASL", this::ASL, AddressModes.ABS, (byte) 6),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 6),
                new Instruction("BPL", this::BPL, AddressModes.REL, (byte) 2),
                new Instruction("ORA", this::ORA, AddressModes.IZY, (byte) 5),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 2),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 8),
                new Instruction("???", this::NOP, AddressModes.IMP, (byte) 4),
                new Instruction("ORA", this::ORA, AddressModes.ZPX, (byte) 4),
                new Instruction("ASL", this::ASL, AddressModes.ZPX, (byte) 6),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 6),
                new Instruction("CLC", this::CLC, AddressModes.IMP, (byte) 2),
                new Instruction("ORA", this::ORA, AddressModes.ABY, (byte) 4),
                new Instruction("???", this::NOP, AddressModes.IMP, (byte) 2),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 7),
                new Instruction("???", this::NOP, AddressModes.IMP, (byte) 4),
                new Instruction("ORA", this::ORA, AddressModes.ABX, (byte) 4),
                new Instruction("ASL", this::ASL, AddressModes.ABX, (byte) 7),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 7),
                new Instruction("JSR", this::JSR, AddressModes.ABS, (byte) 6),
                new Instruction("AND", this::AND, AddressModes.IZX, (byte) 6),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 2),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 8),
                new Instruction("BIT", this::BIT, AddressModes.ZP0, (byte) 3),
                new Instruction("AND", this::AND, AddressModes.ZP0, (byte) 3),
                new Instruction("ROL", this::ROL, AddressModes.ZP0, (byte) 5),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 5),
                new Instruction("PLP", this::PLP, AddressModes.IMP, (byte) 4),
                new Instruction("AND", this::AND, AddressModes.IMM, (byte) 2),
                new Instruction("ROL", this::ROL, AddressModes.IMP, (byte) 2),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 2),
                new Instruction("BIT", this::BIT, AddressModes.ABS, (byte) 4),
                new Instruction("AND", this::AND, AddressModes.ABS, (byte) 4),
                new Instruction("ROL", this::ROL, AddressModes.ABS, (byte) 6),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 6),
                new Instruction("BMI", this::BMI, AddressModes.REL, (byte) 2),
                new Instruction("AND", this::AND, AddressModes.IZY, (byte) 5),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 2),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 8),
                new Instruction("???", this::NOP, AddressModes.IMP, (byte) 4),
                new Instruction("AND", this::AND, AddressModes.ZPX, (byte) 4),
                new Instruction("ROL", this::ROL, AddressModes.ZPX, (byte) 6),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 6),
                new Instruction("SEC", this::SEC, AddressModes.IMP, (byte) 2),
                new Instruction("AND", this::AND, AddressModes.ABY, (byte) 4),
                new Instruction("???", this::NOP, AddressModes.IMP, (byte) 2),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 7),
                new Instruction("???", this::NOP, AddressModes.IMP, (byte) 4),
                new Instruction("AND", this::AND, AddressModes.ABX, (byte) 4),
                new Instruction("ROL", this::ROL, AddressModes.ABX, (byte) 7),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 7),
                new Instruction("RTI", this::RTI, AddressModes.IMP, (byte) 6),
                new Instruction("EOR", this::EOR, AddressModes.IZX, (byte) 6),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 2),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 8),
                new Instruction("???", this::NOP, AddressModes.IMP, (byte) 3),
                new Instruction("EOR", this::EOR, AddressModes.ZP0, (byte) 3),
                new Instruction("LSR", this::LSR, AddressModes.ZP0, (byte) 5),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 5),
                new Instruction("PHA", this::PHA, AddressModes.IMP, (byte) 3),
                new Instruction("EOR", this::EOR, AddressModes.IMM, (byte) 2),
                new Instruction("LSR", this::LSR, AddressModes.IMP, (byte) 2),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 2),
                new Instruction("JMP", this::JMP, AddressModes.ABS, (byte) 3),
                new Instruction("EOR", this::EOR, AddressModes.ABS, (byte) 4),
                new Instruction("LSR", this::LSR, AddressModes.ABS, (byte) 6),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 6),
                new Instruction("BVC", this::BVC, AddressModes.REL, (byte) 2),
                new Instruction("EOR", this::EOR, AddressModes.IZY, (byte) 5),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 2),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 8),
                new Instruction("???", this::NOP, AddressModes.IMP, (byte) 4),
                new Instruction("EOR", this::EOR, AddressModes.ZPX, (byte) 4),
                new Instruction("LSR", this::LSR, AddressModes.ZPX, (byte) 6),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 6),
                new Instruction("CLI", this::CLI, AddressModes.IMP, (byte) 2),
                new Instruction("EOR", this::EOR, AddressModes.ABY, (byte) 4),
                new Instruction("???", this::NOP, AddressModes.IMP, (byte) 2),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 7),
                new Instruction("???", this::NOP, AddressModes.IMP, (byte) 4),
                new Instruction("EOR", this::EOR, AddressModes.ABX, (byte) 4),
                new Instruction("LSR", this::LSR, AddressModes.ABX, (byte) 7),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 7),
                new Instruction("RTS", this::RTS, AddressModes.IMP, (byte) 6),
                new Instruction("ADC", this::ADC, AddressModes.IZX, (byte) 6),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 2),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 8),
                new Instruction("???", this::NOP, AddressModes.IMP, (byte) 3),
                new Instruction("ADC", this::ADC, AddressModes.ZP0, (byte) 3),
                new Instruction("ROR", this::ROR, AddressModes.ZP0, (byte) 5),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 5),
                new Instruction("PLA", this::PLA, AddressModes.IMP, (byte) 4),
                new Instruction("ADC", this::ADC, AddressModes.IMM, (byte) 2),
                new Instruction("ROR", this::ROR, AddressModes.IMP, (byte) 2),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 2),
                new Instruction("JMP", this::JMP, AddressModes.IND, (byte) 5),
                new Instruction("ADC", this::ADC, AddressModes.ABS, (byte) 4),
                new Instruction("ROR", this::ROR, AddressModes.ABS, (byte) 6),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 6),
                new Instruction("BVS", this::BVS, AddressModes.REL, (byte) 2),
                new Instruction("ADC", this::ADC, AddressModes.IZY, (byte) 5),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 2),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 8),
                new Instruction("???", this::NOP, AddressModes.IMP, (byte) 4),
                new Instruction("ADC", this::ADC, AddressModes.ZPX, (byte) 4),
                new Instruction("ROR", this::ROR, AddressModes.ZPX, (byte) 6),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 6),
                new Instruction("SEI", this::SEI, AddressModes.IMP, (byte) 2),
                new Instruction("ADC", this::ADC, AddressModes.ABY, (byte) 4),
                new Instruction("???", this::NOP, AddressModes.IMP, (byte) 2),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 7),
                new Instruction("???", this::NOP, AddressModes.IMP, (byte) 4),
                new Instruction("ADC", this::ADC, AddressModes.ABX, (byte) 4),
                new Instruction("ROR", this::ROR, AddressModes.ABX, (byte) 7),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 7),
                new Instruction("???", this::NOP, AddressModes.IMP, (byte) 2),
                new Instruction("STA", this::STA, AddressModes.IZX, (byte) 6),
                new Instruction("???", this::NOP, AddressModes.IMP, (byte) 2),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 6),
                new Instruction("STY", this::STY, AddressModes.ZP0, (byte) 3),
                new Instruction("STA", this::STA, AddressModes.ZP0, (byte) 3),
                new Instruction("STX", this::STX, AddressModes.ZP0, (byte) 3),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 3),
                new Instruction("DEY", this::DEY, AddressModes.IMP, (byte) 2),
                new Instruction("???", this::NOP, AddressModes.IMP, (byte) 2),
                new Instruction("TXA", this::TXA, AddressModes.IMP, (byte) 2),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 2),
                new Instruction("STY", this::STY, AddressModes.ABS, (byte) 4),
                new Instruction("STA", this::STA, AddressModes.ABS, (byte) 4),
                new Instruction("STX", this::STX, AddressModes.ABS, (byte) 4),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 4),
                new Instruction("BCC", this::BCC, AddressModes.REL, (byte) 2),
                new Instruction("STA", this::STA, AddressModes.IZY, (byte) 6),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 2),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 6),
                new Instruction("STY", this::STY, AddressModes.ZPX, (byte) 4),
                new Instruction("STA", this::STA, AddressModes.ZPX, (byte) 4),
                new Instruction("STX", this::STX, AddressModes.ZPY, (byte) 4),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 4),
                new Instruction("TYA", this::TYA, AddressModes.IMP, (byte) 2),
                new Instruction("STA", this::STA, AddressModes.ABY, (byte) 5),
                new Instruction("TXS", this::TXS, AddressModes.IMP, (byte) 2),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 5),
                new Instruction("???", this::NOP, AddressModes.IMP, (byte) 5),
                new Instruction("STA", this::STA, AddressModes.ABX, (byte) 5),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 5),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 5),
                new Instruction("LDY", this::LDY, AddressModes.IMM, (byte) 2),
                new Instruction("LDA", this::LDA, AddressModes.IZX, (byte) 6),
                new Instruction("LDX", this::LDX, AddressModes.IMM, (byte) 2),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 6),
                new Instruction("LDY", this::LDY, AddressModes.ZP0, (byte) 3),
                new Instruction("LDA", this::LDA, AddressModes.ZP0, (byte) 3),
                new Instruction("LDX", this::LDX, AddressModes.ZP0, (byte) 3),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 3),
                new Instruction("TAY", this::TAY, AddressModes.IMP, (byte) 2),
                new Instruction("LDA", this::LDA, AddressModes.IMM, (byte) 2),
                new Instruction("TAX", this::TAX, AddressModes.IMP, (byte) 2),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 2),
                new Instruction("LDY", this::LDY, AddressModes.ABS, (byte) 4),
                new Instruction("LDA", this::LDA, AddressModes.ABS, (byte) 4),
                new Instruction("LDX", this::LDX, AddressModes.ABS, (byte) 4),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 4),
                new Instruction("BCS", this::BCS, AddressModes.REL, (byte) 2),
                new Instruction("LDA", this::LDA, AddressModes.IZY, (byte) 5),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 2),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 5),
                new Instruction("LDY", this::LDY, AddressModes.ZPX, (byte) 4),
                new Instruction("LDA", this::LDA, AddressModes.ZPX, (byte) 4),
                new Instruction("LDX", this::LDX, AddressModes.ZPY, (byte) 4),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 4),
                new Instruction("CLV", this::CLV, AddressModes.IMP, (byte) 2),
                new Instruction("LDA", this::LDA, AddressModes.ABY, (byte) 4),
                new Instruction("TSX", this::TSX, AddressModes.IMP, (byte) 2),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 4),
                new Instruction("LDY", this::LDY, AddressModes.ABX, (byte) 4),
                new Instruction("LDA", this::LDA, AddressModes.ABX, (byte) 4),
                new Instruction("LDX", this::LDX, AddressModes.ABY, (byte) 4),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 4),
                new Instruction("CPY", this::CPY, AddressModes.IMM, (byte) 2),
                new Instruction("CMP", this::CMP, AddressModes.IZX, (byte) 6),
                new Instruction("???", this::NOP, AddressModes.IMP, (byte) 2),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 8),
                new Instruction("CPY", this::CPY, AddressModes.ZP0, (byte) 3),
                new Instruction("CMP", this::CMP, AddressModes.ZP0, (byte) 3),
                new Instruction("DEC", this::DEC, AddressModes.ZP0, (byte) 5),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 5),
                new Instruction("INY", this::INY, AddressModes.IMP, (byte) 2),
                new Instruction("CMP", this::CMP, AddressModes.IMM, (byte) 2),
                new Instruction("DEX", this::DEX, AddressModes.IMP, (byte) 2),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 2),
                new Instruction("CPY", this::CPY, AddressModes.ABS, (byte) 4),
                new Instruction("CMP", this::CMP, AddressModes.ABS, (byte) 4),
                new Instruction("DEC", this::DEC, AddressModes.ABS, (byte) 6),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 6),
                new Instruction("BNE", this::BNE, AddressModes.REL, (byte) 2),
                new Instruction("CMP", this::CMP, AddressModes.IZY, (byte) 5),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 2),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 8),
                new Instruction("???", this::NOP, AddressModes.IMP, (byte) 4),
                new Instruction("CMP", this::CMP, AddressModes.ZPX, (byte) 4),
                new Instruction("DEC", this::DEC, AddressModes.ZPX, (byte) 6),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 6),
                new Instruction("CLD", this::CLD, AddressModes.IMP, (byte) 2),
                new Instruction("CMP", this::CMP, AddressModes.ABY, (byte) 4),
                new Instruction("NOP", this::NOP, AddressModes.IMP, (byte) 2),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 7),
                new Instruction("???", this::NOP, AddressModes.IMP, (byte) 4),
                new Instruction("CMP", this::CMP, AddressModes.ABX, (byte) 4),
                new Instruction("DEC", this::DEC, AddressModes.ABX, (byte) 7),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 7),
                new Instruction("CPX", this::CPX, AddressModes.IMM, (byte) 2),
                new Instruction("SBC", this::SBC, AddressModes.IZX, (byte) 6),
                new Instruction("???", this::NOP, AddressModes.IMP, (byte) 2),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 8),
                new Instruction("CPX", this::CPX, AddressModes.ZP0, (byte) 3),
                new Instruction("SBC", this::SBC, AddressModes.ZP0, (byte) 3),
                new Instruction("INC", this::INC, AddressModes.ZP0, (byte) 5),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 5),
                new Instruction("INX", this::INX, AddressModes.IMP, (byte) 2),
                new Instruction("SBC", this::SBC, AddressModes.IMM, (byte) 2),
                new Instruction("NOP", this::NOP, AddressModes.IMP, (byte) 2),
                new Instruction("???", this::SBC, AddressModes.IMP, (byte) 2),
                new Instruction("CPX", this::CPX, AddressModes.ABS, (byte) 4),
                new Instruction("SBC", this::SBC, AddressModes.ABS, (byte) 4),
                new Instruction("INC", this::INC, AddressModes.ABS, (byte) 6),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 6),
                new Instruction("BEQ", this::BEQ, AddressModes.REL, (byte) 2),
                new Instruction("SBC", this::SBC, AddressModes.IZY, (byte) 5),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 2),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 8),
                new Instruction("???", this::NOP, AddressModes.IMP, (byte) 4),
                new Instruction("SBC", this::SBC, AddressModes.ZPX, (byte) 4),
                new Instruction("INC", this::INC, AddressModes.ZPX, (byte) 6),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 6),
                new Instruction("SED", this::SED, AddressModes.IMP, (byte) 2),
                new Instruction("SBC", this::SBC, AddressModes.ABY, (byte) 4),
                new Instruction("NOP", this::NOP, AddressModes.IMP, (byte) 2),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 7),
                new Instruction("???", this::NOP, AddressModes.IMP, (byte) 4),
                new Instruction("SBC", this::SBC, AddressModes.ABX, (byte) 4),
                new Instruction("INC", this::INC, AddressModes.ABX, (byte) 7),
                new Instruction("???", this::XXX, AddressModes.IMP, (byte) 7),

        };

    }

    // Note: using Character because it's unsigned
    public ArrayList<String> disassemble(ArrayList<Character> code) {
        ArrayList<String> instrs = new ArrayList<>();

        for (int i = 0; i < code.size(); i++) {
            Instruction instr = instructions[code.get(i)];
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append(instr.symbol);
            stringBuilder.append(" ");
            switch (instr.addrMode) {
                // no operand
                case IMP:
                    stringBuilder.append(" {IMP}");
                    break;
                case ABS: {
                    char hi = code.get(i + 2);
                    char lo = code.get(i + 1);
                    short addr = (short) (hi * 256 + lo);
                    i += 2;
                    stringBuilder.append(String.format("$%X", (short) addr));
                    stringBuilder.append(" {ABS}");
                    break;

                }
                case ABX: {
                    char hi = code.get(i + 2);
                    char lo = code.get(i + 1);
                    short addr = (short) (hi * 256 + lo);
                    stringBuilder.append(String.format("$%X, X", (short) addr));
                    stringBuilder.append(" {ABX}");
                    i += 2;

                    break;
                }
                case ABY: {
                    char hi = code.get(i + 2);
                    char lo = code.get(i + 1);
                    short addr = (short) (hi * 256 + lo);
                    stringBuilder.append(String.format("$%X, Y", (short) addr));
                    stringBuilder.append(" {ABY}");
                    i += 2;

                    break;
                }
                case ZP0: {
                    char operand = code.get(++i);
                    stringBuilder.append(String.format("$%02X", (int) operand));
                    stringBuilder.append(" {ZP0}");
                    break;
                }
                case IMM: {
                    char operand = code.get(++i);
                    stringBuilder.append(String.format("#$%02X", (int) operand));
                    stringBuilder.append(" {IMM}");
                    break;
                }
                case IND: {
                    char hi = code.get(i + 2);
                    char lo = code.get(i + 1);
                    short addr = (short) (hi * 256 + lo);
                    stringBuilder.append(String.format("($%X)", (short) addr));
                    stringBuilder.append(" {IND}");
                    break;
                }
                default:
                    throw new Error("Invalid addressing mode");
            }

            instrs.add(stringBuilder.toString());
        }

        return instrs;
    }

    @Override
    public byte ADC() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ADC'");
    }

    @Override
    public byte AND() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'AND'");
    }

    @Override
    public byte ASL() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ASL'");
    }

    @Override
    public byte BCC() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'BCC'");
    }

    @Override
    public byte BCS() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'BCS'");
    }

    @Override
    public byte BEQ() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'BEQ'");
    }

    @Override
    public byte BIT() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'BIT'");
    }

    @Override
    public byte BMI() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'BMI'");
    }

    @Override
    public byte BNE() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'BNE'");
    }

    @Override
    public byte BPL() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'BPL'");
    }

    @Override
    public byte BRK() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'BRK'");
    }

    @Override
    public byte BVC() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'BVC'");
    }

    @Override
    public byte BVS() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'BVS'");
    }

    @Override
    public byte CLC() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'CLC'");
    }

    @Override
    public byte CLD() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'CLD'");
    }

    @Override
    public byte CLI() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'CLI'");
    }

    @Override
    public byte CLV() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'CLV'");
    }

    @Override
    public byte CMP() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'CMP'");
    }

    @Override
    public byte CPX() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'CPX'");
    }

    @Override
    public byte CPY() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'CPY'");
    }

    @Override
    public byte DEC() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'DEC'");
    }

    @Override
    public byte DEX() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'DEX'");
    }

    @Override
    public byte DEY() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'DEY'");
    }

    @Override
    public byte EOR() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'EOR'");
    }

    @Override
    public byte INC() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'INC'");
    }

    @Override
    public byte INX() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'INX'");
    }

    @Override
    public byte INY() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'INY'");
    }

    @Override
    public byte JMP() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'JMP'");
    }

    @Override
    public byte JSR() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'JSR'");
    }

    @Override
    public byte LDA() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'LDA'");
    }

    @Override
    public byte LDX() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'LDX'");
    }

    @Override
    public byte LDY() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'LDY'");
    }

    @Override
    public byte LSR() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'LSR'");
    }

    @Override
    public byte NOP() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'NOP'");
    }

    @Override
    public byte ORA() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ORA'");
    }

    @Override
    public byte PHA() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'PHA'");
    }

    @Override
    public byte PHP() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'PHP'");
    }

    @Override
    public byte PLA() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'PLA'");
    }

    @Override
    public byte PLP() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'PLP'");
    }

    @Override
    public byte ROL() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ROL'");
    }

    @Override
    public byte ROR() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ROR'");
    }

    @Override
    public byte RTI() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'RTI'");
    }

    @Override
    public byte RTS() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'RTS'");
    }

    @Override
    public byte SBC() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'SBC'");
    }

    @Override
    public byte SEC() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'SEC'");
    }

    @Override
    public byte SED() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'SED'");
    }

    @Override
    public byte SEI() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'SEI'");
    }

    @Override
    public byte STA() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'STA'");
    }

    @Override
    public byte STX() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'STX'");
    }

    @Override
    public byte STY() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'STY'");
    }

    @Override
    public byte TAX() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'TAX'");
    }

    @Override
    public byte TAY() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'TAY'");
    }

    @Override
    public byte TSX() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'TSX'");
    }

    @Override
    public byte TXA() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'TXA'");
    }

    @Override
    public byte TXS() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'TXS'");
    }

    @Override
    public byte TYA() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'TYA'");
    }

    @Override
    public byte ABS() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ABS'");
    }

    @Override
    public byte ABX() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ABX'");
    }

    @Override
    public byte ABY() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ABY'");
    }

    @Override
    public byte IMM() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'IMM'");
    }

    @Override
    public byte IMP() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'IMP'");
    }

    @Override
    public byte IND() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'IND'");
    }

    @Override
    public byte IZX() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'IZX'");
    }

    @Override
    public byte IZY() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'IZY'");
    }

    @Override
    public byte REL() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'REL'");
    }

    @Override
    public byte ZP0() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ZPG'");
    }

    @Override
    public byte ZPX() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ZPX'");
    }

    @Override
    public byte ZPY() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ZPY'");
    }

    @Override
    public byte XXX() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'XXX'");
    }

}
