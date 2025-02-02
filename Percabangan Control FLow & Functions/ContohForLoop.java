public class ContohForLoop {
    public static void main(String[] args) {
        //  Mencetak angka 1-5
        for (int i = 1; i <= 5; i++) {
            System.out.println(i);
        }
        
        //  Perulangan pada array
        int[] angka = {10, 20, 30, 40, 50};
        for (int x : angka) {
            System.out.println(x);
        }
    }
}
