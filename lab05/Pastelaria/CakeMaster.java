public class CakeMaster {
    private CakeBuilder cakeBuilder;

    public void setCakeBuilder(CakeBuilder cb) {
        cakeBuilder = cb;
    }

    public Cake getCake() {
        return cakeBuilder.getCake();
    }

    public void createCake(String message) {
        cakeBuilder.createCake();
        cakeBuilder.setCakeShape(Shape.CIRCULAR);
        cakeBuilder.addCakeLayer();
        cakeBuilder.addCreamLayer();
        cakeBuilder.addTopLayer();
        cakeBuilder.addTopping();
        cakeBuilder.addMessage(message);
    }

    public void createCake(Shape shape, int i, String message) {
        cakeBuilder.createCake();
        cakeBuilder.setCakeShape(shape);
        for (int j = 0; j < i; j++) {
            cakeBuilder.addCakeLayer();
            cakeBuilder.addCreamLayer();
        }
        cakeBuilder.addTopLayer();
        cakeBuilder.addTopping();
        cakeBuilder.addMessage(message);
    }

    public void createCake(int i, String m) {
        cakeBuilder.createCake();
        cakeBuilder.setCakeShape(Shape.CIRCULAR);
        for (int j = 0; j < i; j++) {
            cakeBuilder.addCakeLayer();
            cakeBuilder.addCreamLayer();
        }
        cakeBuilder.addTopLayer();
        cakeBuilder.addTopping();
        cakeBuilder.addMessage(m);
    }

}
