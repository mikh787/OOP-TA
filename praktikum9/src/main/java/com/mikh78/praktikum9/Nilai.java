package com.mikh78.praktikum9;
import java.util.Scanner;

public class Nilai {
    String nama, nim, scValue, predicate;
    float gradeUTS, gradeTGS, gradeUAS;

    //constructor tanpa parameter
    Nilai(){}

    //constructor dengan parameter
    Nilai(String nama, String nim, float uts, float tgs, float uas){
        this.nama =  nama;
        this.nim = nim;
        this.gradeUTS = uts;
        this.gradeTGS = tgs;
        this.gradeUAS = uas;
    }

    //input user
    Scanner input = new Scanner(System.in);
    void inputData(){
        System.out.println("Input Nama Mahasiswa : ");nama = input.nextLine();
        System.out.println("Input NIM Mahasiswa  : ");nim = input.nextLine();
        System.out.println("Input Nilai UTS   : ");gradeUTS = input.nextFloat();
        System.out.println("Input Nilai Tugas : ");gradeTGS = input.nextFloat();
        System.out.println("Input Nilai UAS   : ");gradeUAS = input.nextFloat();
    }

    //getter nama,nim,uts,tgs,uas
    String getNama(){
        return nama;
    }
    String getNim(){
        return nim;
    }
    float getuTs(){
        return gradeUTS;
    }
    float getTgs(){
        return gradeTGS;
    }
    float getuAs(){
        return gradeUAS;
    }

    //method perhitungan
    float getCountUTS(){
        return gradeUTS * 7/20;
    }
    float getCountTGS(){
        return gradeTGS * 3/10;
    }
    float getCountUAS(){
        return gradeUAS * 7/20;
    }

    //hasil nilai akhir
    float getfinalScore(){
        return getCountUTS() + getCountTGS() + getCountUAS();
    }

    //nilai huruf
    String getsHuruf(float nilai){
        if(nilai >= 85){
            scValue = "A";
        }else if(nilai >= 80 && nilai < 85){
            scValue = "AB";
        }else if(nilai >= 70 && nilai < 80){
            scValue = "B";
        }else if(nilai >= 65 && nilai < 70){
            scValue = "BC";
        }else if(nilai >= 60 && nilai < 65){
            scValue = "C";
        }else if(nilai >= 50 && nilai < 59){
            scValue = "D";
        }else if(nilai >= 0 && nilai < 49){
            scValue = "E";
        }
        return scValue;
    }

    //predikat
    String getPredikat(){
        switch(scValue){
            case "A" -> predicate = "Sangat baik";
            case "AB" -> predicate = "Baik";
            case "B" -> predicate = "Cukup Baik";
            case "BC" -> predicate = "Cukup";
            case "C" -> predicate = "Sedang";
            case "D" -> predicate = "Kurang";
            case "E" -> predicate = "Goblok Cok";
        }
        return predicate;
    }

    //cetak hasil
    void hasil(){
        System.out.println("=======================================");
        System.out.println("-=Perhitungan Nilai Akhir Mahasiswa=-");
        System.out.println("=======================================");
        System.out.println("Nama : "+getNama());
        System.out.println("NIM  : "+getNim());
        System.out.println("Nilai UTS   : "+getuTs()+" => "+getCountUTS());
        System.out.println("Nilai Tugas : "+getTgs()+" => "+getCountTGS());
        System.out.println("Nilai UAS   : "+getuAs()+" => "+getCountUAS());
        System.out.println("Nilai Akhir : "+getfinalScore());
        System.out.println("Nilai Huruf : "+getsHuruf(getfinalScore()));
        System.out.println("Nilai Huruf : "+getPredikat());
        System.out.println("=======================================");
    }

    String getPredikat(float nA) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}