public class KonversiSuhu {
    public static void main(String[] args) {
        // Data input
        double suhu = 48.9;  // Suhu yang akan dikonversi
        String skalaAwal = "K";  // C = Celcius, F = Fahrenheit, R = Reamur, K = Kelvin
        String skalaTujuan = "C";  // Skala yang diinginkan
        double hasil = 0.0;
        boolean isValid = true;

        // Proses konversi menggunakan percabangan
        switch (skalaAwal) {
            case "C" -> {
                switch (skalaTujuan) {
                    case "F" -> hasil = (suhu * 9/5) + 32;
                    case "R" -> hasil = suhu * 4/5;
                    case "K" -> hasil = suhu + 273.15;
                    case "C" -> hasil = suhu;
                    default -> isValid = false;
                }
            }
            case "F" -> {
                switch (skalaTujuan) {
                    case "C" -> hasil = (suhu - 32) * 5/9;
                    case "R" -> hasil = (suhu - 32) * 4/9;
                    case "K" -> hasil = (suhu - 32) * 5/9 + 273.15;
                    case "F" -> hasil = suhu;
                    default -> isValid = false;
                }
            }
            case "R" -> {
                switch (skalaTujuan) {
                    case "C" -> hasil = suhu * 5/4;
                    case "F" -> hasil = (suhu * 9/4) + 32;
                    case "K" -> hasil = (suhu * 5/4) + 273.15;
                    case "R" -> hasil = suhu;
                    default -> isValid = false;
                }
            }
            case "K" -> {
                switch (skalaTujuan) {
                    case "C" -> hasil = suhu - 273.15;
                    case "F" -> hasil = (suhu - 273.15) * 9/5 + 32;
                    case "R" -> hasil = (suhu - 273.15) * 4/5;
                    case "K" -> hasil = suhu;
                    default -> isValid = false;
                }
            }
            default -> isValid = false;
        }

        // Menampilkan hasil
        System.out.println("=== PROGRAM KONVERSI SUHU ===");
        System.out.println("Suhu Awal: " + suhu + "°" + skalaAwal);
        
        if (isValid) {
            System.out.println("Hasil Konversi: " + String.format("%.2f", hasil) + "°" + skalaTujuan);
            
            // Tambahan informasi tentang kondisi air pada suhu tersebut (untuk Celcius)
            if (skalaTujuan.equals("C")) {
                if (hasil <= 0) {
                    System.out.println("Kondisi Air: Beku");
                } else if (hasil >= 100) {
                    System.out.println("Kondisi Air: Mendidih");
                } else {
                    System.out.println("Kondisi Air: Cair");
                }
            }
        } else {
            System.out.println("Error: Skala suhu tidak valid!");
        }
    }
}