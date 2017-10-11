class CreatureOfBody implements ILineStep{
    public Body buildProductPart() {
        System.out.println("Body is built");
        return new Body();
    }
}
