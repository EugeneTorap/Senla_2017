class Assembly implements IAssemblyLine{

    private ILineStep Body;
    private ILineStep Engine;
    private ILineStep Tower;

    public Assembly(CreatureOfBody Body,CreatureOfEngine Engine, CreatureOfTower Tower){
        this.Body = Body;
        this.Engine = Engine;
        this.Tower = Tower;
    }

    public IProduct assembleProduct(IProduct iProduct) {
        iProduct.installFirstPart(Body.buildProductPart());
        iProduct.installSecondPart(Engine.buildProductPart());
        iProduct.installThirdPart(Tower.buildProductPart());
        System.out.println("Tank is assembled");
        return iProduct;
    }
}
