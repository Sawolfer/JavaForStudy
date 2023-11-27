import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Spider extends Insect implements DiagonalMoving{

    int step = 1;
    int value;
    public Spider(EntityPosition position, InsectColor color){
        super(position, color);
    }

    @Override
    public void getBestDirection(Map<String, BoardEntity> boardData, int boardSize) {
        Map <Integer, Direction> values = new HashMap<>();
        ArrayList<Integer> maxValues = new ArrayList<>();
        Direction bestDirection;
        int key;

        key = getDiagonalDirectionVisibleValue(Direction.NE, this.entityPosition, boardData, boardSize);
        values.put(key, Direction.NE);
        maxValues.add(key);
        key = getDiagonalDirectionVisibleValue(Direction.SE, this.entityPosition, boardData, boardSize);
        values.put(key, Direction.SE);
        maxValues.add(key);
        key = getDiagonalDirectionVisibleValue(Direction.SW, this.entityPosition, boardData, boardSize);
        values.put(key, Direction.SW);
        maxValues.add(key);
        key = getDiagonalDirectionVisibleValue(Direction.NW, this.entityPosition, boardData, boardSize);
        values.put(key, Direction.NW);
        maxValues.add(key);

        key = Collections.max(maxValues);
        bestDirection = values.get(key);
        if (key == 0){
            travelDirection(Direction.NE, boardData, boardSize);
        } else {
            travelDirection(bestDirection, boardData, boardSize);
        }
    }

    @Override
    public void travelDirection(Direction direction, Map<String, BoardEntity> boardData, int boardSize) {
        switch (direction) {
            case NE:
                value = travelDiagonally(Direction.NE, this.entityPosition, this.color, boardData, boardSize);
                break;
            case SE:
                value = travelDiagonally(Direction.SE, this.entityPosition, this.color, boardData, boardSize);
                break;
            case SW:
                value = travelDiagonally(Direction.SW, this.entityPosition, this.color, boardData, boardSize);
                break;
            case NW:
                value = travelDiagonally(Direction.NW, this.entityPosition, this.color, boardData, boardSize);
                break;
        }
        String letter;
        letter = InsectColor.colorToString(color) + " " + getClass().getName() + " " + direction.getTextRepresentation() + " " + value;
        Main.write(letter);
    }

    @Override
    public int getDiagonalDirectionVisibleValue(Direction dir, EntityPosition entityPosition, Map<String, BoardEntity> boardData, int boardSize) {
        int biggestValue=0;
        String key;
        int value;
        int x = this.entityPosition.x;
        int y = this.entityPosition.y;
        switch (dir){
            case NE:
                while (x > 0 && y < boardSize){
                    x -= step;
                    y += step;
                    key = x + "." + y;
                    if (boardData.get(key) instanceof FoodPoint) {
                        value = ((FoodPoint)boardData.get(key)).getValue();
                        if (value >= biggestValue) {
                            biggestValue = value;
                        }
                    }
                }
                break;
            case SE:
                while (x < boardSize && y < boardSize){
                    x += step;
                    y += step;
                    key = x + "." + y;
                    if (boardData.get(key) instanceof FoodPoint) {
                        value = ((FoodPoint)boardData.get(key)).getValue();
                        if (value >= biggestValue) {
                            biggestValue = value;
                        }
                    }
                }
                break;
            case SW:
                while (x < boardSize && y > 0){
                    x += step;
                    y -= step;
                    key = x + "." + y;
                    if (boardData.get(key) instanceof FoodPoint) {
                        value = ((FoodPoint)boardData.get(key)).getValue();
                        if (value >= biggestValue) {
                            biggestValue = value;
                        }
                    }
                }
                break;
            case NW:
                while (x > 0 && y > 0){
                    x -= step;
                    y -= step;
                    key = x + "." + y;
                    if (boardData.get(key) instanceof FoodPoint) {
                        value = ((FoodPoint)boardData.get(key)).getValue();
                        if (value >= biggestValue) {
                            biggestValue = value;
                        }
                    }
                }
                break;
        }
        return biggestValue;
    }

    @Override
    public int travelDiagonally(Direction dir, EntityPosition entityPosition, InsectColor color, Map<String, BoardEntity> boardData, int boardSize) {
        int x = this.entityPosition.x;
        int y = this.entityPosition.y;
        String thisKey = x + "." + y;
        String key;
        switch (dir){
            case NE:
                while (y < boardSize && x > 0) {
                    y += step;
                    x -= step;
                    key = x + "." + y;
                    if (boardData.get(key) == null) {
                        continue;
                    } else if (boardData.get(key) instanceof FoodPoint) {
                        value += ((FoodPoint)boardData.get(key)).getValue();
                        boardData.remove(key);
                    } else if (boardData.get(key) instanceof Insect){
                        if (this.color == ((Insect)boardData.get(key)).getColor()) {
                            continue;
                        }
                        else {
                            boardData.remove(thisKey);
                            return value;
                        }
                    }
                }
                boardData.remove(thisKey);
                break;
            case SE:
                while (y < boardSize && x < boardSize) {
                    y += step;
                    x += step;
                    key = x + "." + y;
                    if (boardData.get(key) == null) {
                        continue;
                    } else if (boardData.get(key) instanceof FoodPoint) {
                        value += ((FoodPoint)boardData.get(key)).getValue();
                        boardData.remove(key);
                    } else if (boardData.get(key) instanceof Insect){
                        if (this.color == ((Insect)boardData.get(key)).getColor()) {
                            continue;
                        }
                        else {
                            boardData.remove(thisKey);
                            return value;
                        }
                    }
                }
                boardData.remove(thisKey);
                break;
            case SW:
                while (y > 0 && x < boardSize) {
                    y -= step;
                    x += step;
                    key = x + "." + y;
                    if (boardData.get(key) == null) {
                        continue;
                    } else if (boardData.get(key) instanceof FoodPoint) {
                        value += ((FoodPoint)boardData.get(key)).getValue();
                        boardData.remove(key);
                    } else if (boardData.get(key) instanceof Insect){
                        if (this.color == ((Insect)boardData.get(key)).getColor()) {
                            continue;
                        }
                        else {
                            boardData.remove(thisKey);
                            return value;
                        }
                    }
                }
                boardData.remove(thisKey);
                break;
            case NW:
                while (y > 0 && x > 0) {
                    y -= step;
                    x -= step;
                    key = x + "." + y;
                    if (boardData.get(key) == null) {
                        continue;
                    } else if (boardData.get(key) instanceof FoodPoint) {
                        value += ((FoodPoint)boardData.get(key)).getValue();
                        boardData.remove(key);
                    } else if (boardData.get(key) instanceof Insect){
                        if (this.color == ((Insect)boardData.get(key)).getColor()) {
                            continue;
                        }
                        else {
                            boardData.remove(thisKey);
                            return value;
                        }
                    }
                }
                boardData.remove(thisKey);
                break;
        }
        return value;
    }

}
