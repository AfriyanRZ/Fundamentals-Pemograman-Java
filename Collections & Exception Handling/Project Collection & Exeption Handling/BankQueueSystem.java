import java.io.*;
import java.util.*;

class Customer {
    private static int queueCounter = 1;    
    private final int number;
    private final String name;
    private final String serviceType;
    private final boolean isVIP;
    private final int estimatedTime;

    public Customer(int number, String name, String serviceType, boolean isVIP, int estimatedTime) {
        this.number = number;
        this.name = name;
        this.serviceType = serviceType;
        this.isVIP = isVIP;
        this.estimatedTime = estimatedTime;
    }

    public static int getNextQueueNumber() {
        return queueCounter++;
    }

    public int getNumber() { return number; }
    public String getServiceType() { return serviceType; }
    public String getName() { return name; }
    public boolean isVIP() { return isVIP; }
    public int getEstimatedTime() { return estimatedTime; }

    @Override
    public String toString() {
        return number + "," + name + "," + serviceType + "," + isVIP + "," + estimatedTime;
    }

    public static Customer fromString(String line) {
        String[] parts = line.split(",");
        return new Customer(
            Integer.parseInt(parts[0]),
            parts[1],
            parts[2],
            Boolean.parseBoolean(parts[3]),
            Integer.parseInt(parts[4])
        );
    }
}

public class BankQueueSystem {
    private static final String FILE_NAME = "antrian.txt";
    private static final Map<String, Queue<Customer>> serviceQueues = new HashMap<>();
    private static final Map<String, Integer> serviceTimes = Map.of(
        "Setoran", 5, "Penarikan", 7, "CS", 10
    );
    private static final Map<String, Integer> dailyStatistics = new HashMap<>();

    static {
        serviceQueues.put("Setoran", new LinkedList<>());
        serviceQueues.put("Penarikan", new LinkedList<>());
        serviceQueues.put("CS", new LinkedList<>());
        loadQueueFromFile();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("\n===== SISTEM ANTRIAN BANK =====");
                System.out.println("1. Ambil Nomor Antrian");
                System.out.println("2. Panggil Antrian");
                System.out.println("3. Lihat Antrian");
                System.out.println("4. Tampilkan Statistik Harian");
                System.out.println("5. Keluar");
                System.out.print("Pilih menu: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        takeQueue(scanner);
                        break;
                    case 2:
                        callQueue();
                        break;
                    case 3:
                        displayQueue();
                        break;
                    case 4:
                        displayStatistics();
                        break;
                    case 5:
                        saveQueueToFile();
                        System.out.println("Terima kasih telah menggunakan sistem antrian bank!");
                        return;
                    default:
                        System.out.println("Pilihan tidak valid. Coba lagi.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input harus berupa angka! Coba lagi.");
                scanner.next();
            }
        }
    }

    private static void takeQueue(Scanner scanner) {
        System.out.print("Masukkan Nama: ");
        String name = scanner.nextLine();

        System.out.println("Pilih Jenis Layanan: [Setoran/Penarikan/CS]");
        String service = scanner.nextLine();

        if (!serviceQueues.containsKey(service)) {
            System.out.println("Layanan tidak tersedia. Coba lagi.");
            return;
        }

        System.out.print("Apakah pelanggan VIP? (y/n): ");
        boolean isVIP = scanner.nextLine().equalsIgnoreCase("y");

        int estimatedTime = calculateEstimatedTime(service);
        Customer customer = new Customer(getNextQueueNumber(), name, service, isVIP, estimatedTime);
                serviceQueues.get(service).offer(customer);
        
                System.out.println("Nomor Antrian Anda: " + customer.getNumber());
                System.out.println("Estimasi Waktu Tunggu: " + estimatedTime + " menit");
                saveQueueToFile();
            }
        
            private static int getNextQueueNumber() {
                return Customer.getNextQueueNumber();
            }            
        
            private static void callQueue() {
        for (String service : serviceQueues.keySet()) {
            Queue<Customer> queue = serviceQueues.get(service);
            if (!queue.isEmpty()) {
                Customer nextCustomer = queue.poll();
                System.out.println("Memanggil: " + nextCustomer);
                
                dailyStatistics.put(service, dailyStatistics.getOrDefault(service, 0) + 1);
                saveQueueToFile();
                return;
            }
        }
        System.out.println("Tidak ada antrian saat ini.");
    }

    private static void displayQueue() {
        for (String service : serviceQueues.keySet()) {
            Queue<Customer> queue = serviceQueues.get(service);
            System.out.println("\n=== Antrian " + service + " ===");
            if (queue.isEmpty()) {
                System.out.println("Kosong");
            } else {
                for (Customer c : queue) {
                    System.out.println(c);
                }
            }
        }
    }

    private static void displayStatistics() {
        System.out.println("\n=== Statistik Harian ===");
        for (String service : dailyStatistics.keySet()) {
            int count = dailyStatistics.get(service);
            System.out.println(service + ": " + count + " pelanggan");
        }
    }

    private static int calculateEstimatedTime(String service) {
        int totalTime = 0;
        for (Customer c : serviceQueues.get(service)) {
            totalTime += serviceTimes.get(service);
        }
        return totalTime + serviceTimes.get(service);
    }

    private static void saveQueueToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String service : serviceQueues.keySet()) {
                for (Customer c : serviceQueues.get(service)) {
                    writer.write(c.toString());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Gagal menyimpan data antrian!");
        }
    }

    private static void loadQueueFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Customer customer = Customer.fromString(line);
                serviceQueues.get(customer.getServiceType()).offer(customer);
            }
            System.out.println("Data antrian berhasil dimuat.");
        } catch (IOException e) {
            System.out.println("Gagal memuat data antrian!");
        }
    }
}
