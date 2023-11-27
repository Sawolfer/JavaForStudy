import apple.laf.JRSUIConstants;

import java.awt.*;
import java.util.Map;

public class Insect extends BoardEntity {

    protected InsectColor color;
    protected int value;
    public Insect(EntityPosition position, InsectColor color) {
        this.entityPosition = new EntityPosition(position.x, position.y);
        this.color = color;
    }

    public void getBestDirection(Map<String, BoardEntity> boardData, int boardSize) {

    }

    public void travelDirection(Direction direction, Map<String, BoardEntity> boardData, int boardSize) {
        String letter;
        letter = InsectColor.colorToString(color) + " " + getClass().getName() + " " + direction.name() + " " + value;
        Main.write(letter);
    }

    public InsectColor getColor() {
        return color;
    }
    public Class getTypeOfInsect(){
        return this.getClass();
    }

}