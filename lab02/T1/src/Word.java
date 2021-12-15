package lab02.T1.src;

public class Word {
    private String name;
    private Direction dir;
    private int len;
    private int x, y;

    public Word(String name, Direction dir, int len, int x, int y) {
        this.name = name;
        this.dir = dir;
        this.len = len;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public Direction getDir() {
        return dir;
    }

    public int getLen() {
        return len;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
