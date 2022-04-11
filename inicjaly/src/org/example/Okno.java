package org.example;
import javax.swing.*;
import java.awt.*;
public class Okno {
    JFrame f;
    Krzywa p;
    public Okno(){
        f = new JFrame();
        Container c = f.getContentPane();
        c.setLayout(new BorderLayout());
        p = new Krzywa();
        c.add(p);
        f.setSize(600,500);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String args[ ]){
        Okno o = new Okno();
    }
}