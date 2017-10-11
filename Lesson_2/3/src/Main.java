public class Main {
    public static void main(String[] args) {
        System.out.println("Begin");
        Assembly line = new Assembly(new CreatureOfBody(), new CreatureOfEngine(),new CreatureOfTower());
        Tank tank = (Tank) line.assembleProduct(new Tank());
    }
}

