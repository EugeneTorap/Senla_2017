import java.util.Scanner;

public class ArraySort {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Input a line: ");
        String line = in.next().toLowerCase();

        char[] charArray = line.toCharArray();
        for (int i = 0; i < line.length(); i++) {
            for (int j = 0; j < line.length(); j++) {
                if(charArray[i] < charArray[j]){
                    char temp = charArray[i];
                    charArray[i] = charArray[j];
                    charArray[j] = temp;
                }
            }
        }
        System.out.println(charArray);
    }
}
