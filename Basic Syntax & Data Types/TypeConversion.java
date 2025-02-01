

public class TypeConversion {
    public static void main(String[] args) {
        // Konversi otomatis (Widening)
        int angkaInt = 100;
        long angkaLong = angkaInt;
        float angkaFloat = angkaLong;

        System.out.println("Int: " + angkaInt);
        System.out.println("Long: " + angkaLong);
        System.out.println("Float: " + angkaFloat);

        // Konversi Manual
        double angkaDouble = 123.456;
        int konversiKeInt = (int) angkaDouble;

        System.out.println("Double: " + angkaDouble);
        System.out.println("Konversi Ke Int: " + konversiKeInt);
    }
}
