interface CakeBuilder {
    public void setCakeShape(Shape shape);

    public void addCakeLayer();

    public void addCreamLayer();

    public void addTopLayer();

    public void addTopping();

    public void addMessage(String m);

    public void createCake();

    public Cake getCake();

}

class ChocolateCakeBuilder implements CakeBuilder {

    protected Cake cake;

    @Override
    public void setCakeShape(Shape shape) {
        cake.setShape(Shape.CIRCULAR);
    }

    @Override
    public void addCakeLayer() {
        cake.setCakeLayer("Soft Chocolate");
        cake.setNumCakeLayers(cake.getNumCakeLayers() + 1);
    }

    @Override
    public void addCreamLayer() {
        cake.setMidLayerCream(Cream.CHOCOLATE);
    }

    @Override
    public void addTopLayer() {
        cake.setTopLayerCream(Cream.OVOSMOLES);
    }

    @Override
    public void addTopping() {
        cake.setTopping(Topping.VANILLA);
    }

    @Override
    public void addMessage(String m) {
        cake.setMessage(m);
    }

    @Override
    public Cake getCake() {
        return cake;
    }

    @Override
    public void createCake() {
        cake = new Cake();
    }

}

class SpongeCakeBuilder implements CakeBuilder {

    protected Cake cake;

    @Override
    public void setCakeShape(Shape shape) {
        cake.setShape(shape);
    }

    @Override
    public void addCakeLayer() {
        cake.setCakeLayer("Sponge");
        cake.setNumCakeLayers(cake.getNumCakeLayers() + 1);
    }

    @Override
    public void addCreamLayer() {
        cake.setMidLayerCream(Cream.CARAMEL);
    }

    @Override
    public void addTopLayer() {
        cake.setTopLayerCream(Cream.STRAWBERRY);
    }

    @Override
    public void addTopping() {
        cake.setTopping(Topping.CHOCOLATE);
    }

    @Override
    public void addMessage(String m) {
        cake.setMessage(m);
    }

    @Override
    public Cake getCake() {
        return cake;
    }

    @Override
    public void createCake() {
        cake = new Cake();
    }

}

class YogurtCakeBuilder implements CakeBuilder {

    protected Cake cake;

    @Override
    public void setCakeShape(Shape shape) {
        cake.setShape(shape);
    }

    @Override
    public void addCakeLayer() {
        cake.setCakeLayer("Yogurt");
        cake.setNumCakeLayers(cake.getNumCakeLayers() + 1);
    }

    @Override
    public void addCreamLayer() {
        cake.setMidLayerCream(Cream.OVOSMOLES);
    }

    @Override
    public void addTopLayer() {
        cake.setTopLayerCream(Cream.CARAMEL);
    }

    @Override
    public void addTopping() {
        cake.setTopping(Topping.VANILLA);

    }

    @Override
    public void addMessage(String m) {
        cake.setMessage(m);
    }

    @Override
    public Cake getCake() {
        return cake;
    }

    @Override
    public void createCake() {
        cake = new Cake();
    }
}