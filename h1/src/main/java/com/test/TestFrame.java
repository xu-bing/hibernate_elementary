package com.test;

import java.awt.*;
import java.awt.event.*;

public class TestFrame {
    public static void main(String[] args) {
        Frame f = new Frame();
        f.setSize(200,200);

        f.setVisible(true);
        f.addWindowListener(new MyWindowListener());
    }// main



}

class MyWindowListener implements WindowListener{

    public void windowOpened(WindowEvent e) {

    }

    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    public void windowClosed(WindowEvent e) {

    }

    public void windowIconified(WindowEvent e) {

    }

    public void windowDeiconified(WindowEvent e) {

    }

    public void windowActivated(WindowEvent e) {

    }

    public void windowDeactivated(WindowEvent e) {

    }
}