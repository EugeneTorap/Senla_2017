public class Main {
    public static void main(String[] args) {
        Apple apple = new Apple("Apple", "Green", 4, 100, 2);
        Melon melon = new Melon("Melon", "Yellow", 8, 800, 4);
        Pear pear = new Pear("Pear", "Yellow", 5, 150, 5);

        Goods[] goods = {
                apple, melon, pear
        };

        Store store = new Store(3, goods);
        store.getWeightOfGoods(goods);
    }
}
