package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dzbanek {

    public int Function(int wzor_i, int k) {
        if (k == 0 || k == wzor_i) return 1;
        else return Function(wzor_i-1, k-1) + Function(wzor_i-1, k); }

    public Double Silnia(int wzor_o, int wzor_p, double v){
        return Math.pow(v,wzor_p) * Math.pow(1-v,wzor_o-wzor_p) * Function(wzor_o,wzor_p);}

    String sciezka = System.getProperty("user.dir");
    int obliczanie;
    double[][][] pkt = new double[4][4][3];
    String S1 = sciezka + "\\teacup.txt";
    String S2 = sciezka + "\\teapot.txt";
    String S3 = sciezka + "\\teaspoon.txt";
    String S4 = sciezka + "\\Dane3D\\filizanka.txt";
    String S5 = sciezka + "\\Dane3D\\dzbanek.txt";
    String S6 = sciezka + "\\Dane3D\\lyzeczka.txt";
    Dzbanek() throws IOException {
        Bezier(S1, S4);
        Bezier(S2, S5);
        Bezier(S3, S6);}
    public void Bezier(String s1, String s2) throws IOException {
        File file1 = new File(s1);
        String[] tab1;
        List<String> list1 = new ArrayList<String>();
        BufferedWriter bw = new BufferedWriter(new FileWriter(s2));
        int nr_linii = 0;
        double X2 = 0.0;
        double Y2 = 0.0;
        double Z2 = 0.0;
        try (Scanner sc = new Scanner(file1)) {
            while (sc.hasNextLine()) {
                list1.add(sc.nextLine()); }
        } catch (FileNotFoundException e) {
            System.out.println(e); }
        tab1 = list1.get(nr_linii).split(" ");
        obliczanie = Integer.parseInt(tab1[0]);
        for (int i = 0; i < obliczanie; i++) {
            nr_linii++;
            for (int j = 0; j < 4; j++) {
                for (int l = 0; l < 4; l++) {
                    nr_linii += 1;
                    tab1 = list1.get(nr_linii).split(" ");
                    pkt[j][l][0] = Double.parseDouble(tab1[0]);
                    pkt[j][l][1] = Double.parseDouble(tab1[1]);
                    pkt[j][l][2] = Double.parseDouble(tab1[2]);} }
            for (double v = 0.0; v <= 1.0; v += 0.01) {
                for (double w = 0.0; w <= 1.0; w += 0.01) {
                    for (int a = 0; a <= 3; a++) {
                        for (int b = 0; b <= 3; b++) {
                            X2 += pkt[a][b][0] * Silnia(3, a, w) * Silnia(3, b, v);
                            Y2 += pkt[a][b][1] * Silnia(3, a, w) * Silnia(3, b, v);
                            Z2 += pkt[a][b][2] * Silnia(3, a, w) * Silnia(3, b, v); } }
                    bw.write(X2 + "," + Y2 + "," + Z2 + System.lineSeparator());
                    X2 = 0.0;
                    Y2 = 0.0;
                    Z2 = 0.0; }
            }} bw.close();
    }
    public static void main(String[] args) throws IOException {
        new Dzbanek();
        System.out.println("W pliku \"Dane3D\" znajdziesz potrzebne wspolrzedne do utworzenia modelu 3D :)");
    }
}