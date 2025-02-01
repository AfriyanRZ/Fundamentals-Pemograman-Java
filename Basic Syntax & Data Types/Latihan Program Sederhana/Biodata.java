public class Biodata {
    public static void main(String[] args) {
        // Deklarasi variabel dengan berbagai tipe data
        String nama = "Rusdi si Imoet";
        int umur = 20;
        double tinggi = 170.5;
        boolean isStudent = true;
        char golonganDarah = 'A';
        
        // Menampilkan biodata dengan menggabungkan tipe data
        System.out.println("=== BIODATA ===");
        System.out.println("Nama: " + nama);
        System.out.println("Umur: " + umur + " tahun");
        System.out.println("Tinggi: " + tinggi + " cm");
        System.out.println("Mahasiswa: " + isStudent);
        System.out.println("Golongan Darah: " + golonganDarah);

        // Menghitung perkiraan umur 5 tahun kedepan 
        int umur5TahunLagi = umur + 5;
        System.out.println("\numur 5 tahun lagi: " + umur5TahunLagi + " tahun");
    }
}
