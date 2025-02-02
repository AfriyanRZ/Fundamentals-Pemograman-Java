public class ContohSwitch {
    public static void main(String[] args) {
        int hari = 3;
        
        switch (hari) {
            case 1 -> System.out.println("Senin");
            case 2 -> System.out.println("Selasa");
            case 3 -> System.out.println("Rabu");
            case 4 -> System.out.println("Kamis");
            case 5 -> System.out.println("Jumat");
            default -> System.out.println("Hari tidak valid");
        }
    }
}