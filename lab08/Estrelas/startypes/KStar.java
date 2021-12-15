package Estrelas.startypes;
import java.awt.Color;

public class KStar extends StarType{
    
    public KStar(int x, int y) {
        super(1, new Color(230, 160, 10), x, y);
        this.description = "This is a long description of the K type star....";
        // This would store the values of physical properties for each type of star
        this.physicalProperties = new Float[10];
    }
}
