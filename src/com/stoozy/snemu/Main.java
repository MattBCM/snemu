package com.stoozy.snemu;

import java.util.ArrayList;

import com.stoozy.snemu.GUI.Gui;
import com.stoozy.snemu.system.CPU;

public class Main {

    public static void main(String[] args) {
        String hex = "A9 01 8D 00 02 A9 05 8D 01 02 A9 08 8D 02 02";
        String[] hexSplit = hex.split(" ", 0);

        ArrayList<Character> object_code = new ArrayList<>();
        for (String h : hexSplit) {
            int opc = Integer.valueOf(h, 16);
            object_code.add((char) opc);
        }

        CPU cpu = new CPU();

        ArrayList<String> instructions = cpu.disassemble(object_code);

        Gui gui = new Gui(500, 480);
        gui.display();

        for (int i = 0; i < instructions.size(); i++) {
            char[] chars = instructions.get(i).toCharArray();
            gui.getGraphics().drawChars(chars, 0, chars.length, 10, 20 + i * 20);
        }
    }

}
