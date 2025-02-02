public class ContohMetodeSederhana {
    //  Metode tanpa parameter dan return value
    public static void sapaan() {
        System.out.println("Halo, selamat datang!");
    }

    //  Metode dengan parameter
    public static void tambahNilai(int a, int b) {
        int hasil = a + b;
        System.out.println("Hasil penjumlahan " + hasil);
    }

    //  Metode dengan return value
    public static int hitungLuasPersegi(int sisi) {
        return sisi * sisi;
    }

    public static void main(String[] args) {
        //  Pemanggilan metode
        sapaan();

        tambahNilai(5, 3);

        int luas = hitungLuasPersegi(4);
        System.out.println("Luas Persegi: " + luas);
    }
}
