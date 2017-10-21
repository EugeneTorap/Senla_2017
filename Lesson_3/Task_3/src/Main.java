public class Main {
    public static void main(String[] args) {
        Apple apple = new Apple("Apple", 100);
        Melon melon = new Melon("Melon", 800);
        Pear pear = new Pear("Pear", 170);

        Store store = new Store(15);
        for (int i = 0; i < 5; i++) {
            store.addProduct(apple);
        }
        for (int i = 0; i < 3; i++) {
            store.addProduct(melon);
        }
        for (int i = 0; i < 8; i++) {
            store.addProduct(pear);
        }

        int weight = store.getWeightOfGoods();
        if (weight != -1)
            System.out.println("Weight of all goods: " + weight + " gram");
        else
            System.out.println("The store isn't full");
    }
}
