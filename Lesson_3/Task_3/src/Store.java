public class Store {
    private int amountOfKindFruits;
    private Goods[] goods;

    public Store(int amountOfKindFruits, Goods[] goods) {
        this.amountOfKindFruits = amountOfKindFruits;
        this.goods = new Goods[amountOfKindFruits];
    }

    public void getWeightOfGoods(Goods[] goods){
        for (int i = 0; i < getAmountOfKindFruits(); i++) {
            System.out.println(goods[i].getName() + " - " + goods[i].getWeight() * goods[i].getAmount() + " gram");
        }
    }

    public int getAmountOfKindFruits() {
        return amountOfKindFruits;
    }

    public void setAmountOfKindFruits(int amountOfKindFruits) {
        this.amountOfKindFruits = amountOfKindFruits;
    }

    public Goods[] getGoods() {
        return goods;
    }

    public void setGoods(Goods[] goods) {
        this.goods = goods;
    }
}
