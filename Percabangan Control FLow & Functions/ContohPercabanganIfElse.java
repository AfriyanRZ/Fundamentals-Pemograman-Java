public class ContohPercabanganIfElse {
    public static void main(String[] args) {
        int umur = 20;

        //  If - Else sederhana
        if (umur >= 18) {
            System.out.println("Anda sudah Dewasa");
        } else {
            System.out.println("Anda masih di bawah umur");
        }

        //  If - Else if - Else
        int nilai = 85;
        if (nilai >= 90) {
            System.out.println("Nilai A");
        } else if (nilai >= 80) {
            System.out.println("Nilai B");
        } else if (nilai >= 70) {
            System.out.println("Nilai C");
        } else {
            System.out.println("Tidak Lulus");
        }
    }
}
