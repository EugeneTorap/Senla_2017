public class ArraySort {
    public static void main(String[] args) {
        String[] array = new String[]{"Dog", "Cat", "carpet", "zoo", "city", "Case", "cinema", "Zoo", "apple"};
        for (String i: array) {
            System.out.print(i + ", ");
        }

        sort(array);

        System.out.println("\nArray is sorted:");
        for (String i: array) {
            System.out.print(i + ", ");
        }
    }

    private static String[] sort(String[] array){
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if(array[j].toLowerCase().compareTo(array[i].toLowerCase())>0){
                    String temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }
}
