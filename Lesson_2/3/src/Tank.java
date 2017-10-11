class Tank implements IProduct{
    public void installFirstPart(IProductPart body) {
        System.out.println("Body is installed");
    }
    public void installSecondPart(IProductPart bow) {
        System.out.println("Engine is installed");
    }
    public void installThirdPart(IProductPart lens) {
        System.out.println("Tower is installed");
    }
}
