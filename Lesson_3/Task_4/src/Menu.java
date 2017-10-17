public class Menu {
    public static void showAllReader(Reader[] readers){
        System.out.println("List of all reader: \n");
        for (Reader i: readers) {
            System.out.println(i.getName());
        }
    }
}
