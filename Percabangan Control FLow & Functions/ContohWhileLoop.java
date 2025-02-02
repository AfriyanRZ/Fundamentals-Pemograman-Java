public class ContohWhileLoop {
    public static void main(String[] args) {
        int counter = 0;

        while (counter < 5) {
            System.out.println("Counter: " + counter);
            counter++;
        }

        //  Do-While loop
        int i = 0;
        do { 
            System.out.println("Nilai i: " + i);
            i++;
        } while (i < 3);
    }
}
