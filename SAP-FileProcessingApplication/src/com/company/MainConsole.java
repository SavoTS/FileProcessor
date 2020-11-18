package com.company;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class MainConsole {


        public static void main(String[] args) throws IOException {
            MainUI UI=new MainUI();
            UI.setVisible(true);
            UI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    super.windowClosing(e);
                    System.exit(0);
                }
            });
        }

}
