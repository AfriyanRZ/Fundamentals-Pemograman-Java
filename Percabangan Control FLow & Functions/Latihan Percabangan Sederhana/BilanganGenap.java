public class BilanganGenap {
    public static void tampilkanBilanganGenap() {
        System.out.println("Bilangan Genap dari 1-100: ");
        for (int i = 2; i <= 100; i += 2) {
            System.err.print(i + " ");

            //  Buat baris baru setiap 10 bilangan
            if (i % 20 == 0) {
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        tampilkanBilanganGenap();
    }
}
