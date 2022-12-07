package depoCalısma.depoYonetimi06;

import DepoYonetimi.P11_depoYonetimi.depoYonetimi06.GirisCikisInterface;

import java.util.*;
public class Method implements GirisCikisInterface {
    public static final String W = "\u001B[37m";
    public static final String R = "\u001B[31m";
    public static final String G = "\u001B[32m";
    public static final String Y = "\u001B[33m";
    public static final String B = "\u001B[34m";
    static Scanner scan = new Scanner(System.in);
    static int id = 1000;
    static HashMap<Integer, UrunTanimlama> urunler = new HashMap<>();
    static int count = 0;

    public void girisPaneli() {

        System.out.println(Y + "========================== İŞLEMLER =======================\r\n"
                + "   ____________________              ____________________   \n"
                + "   | 1-URUN TANIMLAMA |              |  2-URUN LİSTELE  |   \n"
                + "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯              ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯   \n"
                + "   ____________________              ____________________   \n"
                + "   | 3-URUN GIRISI    |              |  4-URUN RAFA KOY |   \n"
                + "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯              ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯   \n"
                + "   ____________________              ____________________   \n"
                + "   | 5-URUN CIKISI    |              |  6-BITIRME       |   \n"
                + "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯              ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯  " + R);

        System.out.print("Yapmak istediginiz islem : ");

        try {
            int secim = scan.nextInt();

            switch (secim) {
                case 1:
                    urunTanimlama();
                    girisPaneli();
                    break;
                case 2:
                    urunListele();
                    girisPaneli();
                    break;
                case 3:
                    miktarGuncelle();
                    urunListele();
                    girisPaneli();
                    break;
                case 4:
                    rafaKoy();
                    urunListele();
                    girisPaneli();
                    break;
                case 5:
                    urunCikis();
                    urunListele();
                    girisPaneli();
                    break;
                case 6:
                    cikisYap();
                    break;
                default:
                    System.out.println("Hatali secim yaptınız");
                    girisPaneli();
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Sayi disinda karakter girilemez");
            scan.nextLine();
            girisPaneli();
        }
    }

    public void cikisYap() {
        System.out.println("Cikis yapildi");
    }

    private static void urunTanimlama() {

        System.out.println("lutfen urun bilgilerini giriniz");
        System.out.println("Urun ismi:");
        scan.nextLine();
        String urunIsmi = scan.nextLine();
        System.out.println("Uretici firma:");
        String uretici = scan.nextLine();
        System.out.println("Birim:");
        String birim = scan.nextLine();
        System.out.print("Urun Icin Raf Bilgisi Giriniz : ");
        String raf = scan.next();
        System.out.println("ürün miktarını giriniz:");
        int miktar = scan.nextInt();

        UrunTanimlama urun = new UrunTanimlama(id, urunIsmi, uretici, miktar, birim, raf);
        urunler.put(id, urun);
        id++;

    }

    public static void urunListele() {
        Set<Map.Entry<Integer, UrunTanimlama>> urunlerSeti = urunler.entrySet(); // duplicate olmasın diye set?

        System.out.println("id       ismi         ureticisi       miktari       birimi         raf" +
                "\n----------------------------------------------------------------------");
        if (!urunler.isEmpty()) {//şart açtım boş değilse şunu yap
            for (Map.Entry<Integer, UrunTanimlama> w : urunlerSeti) {//listede kaç eleman olduğunu bilmiyoruz that's why for.e
                // Integer urunlerKey= w.getKey(); gereksiz geldi atama yapmak,variable başka yerde kullanılıyor mu kontrol et!!
                System.out.printf("%d    %-8s       %-14s %3d          %-14s %s"
                        , w.getKey(), w.getValue().getUrunIsmi(), w.getValue().getUretici(), w.getValue().getMiktar(), w.getValue().getBirim(), w.getValue().getRaf());//getvalue indexin valuesu
                System.out.println("");
            }
        } else {
            System.out.println("Listelenecek herhangi bir ürün mevcut değil.\nEn az bir adet ürün girişi yapılması gerekli.\n aşağıdaki adımları takip ediniz");
            urunTanimlama();
        }//ürün yoksa 1 nolu metoda yolluyor.ürün girdisi alıyor.

    }

    public static void miktarGuncelle() {
        System.out.println("Urun girisi icin Id bilgisi giriniz");

        int arananId = scan.nextInt();
        int guncelMiktar;
        while (true) {
            try {
                guncelMiktar = Integer.parseInt(scan.next());
                break;
            } catch (Exception e) {
                System.out.println("Miktari Bir Sayi Olarak Girmelisiniz");
            }
        }

        if (urunler.keySet().contains(arananId)) {

            System.out.println("Guncel miktarinizi giriniz");
            int guncelMik = scan.nextInt();
            urunler.get(arananId).setMiktar(guncelMik);

        } else {
            System.out.println("aradiniz urun yok");
        }


    }

    private static void rafaKoy() {
        int arananId;
        while (true) {
            try {
                System.out.println("\nLutfen Rafini Guncellemek Istediginiz Urunun Id'sini Giriniz");
                arananId = Integer.parseInt(scan.next());
                break;


            } catch (Exception e) {

                System.out.println("Bir Sayi Olmali");
            }
        }
        scan.nextLine();
        if (urunler.keySet().contains(arananId)) {
            System.out.print("Guncel Raf Degerini Giriniz : ");
            String guncelRaf = scan.nextLine();

            urunler.get(arananId).setRaf(guncelRaf);

            System.out.println("Urunun Rafi Guncellendi : " + urunler.get(arananId).getRaf());

        } else {
            System.out.println("Aradıgınız Urun Sistemde Yok. Sistemdeki Urunleriniz Asagidaki Gosterilmistir");
        }
    }

    private static void urunCikis() {
        System.out.println("Urun cikisi icin Id bilgisi giriniz");
        int arananId = scan.nextInt();

        if (urunler.keySet().contains(arananId)) {

            System.out.println("Çıkarmak istediğiniz miktarinizi giriniz");
            int guncelMik = scan.nextInt();
            urunler.get(arananId).setMiktar(urunler.get(arananId).getMiktar() - guncelMik);

        } else {
            System.out.println("aradiniz urun yok");
        }

    }
}