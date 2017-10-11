class CreatureOfTower implements ILineStep{
    public Tower buildProductPart() {
        System.out.println("Tower is built");
        return new Tower();
    }
}
