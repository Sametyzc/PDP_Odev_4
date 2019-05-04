/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odev_4;

/**
 *
 * @author MONSTER
 */
public class Islem implements Runnable {
    
    public String sonuc="";
    private final String sayilar; 
    //kurucu fonksiyon
    Islem(String sayilar){
        this.sayilar=sayilar;
    }
    @Override
    public void run() {
        sonuc = String.valueOf(Topla(sayilar));// alinan sayilarin toplamini hesaplatir
    }
    public int Topla(String sayilar)
    {
        //Toplam sayilari tutan degisken
        int toplam=0;
        int sayi=0;
        for(int i = 0;i<sayilar.length();i++)
        {
            //eger index tasmasi olursa hata vermemesi icin
            if(i+1>sayilar.length())
                break;
            //String icinden sayilari alip int e donusturme
            sayi=Integer.parseInt(sayilar.substring(i, i+1));
            //Bulunan sayilari toplam degiskenine atadik
            toplam+=sayi;
        }
        //Toplam donduruldu
        return toplam;
    }
}
