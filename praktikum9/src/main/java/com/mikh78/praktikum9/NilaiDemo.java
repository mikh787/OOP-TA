package com.mikh78.praktikum9;
import java.util.Scanner;

public class NilaiDemo {
    public static void main(String [] args){
        Nilai score1 = new Nilai("Mikhael Andreas N", "A11.2020.12836", 95.5f, 86.5f, 93.5f);
        score1.hasil();
        System.out.println("\n");
        boolean x = true;
        try (Scanner entry = new Scanner(System.in)) {
            int counter = 0;
            Nilai [] data = new Nilai[10];
            while (x){
                System.out.println("Input Data MHS [Y/T] : ");
                String ans = entry.next();
                System.out.println("");
                if (ans.equalsIgnoreCase("Y")) {
                    Nilai score2 = new Nilai();
                    score2.inputData();
                    score2.getCountUTS();
                    score2.getCountTGS();
                    score2.getCountUAS();
                    System.out.println("\n");
                    score2.hasil();
                    data[counter] = new Nilai();
                    System.out.println("");
                    counter++; // counter = counter + 1;
                    x = true;
                } else {
                    x = false;
                }
            }
        }
    }        
}
