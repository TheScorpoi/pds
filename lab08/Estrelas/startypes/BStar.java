package Estrelas.startypes;
import java.awt.Color;

public class BStar extends StarType{
    
    public BStar(int x, int y) {
        super(5, new Color(230, 252, 252), x, y);
        this.description = "This is a long description of the B type star....";
        // This would store the values of physical properties for each type of star
        this.physicalProperties = new Float[10];
    }
}
