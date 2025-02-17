import java.util.*;

class Pet {
    private String name;
    private String type;
    private int health;
    private int hunger;
    private int happiness;
    private int level;
    private static final String[] EVOLUTION_STAGES = {"Bayi", "Remaja", "Dewasa", "Raksasa", "Legenda", "Raja Iblis 😈"};

    public Pet(String name, String type) {
        this.name = name;
        this.type = type;
        this.health = 100;
        this.hunger = 50;
        this.happiness = 50;
        this.level = 1;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getHunger() {
        return hunger;
    }

    public int getHappiness() {
        return happiness;
    }

    public int getLevel() {
        return level;
    }

    public String getStage() {
        return EVOLUTION_STAGES[level - 1];
    }

    public void feed(int hungerReduction) {
        if (hunger <= 10) {
            System.out.println(name + " sudah kenyang!");
        } else {
            hunger -= hungerReduction;
            happiness += 5;
            checkEvolution();
            System.out.println(name + " makan dengan lahap! Hunger -" + hungerReduction + ", Happines +5");
        }
    }

    public void play() {
        if (happiness >= 90) {
            System.out.println(name + " sudah sangat bahagia!");
        } else if (hunger > 80) {
            System.out.println(name + " terlalu lapar untuk bermain!");
        } else {
            happiness += 10;
            hunger += 5;
            checkEvolution();
            System.out.println(name + " bermain dengan ceria! Happines +10, Hunger +5");
        }
    }

    public void heal() {
        if (health >= 100) {
            System.out.println(name + " sudah dalam kondisi sehat!");
        } else {
            health = 100;
            System.out.println(name + " telah sembuh sepenuhnya!");
        }
    }

    private void checkEvolution() {
        if (happiness > 80 && hunger < 30 && level < 5) {
            level++;
            System.out.println("✨ " + name + " telah berevolusi ke tahap **" + getStage() + "**! ✨");
        }
    }

    public void decreaseStatus() {
        hunger += 5;
        happiness -= 5;
        if (hunger >= 100) {
            health -= 20;
            System.out.println(name + " sangat kelaparan Health -20");
        }
        if (happiness <= 10) {
            health -= 10;
            System.out.println(name + " merasa kesepian! Health -10");
        }
        if (health <= 0) {
            System.out.println("⚠️ " + name + " telah mati... 😵");
        }
    }

    @Override
    public String toString() {
        return "Nama: " + name + " | Jenis: " + type + " | Evolusi: " + getStage() + " | Kesehatan: " + health + " | Kelaparan: " + hunger + " | Kebahagian: " + happiness;
    }
}

class PetManager {
    private List<Pet> pets;
    private int coins;

    public PetManager() {
        pets = new ArrayList<>();
        coins = 20;
    }

    public void earnCoins() {
        coins += 5;
    }

    public void adoptPet(String name, String type) {
        pets.add(new Pet(name, type));
        System.out.println(name + " telah diadopsi!");
    }

    public void displayPets() {
        if (pets.isEmpty()) {
            System.out.println("Belum ada hewan peliharaan.");
        } else {
            System.out.println("\nDaftar Hewan Peliharaan:");
            for (Pet pet : pets) {
                System.out.println(pet);
            }
        }
    }

    public Pet findPet(String name) {
        for (Pet pet : pets) {
            if (pet.getName().equalsIgnoreCase(name)) {
                return pet;
            }
        }
        return null;
    }

    public boolean buyFood(int cost) {
        if (coins >= cost) {
            coins -= cost;
            System.out.println("Kamu membeli makanan! Koin tersisa: " + coins);
            return true;
        } else {
            System.out.println("Uang tidak cukup!");
            return false;
        }
    }

    public int getCoins() {
        return coins;
    }

    public void removeDeadPets() {
        pets.removeIf(pet -> pet.getHealth() <= 0);
    }

    public void decreaseAllPetsStatus() {
        for ( Pet pet : pets) {
            pet.decreaseStatus();
        }
        removeDeadPets();
    }
}

public class VirtualPetGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PetManager petManager = new PetManager();
        Timer timer = new Timer();

        // Timer untuk status berkurang dan koin bertambah otomatis
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                petManager.decreaseAllPetsStatus();
                petManager.earnCoins();
                System.out.println("💰 Kamu mendapatkan 5 koin! Koin saat ini: " + petManager.getCoins());
            }
        }, 0, 30000);

        while (true) {
            System.out.println("\n=== Sistem Manajemen Hewan Peliharaan Virtual ===");
            System.out.println("1. Adopsi Hewan");
            System.out.println("2. Lihat Hewan");
            System.out.println("3. Beri Makan");
            System.out.println("4. Bermain");
            System.out.println("5. Sembuhkan");
            System.out.println("6. Beli Makanan");
            System.out.println("7. Keluar");
            System.out.print("Pilih menu: ");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); 
            } catch (InputMismatchException e) {
                System.out.println("Input tidak valid! Harap masukkan angka.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Masukkan nama hewan: ");
                    String name = scanner.nextLine();
                    System.out.print("Masukkan jenis hewan: ");
                    String type = scanner.nextLine();
                    petManager.adoptPet(name, type);
                    break;

                case 2:
                    petManager.displayPets();
                    break;

                case 3:
                    System.out.print("Masukkan nama hewan yang ingin diberi makan: ");
                    String petName = scanner.nextLine();
                    Pet petToFeed = petManager.findPet(petName);
                    if (petToFeed != null) {
                        petToFeed.feed(15);
                    } else {
                        System.out.println("Hewan tidak ditemukan!");
                    }
                    break;

                case 6:
                    System.out.println("Harga makanan: 10 koin");
                    if (petManager.buyFood(10)) {
                        System.out.println("Makanan bisa digunakan saat memberi makan hewan!");
                    }
                    break;

                case 7:
                    System.out.println("Terima kasih telah bermain!");
                    scanner.close();
                    timer.cancel();
                    return;

                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }
}