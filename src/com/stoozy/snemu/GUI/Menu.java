package com.stoozy.snemu.GUI;

import javax.swing.*;

import com.stoozy.snemu.system.Nes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Menu extends JMenuBar implements ActionListener {
    static JMenu m1;
    static JMenuItem openItem;
    static JFileChooser fileChooser = new JFileChooser();

    public Menu() {
        super();
        m1 = new JMenu("File");

        openItem = new JMenuItem("Open");
        openItem.addActionListener(this);

        m1.add(openItem);
        this.add(m1);

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        String action = arg0.getActionCommand();

        if (action.equals("Open")) {
            int ret = fileChooser.showOpenDialog(this);
            if (ret == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                System.out.println(file.getName() + " was selected");

                try {
                    byte[] bytes = Files.readAllBytes(file.toPath());

                    if (bytes[0] != 0x4E || bytes[1] != 0x45 || bytes[2] != 0x53 || bytes[3] != 0x1a) {
                        System.out.println("Invalid cartridge");
                        System.exit(0);
                    } else {
                        Nes nes = new Nes();
                        nes.start(bytes);
                    }

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    System.exit(0);
                }

            }
        }

    }

}
