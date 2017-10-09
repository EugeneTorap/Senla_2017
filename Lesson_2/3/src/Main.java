public class Main {
    public static void main(String[] args) {
        Tank tank = new Tank();
        System.out.println("Begin");
        Assembly line = new Assembly(new CreatureOfBody(), new CreatureOfEngine(),new CreatureOfTower());
        tank =(Tank) line.assembleProduct(tank);
    }
}

interface IAssemblyLine{
    IProduct assembleProduct(IProduct iProduct);
}

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

interface ILineStep {
    IProductPart buildProductPart();
}

class CreatureOfBody implements ILineStep{
    public Body buildProductPart() {
        System.out.println("Body is built");
        return new Body();
    }
}

class CreatureOfEngine implements ILineStep {
    public Engine buildProductPart() {
        System.out.println("Engine is built");
        return new Engine();
    }
}

class CreatureOfTower implements ILineStep{
    public Tower buildProductPart() {
        System.out.println("Tower is built");
        return new Tower();
    }
}

interface IProductPart {
}

class Body implements IProductPart{
    public Body() {
        System.out.println("Body is made");
    }
}

class Engine implements IProductPart{
    public Engine() {
        System.out.println("Engine is made");
    }
}

class Tower implements IProductPart{
    public Tower() {
        System.out.println("Tower is made");
    }
}

interface IProduct {
    void installFirstPart(IProductPart productPart);
    void installSecondPart(IProductPart productPart);
    void installThirdPart(IProductPart productPart);
}

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
