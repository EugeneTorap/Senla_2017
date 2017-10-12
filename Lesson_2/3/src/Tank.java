class Tank implements IProduct{
    private IProductPart Body;
    private IProductPart Engine;
    private IProductPart Tower;

    public void installFirstPart(IProductPart Body) {
        this.Body = Body;
        System.out.println("Body is installed");
    }
    public void installSecondPart(IProductPart Engine) {
        this.Engine = Engine;
        System.out.println("Engine is installed");
    }
    public void installThirdPart(IProductPart Tower) {
        this.Tower = Tower;
        System.out.println("Tower is installed");
    }
}
