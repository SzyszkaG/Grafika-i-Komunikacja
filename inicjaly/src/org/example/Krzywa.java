package org.example;
import javax.swing.*;
import java.awt.*;
import static java.lang.StrictMath.pow;

public class Krzywa extends JPanel {
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        setBackground(Color.white);
        double Px, Py,Gx,Gy;
        double[] x = {228,237,237,160,160,143,143,120,120,86,86,86,86,85,85,93,93,96,96,110,110,127,127,147,147,153,153,250,250,252,252,257,257,252,252,238,238,226,226,183,183,170,170};
        double[] y = {106,93,93,84,84,88,88,94,94,141,141,156,156,192,192,280,280,281,281,286,286,301,301,298,298,297,297,289,289,274,274,239,239,215,215,210,210,206,206,212,212,212,212};
        double[]x1={528,524,524,494,494,473,473,458,458,428,428,423,423,400,400,435,435,421,421,407,407,493,493,481,481,474,474,520,520,530,530,544,544,536,536,534,534,532,532,519,519,507,507,492,492,472,472,457,457,441,441,398,398,395,395,391,391,404,404,378};
        double[]y1={119,80,80,90,90,87,87,85,85,98,98,112,112,176,176,187,187,180,180,173,173,218,218,209,209,204,204,232,232,244,244,261,261,261,261,278,278,293,293,326,326,327,327,328,328,345,345,346,346,347,347,369,369,354,354,333,333,306,306,319};
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(5f,
                BasicStroke.CAP_SQUARE,
                BasicStroke.JOIN_MITER,
                10));
        for(int i = 0; i <= 9; i++) {
            for(double t = 0; t <=1; t=t+0.001) {
                Px = pow(1-t,3) * x[4*i] + 3 * pow(1-t,2) * t * x[4*i+1] + 3 * (1-t) * pow(t,2) * x[4*i+2] + pow(t,3) * x[4*i+3];
                Py = pow(1-t,3) * y[4*i] + 3 * pow(1-t,2) * t * y[4*i+1] + 3 * (1-t) * pow(t,2) * y[4*i+2] + pow(t,3) * y[4*i+3];
                g2.drawLine((int)Px,(int)Py, (int)Px, (int)Py);
            }
        }
        for(int i = 0; i <= 14; i++) {
            for(double t = 0; t <=1; t=t+0.001) {
                Gx = pow(1-t,3) * x1[4*i] + 3 * pow(1-t,2) * t * x1[4*i+1] + 3 * (1-t) * pow(t,2) * x1[4*i+2] + pow(t,3) * x1[4*i+3];
                Gy = pow(1-t,3) * y1[4*i] + 3 * pow(1-t,2) * t * y1[4*i+1] + 3 * (1-t) * pow(t,2) * y1[4*i+2] + pow(t,3) * y1[4*i+3];
                g2.drawLine((int)Gx,(int)Gy, (int)Gx, (int)Gy);
            }
        }
    }
}
