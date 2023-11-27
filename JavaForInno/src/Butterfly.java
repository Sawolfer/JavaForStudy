import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Butterfly extends Insect implements OrthogonalMoving{

    int step = 1;
    int value;
    public Butterfly(EntityPosition position, InsectColor color){
        super(position, color);
    }

    @Override
    public void getBestDirection(Map<String, BoardEntity> boardData, int boardSize) {
        Map <Integer, Direction> values = new HashMap<>();
        ArrayList<Integer> maxValues = new ArrayList<>();
        Direction bestDirection;
        int key;
        key = getOrthogonalDirectionVisibleValue(Direction.N, this.entityPosition, boardData, boardSize);
        values.put(key, Direction.N);
        maxValues.add(key);
        key = getOrthogonalDirectionVisibleValue(Direction.E, this.entityPosition, boardData, boardSize);
        values.put(key, Direction.E);
        maxValues.add(key);
        key = getOrthogonalDirectionVisibleValue(Direction.S, this.entityPosition, boardData, boardSize);
        values.put(key, Direction.S);
        maxValues.add(key);
        key = getOrthogonalDirectionVisibleValue(Direction.W, this.entityPosition, boardData, boardSize);
        values.put(key, Direction.W);
        maxValues.add(key);

        key = Collections.max(maxValues);
        bestDirection = values.get(key);
        if (key == 0){
            travelDirection(Direction.N, boardData, boardSize);
        } else {
            travelDirection(bestDirection, boardData, boardSize);
        }
    }

    @Override
    public void travelDirection(Direction direction, Map<String, BoardEntity> boardData, int boardSize) {
        switch (direction) {
            case N:
                value = travelOrthogonally(Direction.N, this.entityPosition, this.color, boardData, boardSize);
                break;
            case E:
                value = travelOrthogonally(Direction.E, this.entityPosition, this.color, boardData, boardSize);
                break;
            case S:
                value = travelOrthogonally(Direction.S, this.entityPosition, this.color, boardData, boardSize);
                break;
            case W:
                value = travelOrthogonally(Direction.W, this.entityPosition, this.color, boardData, boardSize);
                break;
        }
        String letter;
        letter = InsectColor.colorToString(color) + " " + getClass().getName() + " " + direction.getTextRepresentation() + " " + value;
        Main.write(letter);
    }

    @Override
    public int getOrthogonalDirectionVisibleValue(Direction dir, EntityPosition entityPosition, Map<String, BoardEntity> boardData, int boardSize) {
        int biggestValue=0;
        String key;
        int value;
        int x = entityPosition.x;
        int y = entityPosition.y;
        switch (dir){
            case N:
                for (int i = x; i > 0; i-=step) {
                    key = i + "." + y;
                    if (boardData.get(key) instanceof FoodPoint) {
                        value = ((FoodPoint)boardData.get(key)).getValue();
                        if (value >= biggestValue) {
                            biggestValue = value;
                        }
                    }
                }
                break;
            case E:
                for (int i = y; i <= boardSize; i+=step) {
                    key = x + "." + i;
                    if (boardData.get(key) instanceof FoodPoint) {
                        value = ((FoodPoint)boardData.get(key)).getValue();
                        if (value >= biggestValue) {
                            biggestValue = value;
                        }
                    }
                }
                break;
            case S:
                for (int i = x; i <= boardSize; i+=step) {
                    key = i + "." + y;
                    if (boardData.get(key) instanceof FoodPoint) {
                        value = ((FoodPoint)boardData.get(key)).getValue();
                        if (value >= biggestValue) {
                            biggestValue = value;
                        }
                    }
                }
                break;
            case W:
                for (int i = y; i > 0; i-=step) {
                    key = x + "." + i;
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
    public int travelOrthogonally(Direction dir, EntityPosition entityPosition, InsectColor color, Map<String, BoardEntity> boardData, int boardSize) {
        int x = this.entityPosition.x;
        int y = this.entityPosition.y;
        String thisKey = x + "." + y;
        String key;
        switch (dir){
            case N:
                while (x > 0) {
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
            case E:
                while (y < boardSize) {
                    y += step;
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
            case S:
                while (x < boardSize) {
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
            case W:
                while (y > 0) {
                    y -= step;
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
