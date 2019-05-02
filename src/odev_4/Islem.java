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
    Islem(String sayilar,String sonuc){
        this.sayilar=sayilar;
        this.sonuc=sonuc;
    }
    @Override
    public void run() {
        sonuc += String.valueOf(Topla(sayilar));
    }
    public int Topla(String sayilar)
    {
        int toplam=0;
        int sayi=0;
        for(int i = 0;i<sayilar.length();i++)
        {
            if(i+1>sayilar.length())
                break;
            sayi=Integer.parseInt(sayilar.substring(i, i+1));
          //  System.out.println("sayi : "+ sayi);
            toplam+=sayi;
        }
        return toplam;
    }
}
