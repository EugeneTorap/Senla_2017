public class Main {
    public static void main(String[] args) {
        //Tank tank = new Tank();
        System.out.println("Begin");
        Assembly line = new Assembly(new CreatureOfBody(), new CreatureOfEngine(),new CreatureOfTower());
        Tank tank = line.assembleProduct(new Tank);
    }
}

