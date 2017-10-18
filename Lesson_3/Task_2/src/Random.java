public class Random {
    public static void main(String[] args) {
        int[] number = new int[3];

        for (int i = 0; i < 3; i++) {
            number[i] = randomNumber();
            System.out.println("Number_" + (i+1) + ": " + number[i]);
        }
        System.out.println("Sum: " + sum(number));
    }

    private static int randomNumber(){
        return 100 + new java.util.Random().nextInt(999 - 100 + 1);
    }

    private static int sum(int number[]){
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            sum += number[i] / 100;
        }
        return sum;
    }
}
