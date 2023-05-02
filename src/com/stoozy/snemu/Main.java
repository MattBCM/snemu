package com.stoozy.snemu;

import com.stoozy.snemu.GUI.Gui;

public class Main {

    public static void main(String[] args) {
        String binary = "A5 07 65 A0 E4 32";
        String[] instructions = binary.split(" ", 0);

        for (String instr : instructions) {
            int opc = Integer.valueOf(instr, 16);
            System.out.println("Opcode is " + String.format("0x%x", opc));

        }
        // Gui gui = new Gui(500, 480);
        // gui.display();
    }

}
