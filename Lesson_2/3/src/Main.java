public class Main {
    public static void main(String[] args) {
        System.out.println("Begin");

        ILineStep Body = new CreatureOfBody();
        ILineStep Engine = new CreatureOfEngine();
        ILineStep Tower = new CreatureOfTower();

        Assembly line = new Assembly((CreatureOfBody) Body,(CreatureOfEngine) Engine,(CreatureOfTower) Tower);
        Tank tank = (Tank) line.assembleProduct(new Tank());
    }
}

