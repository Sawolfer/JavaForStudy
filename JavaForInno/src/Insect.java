import apple.laf.JRSUIConstants;

import java.awt.*;
import java.util.Map;

public class Insect extends BoardEntity {

    protected InsectColor color;

    public Insect(EntityPosition position, InsectColor color) {
        this.entityPosition = new EntityPosition(position.x, position.y);
        this.color = color;
    }

    public void getBestDirection(Map<String, BoardEntity> boardData, int boardSize) {

    }

    public void travelDirection(Direction direction, Map<String, BoardEntity> boardData, int boardSize) {

    }

    public InsectColor getColor() {
        return color;
    }
    public Class getTypeOfInsect(){
        return this.getClass();
    }
}