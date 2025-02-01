public class KonversiWaktu {
    public static void main(String[] args) {
        // Data waktu lokal (Jakarta)
        int jamJKT = 23; // Format 24 jam
        int menitJKT = 40;
        String meridianJKT = "PM";
        String zonaWaktu = "WIB";
        
        // Konversi ke format 24 jam jika dalam format 12 jam
        if (meridianJKT.equals("PM") && jamJKT != 12) {
            jamJKT += 12;
        } else if (meridianJKT.equals("AM") && jamJKT == 12) {
            jamJKT = 0;
        }

        // Perbedaan waktu dengan kota lain (dalam jam)
        int diffLondon = -7;
        int diffNewYork = -12;
        int diffTokyo = 2;
        int diffDubai = -3;

        // Hitung waktu di kota lain
        int jamLondon = jamJKT + diffLondon;
        int jamNewYork = jamJKT + diffNewYork;
        int jamTokyo = jamJKT + diffTokyo;
        int jamDubai = jamJKT + diffDubai;

        // Normalisasi jam (pastikan dalam rentang 0-23)
        jamLondon = (jamLondon + 24) % 24;
        jamNewYork = (jamNewYork + 24) % 24;
        jamTokyo = (jamTokyo + 24) % 24;
        jamDubai = (jamDubai + 24) % 24;

        // Format waktu ke 12 jam untuk display
        String formattedTimeJKT = formatWaktu(jamJKT, menitJKT);
        String formattedTimeLondon = formatWaktu(jamLondon, menitJKT);
        String formattedTimeNewYork = formatWaktu(jamNewYork, menitJKT);
        String formattedTimeTokyo = formatWaktu(jamTokyo, menitJKT);
        String formattedTimeDubai = formatWaktu(jamDubai, menitJKT);

        // Tampilkan hasil
        System.out.println("=== KONVERSI WAKTU DUNIA ===");
        System.out.println("Jakarta (" + zonaWaktu + "): " + formattedTimeJKT);
        System.out.println("London (GMT): " + formattedTimeLondon);
        System.out.println("New York (EST): " + formattedTimeNewYork);
        System.out.println("Tokyo (JST): " + formattedTimeTokyo);
        System.out.println("Dubai (GST): " + formattedTimeDubai);

        // Informasi tambahan
        boolean isBusinessHourJKT = jamJKT >= 9 && jamJKT < 17;
        System.out.println("\n=== INFORMASI TAMBAHAN ===");
        System.out.println("Jam Kerja Jakarta: " + (isBusinessHourJKT ? "Buka" : "Tutup"));
    }

    private static String formatWaktu(int jam, int menit) {
        String period = "AM";
        int displayJam = jam;

        if (jam >= 12) {
            period = "PM";
            if (jam > 12) displayJam = jam - 12;
        }
        if (jam == 0) displayJam = 12;

        return String.format("%02d:%02d %s", displayJam, menit, period);
    }
}