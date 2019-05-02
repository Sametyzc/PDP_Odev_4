/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odev_4;
import java.util.concurrent.*;

/**
 *
 * @author MONSTER
 */
public class Odev_4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        long Baslangic,Son;
        double seri,paralel;
        
        String birler="9359666",onlar="7887995",yuzler="3996543",binler="5532138",Sonuc;
        
        Baslangic=System.nanoTime();
        Sonuc=String.valueOf(Topla(binler))+Topla(yuzler)+Topla(onlar)+Topla(birler);
        Son=System.nanoTime();
        seri=(Son-Baslangic)/1000000.0;
        Sonuc="";
        ExecutorService havuz = Executors.newFixedThreadPool(4);
        Baslangic=System.nanoTime();
        havuz.execute(new Islem(binler,Sonuc));
        havuz.execute(new Islem(yuzler,Sonuc));
        havuz.execute(new Islem(onlar,Sonuc));
        havuz.execute(new Islem(birler,Sonuc));
        Son=System.nanoTime();
        paralel=(Son-Baslangic)/1000000.0;
        System.out.println("Sonuc : "+Sonuc);
        System.out.println("Seri Zaman : "+seri);
        System.out.println("Paralel Zaman : "+paralel);
         havuz.shutdown();
         while(!havuz.isTerminated()){  } 
        
    }
    public static int Topla(String sayilar)
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
