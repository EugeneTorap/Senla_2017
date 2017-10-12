class Tank implements IProduct{

    private Body bodyProduct;
    private Engine engineProduct;
    private Tower towerProduct;

    public void installFirstPart(IProductPart body) {
        bodyProduct = (Body) body;
        System.out.println("Body is installed");
    }

    public void installSecondPart(IProductPart engine) {
        engineProduct = (Engine) engine;
        System.out.println("Engine is installed");
    }

    public void installThirdPart(IProductPart tower) {
        towerProduct = (Tower) tower;
        System.out.println("Tower is installed");
    }
}
