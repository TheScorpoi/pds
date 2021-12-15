package lab02.T2.lab01;

public class WordFound {
    String word;
    int length, x, y,number,overlapCounter;
    Direction direction;
    
    public WordFound(String word, int length, int x, int y, Direction direction, int number) {
        this.word = word;
        this.length = length;
        this.x = x+1;
        this.y = y+1;
        this.direction = direction;
        this.number = number;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getNumber() {
        return number;
    }

    public void setWord(int number) {
        this.number = number;
    }
    
    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return String.format("%-15s\t%-5d\t%d,%d\t%-10s",word,length,x,y,direction);
    }
}