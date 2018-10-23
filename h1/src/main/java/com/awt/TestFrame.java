package com.awt;

//import java.awt.Frame;
import java.awt.*;
import java.awt.event.*;
//import java.awt.event.WindowListener;

public class TestFrame {

    /**
     * @param ar
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Frame f = new Frame();
        f.setSize(200, 200);
        f.addWindowListener(new MyWindowListener());
        f.setVisible(true);
    }

}
    class MyWindowListener implements WindowListener {

        public void windowActivated(WindowEvent e) {
            // TODO Auto-generated method stub


        }

        public void windowClosed(WindowEvent e) {
            // TODO Auto-generated method stub

        }

        public void windowClosing(WindowEvent e) {
            // TODO Auto-generated method stub
            System.exit(0);

        }

        public void windowDeactivated(WindowEvent e) {
            // TODO Auto-generated method stub

        }

        public void windowDeiconified(WindowEvent e) {
            // TODO Auto-generated method stub

        }

        public void windowIconified(WindowEvent e) {
            // TODO Auto-generated method stub

        }

        public void windowOpened(WindowEvent e) {
            // TODO Auto-generated method stub

        }


}
