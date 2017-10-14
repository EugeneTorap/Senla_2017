public class Random {
    public static void main(String[] args) {
        int number, sum = 0;

        for (int i = 1; i < 4; i++) {
            number  = 100 + new java.util.Random().nextInt(999 - 100 + 1);
            sum += number / 100;
            System.out.println("Number_" + i + ": " + number);
        }
        System.out.println("Sum: " + sum);
    }
}
