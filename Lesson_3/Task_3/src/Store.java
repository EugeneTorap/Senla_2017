public class Store {
    private int amountOfFruits;
    private Goods[] goods;
    private Boolean isStoreFull;

    public Store(int amountOfFruits) {
        this.amountOfFruits = amountOfFruits;
        this.goods = new Goods[0];
        isStoreFull = false;
    }

    public void addGoods(Goods newGoods){
        if (goods.length != amountOfFruits) {
            Goods[] newReaders = new Goods[goods.length + 1];
            System.arraycopy(goods, 0, newReaders, 0, goods.length);
            newReaders[goods.length] = newGoods;
            goods = newReaders;
            if (goods.length == amountOfFruits){
                isStoreFull = true;
                System.out.println("The store is full");
            }
        }
    }

    public Goods[] getGoods() {
        return goods;
    }

    public void setGoods(Goods[] goods) {
        this.goods = goods;
    }

    public int getWeightOfGoods(){
        if (isStoreFull){
            int sum = 0;
            for (Goods good : goods) {
                sum += good.getWeight();
            }
            return sum;
        }
        return -1;
    }
}
