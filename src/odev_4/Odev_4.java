/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odev_4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.*;

/**
 *
 * @author MONSTER
 */
public class Odev_4 {

    /**
     * @param args the command line arguments
     */
    //okunan basamakları tutan stringler tanımlandı
    static String binler = "";
    static String yuzler = "";
    static String onlar = "";
    static String birler = "";

    //
    /**
     * dosyadan okunan sayilari basamaklarina ayiran fonksiyon satirin ilk
     * elemani binler, ikinci elemani yuzler... seklinde ayirir
     *
     * @param satir okunan dosyadaki satirlar
     */
    static void BasamakAyir(String satir) {
        if (satir.length() == 4) {
            binler += satir.substring(0, 1);
            yuzler += satir.substring(1, 2);
            onlar += satir.substring(2, 3);
            birler += satir.substring(3, 4);
        }

    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        long Baslangic, Son;//baslangıc ve bitis zamanlarini temsil eden degiskenler tanimlandi
        double seri, paralel; //seri ve paralel hesaplamada gecen sureleri tutan degisken tanimlari

        String Sonuc; //basamak tolamlarini tutan degisken
        File dosya = new File("sayi.txt"); //dosya okuma baslangici

        FileReader okunanDosya = new FileReader(dosya);
        String satir;

        BufferedReader br = new BufferedReader(okunanDosya);
//dosyayı satır satır okumak için while döngüsü
        while ((satir = br.readLine()) != null) {

            BasamakAyir(satir);

        }

        br.close();
        //Seri icin baslangic suresi alindi
        Baslangic = System.nanoTime();
        //seri hesaplama ile sonuc hesaplandi
        Sonuc = String.valueOf(Topla(binler)) + Topla(yuzler) + Topla(onlar) + Topla(birler);
        //Hesaplanamanin bitis suresi alindi
        Son = System.nanoTime();
        //Seri hesaplama suresi bulundu
        seri = (Son - Baslangic) / 1000000.0;

        ExecutorService havuz = Executors.newFixedThreadPool(4); //4 tane thread olusturuldu
        //Seri hesapama icin baslangic suresi bulundu
        Baslangic = System.nanoTime();
        Islem x;
        Sonuc += "";
        //paralel islemler baslatildi
        havuz.execute(x = new Islem(binler));
        Sonuc += x.sonuc;
        havuz.execute(x = new Islem(yuzler));
        Sonuc += x.sonuc;
        havuz.execute(x = new Islem(onlar));
        Sonuc += x.sonuc;
        havuz.execute(x = new Islem(birler));
        Sonuc += x.sonuc;
        havuz.shutdown();

        while (!havuz.isTerminated()) {
        }
        Son = System.nanoTime();
        //paralel hesaplama bitisi bulundu
        //paralel suresi hesaplandi
        paralel = (Son - Baslangic) / 1000000.0;
        //Sonuc ekrana yazildi
        System.out.println("Sonuc : " + Sonuc);
        //Seri suresi ekrana yazildi
        System.out.println("Seri Zaman : " + seri);
        //Seri suresi hesaplandi
        System.out.println("Paralel Zaman : " + paralel);

    }

    public static int Topla(String sayilar) {
        int toplam = 0;
        int sayi = 0;
        for (int i = 0; i < sayilar.length(); i++) {
            if (i + 1 > sayilar.length()) {
                break;
            }
            sayi = Integer.parseInt(sayilar.substring(i, i + 1));
            toplam += sayi;
        }
        return toplam;
    }

}
