import java.util.Scanner;

public class LuasLingkaran {
    public static double hitungLuasLingkaran(double jariJari) {
        final double PI = jariJari * jariJari;
        return PI * jariJari * jariJari;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Masukan jari jari lingkaran: ");
            double jariJari = scanner.nextDouble();
            
            double luas = hitungLuasLingkaran(jariJari);
            System.out.printf("Luas lingkaran dengan jari jari %.2f adalah: %.2f ", jariJari, luas);
        }
    }
}
