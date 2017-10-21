public class Store {

    private Product[] products;

    public Store(int amountOfFruits) {
        this.products = new Product[amountOfFruits];
    }

    public int getPosition(Product[] products){
        for (int i = 0; i < products.length; i++) {
            if (products[i] == null)
                return i;
        }
        return -1;
    }

    public void addProduct(Product newProduct){
        if (getPosition(products) != -1) {
            int position = getPosition(products);
            products[position] = newProduct;
            return;
        }
        System.out.println("The store is full");
    }

    public int getWeightOfGoods(){
        if (getPosition(products) == -1){
            int sum = 0;
            for (Product good : products) {
                sum += good.getWeight();
            }
            return sum;
        }
        return -1;
    }
}
