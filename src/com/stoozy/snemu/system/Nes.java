package com.stoozy.snemu.system;

public class Nes {
    CPU cpu;
    RAM memory;

    public Nes() {
        cpu = new CPU();
        memory = new RAM();
    }

    public void start(byte[] cartridge) {
        byte prg_size = cartridge[4]; // in 16KiB units
        byte chr_size = cartridge[5]; // in 8KiB units

        System.out.println("PRG size is " + prg_size * 16 * 1024);
        System.out.println("CHR size is " + chr_size * 8 * 1024);
        System.out.println("Total file size is " + cartridge.length);
    }
}
