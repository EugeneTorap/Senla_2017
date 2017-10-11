class CreatureOfEngine implements ILineStep {
    public Engine buildProductPart() {
        System.out.println("Engine is built");
        return new Engine();
    }
}
