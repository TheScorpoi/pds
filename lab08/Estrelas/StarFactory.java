package Estrelas;

import java.util.HashMap;
import java.util.Map;

import Estrelas.startypes.*;
import Estrelas.startypes.StarType;

public class StarFactory {

    static int CANVAS_SIZE = 1200;

    static Map<Character, StarType> starTypesMap = new HashMap<>();

    public static StarType getStarType(char type){
        
        StarType result = starTypesMap.get(type);

        if (result == null) {

            int y = random(0, CANVAS_SIZE);
            int x = random(0, CANVAS_SIZE);
            switch (type) {
                case 'O':
                    result = new OStar(x, y);
                    break;
                case 'A':
                    result = new AStar(x, y);
                    break;
                case 'B':
                    result = new BStar(x, y);
                    break;
                case 'F':
                    result = new FStar(x, y);
                    break;
                case 'G':
                    result = new GStar(x, y);
                    break;
                case 'K':
                    result = new KStar(x, y);
                    break;
                case 'M':
                    result = new MStar(x, y);
                    break;

            }
            starTypesMap.put(type, result);
        }
        return result;
    }
    
    private static int random(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }
}



