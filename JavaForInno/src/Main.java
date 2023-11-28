
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    private static InvalidBoardSizeException invalidBSE = new InvalidBoardSizeException();
    private static InvalidNumberOfInsectsException invalidNOIE = new InvalidNumberOfInsectsException();
    private static InvalidNumberOfFoodPointsException invalidNOFPE = new InvalidNumberOfFoodPointsException();
    protected static InvalidInsectColorException invalidICE = new InvalidInsectColorException();
    private static InvalidEntityPositionException invalidEPE = new InvalidEntityPositionException();
    private static InvalidInsectTypeException invalidITE = new InvalidInsectTypeException();
    protected static TwoEntitiesOnSamePositionException tESPE = new TwoEntitiesOnSamePositionException();
    protected static DuplicateInsectException die = new DuplicateInsectException();
    private static Board gameBoard;
    private static BufferedWriter bw;
    private static BufferedReader br;
    private static int d;
    private static int n;
    private static int m;

    private static final int LOWER_BOUND_D = 4;
    private static final int UPPER_BOUND_D = 1000;
    private static final int LOWER_BOUND_N = 1;
    private static final int UPPER_BOUND_N = 16;
    private static final int LOWER_BOUND_M = 1;
    private static final int UPPER_BOUND_M = 200;
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;

    private static List<Insect> insects = new ArrayList<>();

    public static void main(String[] args) {
        try {
            bw = new BufferedWriter(new FileWriter("src/output.txt"));
            br = new BufferedReader(new FileReader("src/input.txt"));
            String currentString;
            String strD = br.readLine();
            String strN = br.readLine();
            String strM = br.readLine();
            try {
                d = Integer.parseInt(strD);
                if (d < LOWER_BOUND_D || d > UPPER_BOUND_D) {
                    finish(invalidBSE.getMessage());
                }
                n = Integer.parseInt(strN);
                if (n < LOWER_BOUND_N || n > UPPER_BOUND_N) {
                    finish(invalidNOIE.getMessage());
                }
                m = Integer.parseInt(strM);
                if (m < LOWER_BOUND_M || m > UPPER_BOUND_M) {
                    finish(invalidNOFPE.getMessage());
                }
            } catch (NumberFormatException e) {
                finish();
            }
            gameBoard = new Board(d);
            for (int i = 0; i < n; i++) {
                currentString = br.readLine();
                int tmpX;
                int tmpY;
                EntityPosition position;
                String[] words = currentString.split(" ");
                InsectColor color = InsectColor.toColor(words[ZERO]);
                switch (words[ONE]) {
                    case ("Ant"):
                        try {
                            tmpX = Integer.parseInt(words[TWO]);
                            tmpY = Integer.parseInt(words[THREE]);
                            if (tmpX > d || tmpY > d || tmpY <= 0 || tmpX <= 0) {
                                finish(invalidEPE.getMessage());
                            }
                            position = new EntityPosition(tmpX, tmpY);
                            for (Insect insect : insects) {
                                if (color == insect.getColor() && insect.getTypeOfInsect() == Ant.class) {
                                    finish(die.getMessage());
                                }
                            }
                            Ant newAnt = new Ant(position, color);
                            gameBoard.addEntity(newAnt);
                            insects.add(newAnt);

                        } catch (NumberFormatException e) {
                            finish(invalidEPE.getMessage());
                        }
                        break;
                    case ("Butterfly"):
                        try {
                            tmpX = Integer.parseInt(words[TWO]);
                            tmpY = Integer.parseInt(words[THREE]);
                            if (tmpX > d || tmpY > d || tmpY <= 0 || tmpX <= 0) {
                                finish(invalidEPE.getMessage());
                            }
                            for (Insect insect : insects) {
                                if (color == insect.getColor() && insect.getTypeOfInsect() == Butterfly.class) {
                                    finish(die.getMessage());
                                }
                            }
                            position = new EntityPosition(tmpX, tmpY);
                            Butterfly newButterfly = new Butterfly(position, color);
                            gameBoard.addEntity(newButterfly);
                            insects.add(newButterfly);
                        } catch (NumberFormatException e) {
                            finish(invalidEPE.getMessage());
                        }
                        break;
                    case ("Spider"):
                        try {
                            tmpX = Integer.parseInt(words[TWO]);
                            tmpY = Integer.parseInt(words[THREE]);
                            if (tmpX > d || tmpY > d || tmpY <= 0 || tmpX <= 0) {
                                finish(invalidEPE.getMessage());
                            }
                            for (Insect insect : insects) {
                                if (color == insect.getColor() && insect.getTypeOfInsect() == Spider.class) {
                                    finish(die.getMessage());
                                }
                            }
                            position = new EntityPosition(tmpX, tmpY);
                            Spider newSpider = new Spider(position, color);
                            gameBoard.addEntity(newSpider);
                            insects.add(newSpider);
                        } catch (NumberFormatException e) {
                            finish(invalidEPE.getMessage());
                        }
                        break;
                    case ("Grasshopper"):
                        try {
                            tmpX = Integer.parseInt(words[TWO]);
                            tmpY = Integer.parseInt(words[THREE]);
                            if (tmpX > d || tmpY > d || tmpY <= 0 || tmpX <= 0) {
                                finish(invalidEPE.getMessage());
                            }
                            for (Insect insect : insects) {
                                if (color == insect.getColor() && insect.getTypeOfInsect() == Grasshopper.class) {
                                    finish(die.getMessage());
                                }
                            }
                            position = new EntityPosition(tmpX, tmpY);
                            Grasshopper newGrasshopper = new Grasshopper(position, color);
                            gameBoard.addEntity(newGrasshopper);
                            insects.add(newGrasshopper);
                        } catch (NumberFormatException e) {
                            finish(invalidEPE.getMessage());
                        }
                        break;
                    default:
                        finish(invalidITE.getMessage());
                }
            }
            for (int i = 0; i < m; i++) {
                currentString = br.readLine();
                int tmpX;
                int tmpY;
                int value;
                EntityPosition position;
                String[] words = currentString.split(" ");
                try {
                    value = Integer.parseInt(words[ZERO]);
                    tmpX = Integer.parseInt(words[ONE]);
                    tmpY = Integer.parseInt(words[TWO]);
                    if (tmpX > d || tmpY > d || tmpY <= 0 || tmpX <= 0) {
                        finish(invalidEPE.getMessage());
                    }
                    position = new EntityPosition(tmpX, tmpY);
                    FoodPoint foodPoint = new FoodPoint(position, value);
                    gameBoard.addEntity(foodPoint);
                } catch (NumberFormatException e) {
                    finish(invalidEPE.getMessage());
                }
            }
            for (Insect insect : insects) {
                insect.getBestDirection(gameBoard.getBoardData(), d);
            }
            finish();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void finish() {
        try {
            bw.close();
            br.close();
            System.exit(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.exit(0);
    }
    public static void finish(String exception) {
        try {
            bw.write(exception);
            bw.newLine();
            bw.close();
            br.close();
            System.exit(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.exit(0);
    }

    public static void write(String letter) {
        try {
            bw.write(letter);
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

abstract class BoardEntity {
    protected EntityPosition entityPosition;

}

class Board {
    private Map<String, BoardEntity> boardData;
    private int size;

    public Board(int boardSize) {
        this.size = boardSize;
        this.boardData = new HashMap<>();
    }

    public void addEntity(BoardEntity entity) {
        String key = entity.entityPosition.toKey();
        if (boardData.get(key) != null) {
            Main.finish(Main.tESPE.getMessage());
            System.out.println(boardData.get(key));
        } else {
            boardData.put(key, entity);
        }
    }

    public Map<String, BoardEntity> getBoardData() {
        return boardData;
    }

    public BoardEntity getEntity(EntityPosition position) {
        String key = position.toKey();
        return boardData.get(key);
    }

    public Direction getDirection(Insect insect) {
        // Implementation
        return null;
    }

    public int getDirectionSum(Insect insect) {
        // Implementation
        return 0;
    }
}
class EntityPosition {
    protected int x;
    protected int y;

    public EntityPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toKey() {
        String key = x + "." + y;
        return key;
    }
}

class FoodPoint extends BoardEntity {

    protected int value;

    public FoodPoint(EntityPosition position, int value) {
        this.entityPosition = new EntityPosition(position.x, position.y);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
enum Direction {
    N("North"),
    E("East"),
    S("South"),
    W("West"),
    NE("North-East"),
    SE("South-East"),
    SW("South-West"),
    NW("North-West");

    private String textRepresentation;

    Direction(String text) {
        this.textRepresentation = text;
    }

    public String getTextRepresentation() {
        return textRepresentation;
    }
}

enum InsectColor {
    RED,
    GREEN,
    BLUE,
    YELLOW;

    public static InsectColor toColor(String s) {
        s = s.toLowerCase();
        switch (s) {
            case ("red"):
                return RED;
            case ("green"):
                return GREEN;
            case ("blue"):
                return BLUE;
            case ("yellow"):
                return YELLOW;
            default:
                Main.finish(Main.invalidICE.getMessage());
                return null;
        }
    }

    public static String colorToString(InsectColor color) {
        switch (color) {
            case RED:
                return "Red";
            case BLUE:
                return "Blue";
            case GREEN:
                return "Green";
            case YELLOW:
                return "Yellow";
            default:
                return null;
        }
    }
}

class Insect extends BoardEntity {

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
    public Class getTypeOfInsect() {
        return this.getClass();
    }

}
interface OrthogonalMoving {
    int getOrthogonalDirectionVisibleValue(Direction dir, EntityPosition entityPosition,
                                           Map<String, BoardEntity> boardData, int boardSize);

    int travelOrthogonally(Direction dir, EntityPosition entityPosition, InsectColor color,
                           Map<String, BoardEntity> boardData, int boardSize);
}

interface DiagonalMoving {
    int getDiagonalDirectionVisibleValue(Direction dir, EntityPosition entityPosition,
                                         Map<String, BoardEntity> boardData, int boardSize);

    int travelDiagonally(Direction dir, EntityPosition entityPosition, InsectColor color,
                         Map<String, BoardEntity> boardData, int boardSize);
}

class Ant extends Insect implements OrthogonalMoving, DiagonalMoving {

    private int step = 1;
    private int value;
    public Ant(EntityPosition position, InsectColor color) {
        super(position, color);
    }
    @Override
    public void getBestDirection(Map<String, BoardEntity> boardData, int boardSize) {
        Map<Integer, Direction> values = new HashMap<>();
        ArrayList<Integer> maxValues = new ArrayList<>();
        Direction bestDirection;
        int key;
        key = getOrthogonalDirectionVisibleValue(Direction.N, this.entityPosition, boardData, boardSize);
        if (values.get(key) == null) {
            values.put(key, Direction.N);
            maxValues.add(key);
        }
        key = getOrthogonalDirectionVisibleValue(Direction.E, this.entityPosition, boardData, boardSize);
        if (values.get(key) == null) {
            values.put(key, Direction.E);
            maxValues.add(key);
        }
        key = getOrthogonalDirectionVisibleValue(Direction.S, this.entityPosition, boardData, boardSize);
        if (values.get(key) == null) {
            values.put(key, Direction.S);
            maxValues.add(key);
        }
        key = getOrthogonalDirectionVisibleValue(Direction.W, this.entityPosition, boardData, boardSize);
        if (values.get(key) == null) {
            values.put(key, Direction.W);
            maxValues.add(key);
        }

        key = getDiagonalDirectionVisibleValue(Direction.NE, this.entityPosition, boardData, boardSize);
        if (values.get(key) == null) {
            values.put(key, Direction.NE);
            maxValues.add(key);
        }
        key = getDiagonalDirectionVisibleValue(Direction.SE, this.entityPosition, boardData, boardSize);
        if (values.get(key) == null) {
            values.put(key, Direction.SE);
            maxValues.add(key);
        }
        key = getDiagonalDirectionVisibleValue(Direction.SW, this.entityPosition, boardData, boardSize);
        if (values.get(key) == null) {
            values.put(key, Direction.SW);
            maxValues.add(key);
        }
        key = getDiagonalDirectionVisibleValue(Direction.NW, this.entityPosition, boardData, boardSize);
        if (values.get(key) == null) {
            values.put(key, Direction.NW);
            maxValues.add(key);
        }

        key = Collections.max(maxValues);
        bestDirection = values.get(key);
        if (key == 0) {
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
            default:
                return;
        }
        String letter;
        letter = InsectColor.colorToString(color) + " " + getClass().getName() + " "
                + direction.getTextRepresentation() + " " + value;
        Main.write(letter);
    }

    @Override
    public int getOrthogonalDirectionVisibleValue(Direction dir, EntityPosition entityPosition,
                                                  Map<String, BoardEntity> boardData, int boardSize) {
        int biggestValue = 0;
        String key;
        int value;
        int x = entityPosition.x;
        int y = entityPosition.y;
        switch (dir) {
            case N:
                for (int i = x; i > 0; i -= step) {
                    key = i + "." + y;
                    if (boardData.get(key) instanceof FoodPoint) {
                        value = ((FoodPoint) boardData.get(key)).getValue();
                        biggestValue += value;
                    }
                }
                break;
            case E:
                for (int i = y; i <= boardSize; i += step) {
                    key = x + "." + i;
                    if (boardData.get(key) instanceof FoodPoint) {
                        value = ((FoodPoint) boardData.get(key)).getValue();
                        biggestValue += value;
                    }
                }
                break;
            case S:
                for (int i = x; i <= boardSize; i += step) {
                    key = i + "." + y;
                    if (boardData.get(key) instanceof FoodPoint) {
                        value = ((FoodPoint) boardData.get(key)).getValue();
                        biggestValue += value;
                    }
                }
                break;
            case W:
                for (int i = y; i > 0; i -= step) {
                    key = x + "." + i;
                    if (boardData.get(key) instanceof FoodPoint) {
                        value = ((FoodPoint) boardData.get(key)).getValue();
                        biggestValue += value;
                    }
                }
                break;
            default:
                return 0;
        }
        return biggestValue;
    }

    @Override
    public int travelOrthogonally(Direction dir, EntityPosition entityPosition, InsectColor color,
                                  Map<String, BoardEntity> boardData, int boardSize) {
        int x = this.entityPosition.x;
        int y = this.entityPosition.y;
        String thisKey = x + "." + y;
        String key;
        switch (dir) {
            case N:
                while (x > 0) {
                    x -= step;
                    key = x + "." + y;
                    if (boardData.get(key) == null) {
                        continue;
                    } else if (boardData.get(key) instanceof FoodPoint) {
                        value += ((FoodPoint) boardData.get(key)).getValue();
                        boardData.remove(key);
                    } else if (boardData.get(key) instanceof Insect) {
                        if (this.color == ((Insect) boardData.get(key)).getColor()) {
                            continue;
                        } else {
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
                        value += ((FoodPoint) boardData.get(key)).getValue();
                        boardData.remove(key);
                    } else if (boardData.get(key) instanceof Insect) {
                        if (this.color == ((Insect) boardData.get(key)).getColor()) {
                            continue;
                        } else {
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
                        value += ((FoodPoint) boardData.get(key)).getValue();
                        boardData.remove(key);
                    } else if (boardData.get(key) instanceof Insect) {
                        if (this.color == ((Insect) boardData.get(key)).getColor()) {
                            continue;
                        } else {
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
                        value += ((FoodPoint) boardData.get(key)).getValue();
                        boardData.remove(key);
                    } else if (boardData.get(key) instanceof Insect) {
                        if (this.color == ((Insect) boardData.get(key)).getColor()) {
                            continue;
                        } else {
                            boardData.remove(thisKey);
                            return value;
                        }
                    }
                }
                boardData.remove(thisKey);
                break;
            default:
                return 0;
        }
        return value;
    }

    @Override
    public int getDiagonalDirectionVisibleValue(Direction dir, EntityPosition entityPosition,
                                                Map<String, BoardEntity> boardData, int boardSize) {
        int biggestValue = 0;
        String key;
        int value;
        int x = this.entityPosition.x;
        int y = this.entityPosition.y;
        switch (dir) {
            case NE:
                while (x > 0 && y < boardSize) {
                    x -= step;
                    y += step;
                    key = x + "." + y;
                    if (boardData.get(key) instanceof FoodPoint) {
                        value = ((FoodPoint) boardData.get(key)).getValue();
                        biggestValue += value;
                    }
                }
                break;
            case SE:
                while (x < boardSize && y < boardSize) {
                    x += step;
                    y += step;
                    key = x + "." + y;
                    if (boardData.get(key) instanceof FoodPoint) {
                        value = ((FoodPoint) boardData.get(key)).getValue();
                        biggestValue += value;
                    }
                }
                break;
            case SW:
                while (x < boardSize && y > 0) {
                    x += step;
                    y -= step;
                    key = x + "." + y;
                    if (boardData.get(key) instanceof FoodPoint) {
                        value = ((FoodPoint) boardData.get(key)).getValue();
                        biggestValue += value;
                    }
                }
                break;
            case NW:
                while (x > 0 && y > 0) {
                    x -= step;
                    y -= step;
                    key = x + "." + y;
                    if (boardData.get(key) instanceof FoodPoint) {
                        value = ((FoodPoint) boardData.get(key)).getValue();
                        biggestValue += value;
                    }
                }
                break;
            default:
                return 0;
        }
        return biggestValue;
    }

    @Override
    public int travelDiagonally(Direction dir, EntityPosition entityPosition, InsectColor color,
                                Map<String, BoardEntity> boardData, int boardSize) {
        int x = this.entityPosition.x;
        int y = this.entityPosition.y;
        String thisKey = x + "." + y;
        String key;

        switch (dir) {
            case NE:
                while (y < boardSize && x > 0) {
                    y += step;
                    x -= step;
                    key = x + "." + y;
                    if (boardData.get(key) == null) {
                        continue;
                    } else if (boardData.get(key) instanceof FoodPoint) {
                        value += ((FoodPoint) boardData.get(key)).getValue();
                        boardData.remove(key);
                    } else if (boardData.get(key) instanceof Insect) {
                        if (this.color == ((Insect) boardData.get(key)).getColor()) {
                            continue;
                        } else {
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
                        value += ((FoodPoint) boardData.get(key)).getValue();
                        boardData.remove(key);
                    } else if (boardData.get(key) instanceof Insect) {
                        if (this.color == ((Insect) boardData.get(key)).getColor()) {
                            continue;
                        } else {
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
                        value += ((FoodPoint) boardData.get(key)).getValue();
                        boardData.remove(key);
                    } else if (boardData.get(key) instanceof Insect) {
                        if (this.color == ((Insect) boardData.get(key)).getColor()) {
                            continue;
                        } else {
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
                        value += ((FoodPoint) boardData.get(key)).getValue();
                        boardData.remove(key);
                    } else if (boardData.get(key) instanceof Insect) {
                        if (this.color == ((Insect) boardData.get(key)).getColor()) {
                            continue;
                        } else {
                            boardData.remove(thisKey);
                            return value;
                        }
                    }
                }
                boardData.remove(thisKey);
                break;
            default:
                return 0;
        }
        return value;
    }
}

class Butterfly extends Insect implements OrthogonalMoving {

    private int step = 1;
    private int value;
    public Butterfly(EntityPosition position, InsectColor color) {
        super(position, color);
    }

    @Override
    public void getBestDirection(Map<String, BoardEntity> boardData, int boardSize) {
        Map<Integer, Direction> values = new HashMap<>();
        ArrayList<Integer> maxValues = new ArrayList<>();
        Direction bestDirection;
        int key;
        key = getOrthogonalDirectionVisibleValue(Direction.N, this.entityPosition, boardData, boardSize);
        if (values.get(key) == null) {
            values.put(key, Direction.N);
            maxValues.add(key);
        }
        key = getOrthogonalDirectionVisibleValue(Direction.E, this.entityPosition, boardData, boardSize);
        if (values.get(key) == null) {
            values.put(key, Direction.E);
            maxValues.add(key);
        }
        key = getOrthogonalDirectionVisibleValue(Direction.S, this.entityPosition, boardData, boardSize);
        if (values.get(key) == null) {
            values.put(key, Direction.S);
            maxValues.add(key);
        }
        key = getOrthogonalDirectionVisibleValue(Direction.W, this.entityPosition, boardData, boardSize);
        if (values.get(key) == null) {
            values.put(key, Direction.W);
            maxValues.add(key);
        }

        key = Collections.max(maxValues);
        bestDirection = values.get(key);
        if (key == 0) {
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
            default:
                return;
        }
        String letter;
        letter = InsectColor.colorToString(color) + " " + getClass().getName() + " "
                + direction.getTextRepresentation() + " " + value;
        Main.write(letter);
    }

    @Override
    public int getOrthogonalDirectionVisibleValue(Direction dir, EntityPosition entityPosition,
                                                  Map<String, BoardEntity> boardData, int boardSize) {
        int biggestValue = 0;
        String key;
        int value;
        int x = entityPosition.x;
        int y = entityPosition.y;

        switch (dir) {
            case N:
                for (int i = x; i > 0; i -= step) {
                    key = i + "." + y;
                    if (boardData.get(key) instanceof FoodPoint) {
                        value = ((FoodPoint) boardData.get(key)).getValue();
                        biggestValue += value;
                    }
                }
                break;
            case E:
                for (int i = y; i <= boardSize; i += step) {
                    key = x + "." + i;
                    if (boardData.get(key) instanceof FoodPoint) {
                        value = ((FoodPoint) boardData.get(key)).getValue();
                        biggestValue += value;
                    }
                }
                break;
            case S:
                for (int i = x; i <= boardSize; i += step) {
                    key = i + "." + y;
                    if (boardData.get(key) instanceof FoodPoint) {
                        value = ((FoodPoint) boardData.get(key)).getValue();
                        biggestValue += value;
                    }
                }
                break;
            case W:
                for (int i = y; i > 0; i -= step) {
                    key = x + "." + i;
                    if (boardData.get(key) instanceof FoodPoint) {
                        value = ((FoodPoint) boardData.get(key)).getValue();
                        biggestValue += value;
                    }
                }
                break;
            default:
                return 0;
        }
        return biggestValue;
    }

    @Override
    public int travelOrthogonally(Direction dir, EntityPosition entityPosition, InsectColor color,
                                  Map<String, BoardEntity> boardData, int boardSize) {
        int x = this.entityPosition.x;
        int y = this.entityPosition.y;
        String thisKey = x + "." + y;
        String key;
        switch (dir) {
            case N:
                while (x > 0) {
                    x -= step;
                    key = x + "." + y;
                    if (boardData.get(key) == null) {
                        continue;
                    } else if (boardData.get(key) instanceof FoodPoint) {
                        value += ((FoodPoint) boardData.get(key)).getValue();
                        boardData.remove(key);
                    } else if (boardData.get(key) instanceof Insect) {
                        if (this.color == ((Insect) boardData.get(key)).getColor()) {
                            continue;
                        } else {
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
                        value += ((FoodPoint) boardData.get(key)).getValue();
                        boardData.remove(key);
                    } else if (boardData.get(key) instanceof Insect) {
                        if (this.color == ((Insect) boardData.get(key)).getColor()) {
                            continue;
                        } else {
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
                        value += ((FoodPoint) boardData.get(key)).getValue();
                        boardData.remove(key);
                    } else if (boardData.get(key) instanceof Insect) {
                        if (this.color == ((Insect) boardData.get(key)).getColor()) {
                            continue;
                        } else {
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
                        value += ((FoodPoint) boardData.get(key)).getValue();
                        boardData.remove(key);
                    } else if (boardData.get(key) instanceof Insect) {
                        if (this.color == ((Insect) boardData.get(key)).getColor()) {
                            continue;
                        } else {
                            boardData.remove(thisKey);
                            return value;
                        }
                    }
                }
                boardData.remove(thisKey);
                break;
            default:
                return 0;
        }
        return value;
    }
}

class Spider extends Insect implements DiagonalMoving {

    private int step = 1;
    private int value;
    public Spider(EntityPosition position, InsectColor color) {
        super(position, color);
    }

    @Override
    public void getBestDirection(Map<String, BoardEntity> boardData, int boardSize) {
        Map<Integer, Direction> values = new HashMap<>();
        ArrayList<Integer> maxValues = new ArrayList<>();
        Direction bestDirection;
        int key;

        key = getDiagonalDirectionVisibleValue(Direction.NE, this.entityPosition, boardData, boardSize);
        if (values.get(key) == null) {
            values.put(key, Direction.NE);
            maxValues.add(key);
        }
        key = getDiagonalDirectionVisibleValue(Direction.SE, this.entityPosition, boardData, boardSize);
        if (values.get(key) == null) {
            values.put(key, Direction.SE);
            maxValues.add(key);
        }
        key = getDiagonalDirectionVisibleValue(Direction.SW, this.entityPosition, boardData, boardSize);
        if (values.get(key) == null) {
            values.put(key, Direction.SW);
            maxValues.add(key);
        }
        key = getDiagonalDirectionVisibleValue(Direction.NW, this.entityPosition, boardData, boardSize);
        if (values.get(key) == null) {
            values.put(key, Direction.NW);
            maxValues.add(key);
        }
        key = Collections.max(maxValues);
        bestDirection = values.get(key);
        if (key == 0) {
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
            default:
                return;
        }
        String letter;
        letter = InsectColor.colorToString(color) + " " + getClass().getName() + " "
                + direction.getTextRepresentation() + " " + value;
        Main.write(letter);
    }

    @Override
    public int getDiagonalDirectionVisibleValue(Direction dir, EntityPosition entityPosition,
                                                Map<String, BoardEntity> boardData, int boardSize) {
        int biggestValue = 0;
        String key;
        int value;
        int x = this.entityPosition.x;
        int y = this.entityPosition.y;
        switch (dir) {
            case NE:
                while (x > 0 && y < boardSize) {
                    x -= step;
                    y += step;
                    key = x + "." + y;
                    if (boardData.get(key) instanceof FoodPoint) {
                        value = ((FoodPoint) boardData.get(key)).getValue();
                        biggestValue += value;
                    }
                }
                break;
            case SE:
                while (x < boardSize && y < boardSize) {
                    x += step;
                    y += step;
                    key = x + "." + y;
                    if (boardData.get(key) instanceof FoodPoint) {
                        value = ((FoodPoint) boardData.get(key)).getValue();
                        biggestValue += value;
                    }
                }
                break;
            case SW:
                while (x < boardSize && y > 0) {
                    x += step;
                    y -= step;
                    key = x + "." + y;
                    if (boardData.get(key) instanceof FoodPoint) {
                        value = ((FoodPoint) boardData.get(key)).getValue();
                        biggestValue += value;
                    }
                }
                break;
            case NW:
                while (x > 0 && y > 0) {
                    x -= step;
                    y -= step;
                    key = x + "." + y;
                    if (boardData.get(key) instanceof FoodPoint) {
                        value = ((FoodPoint) boardData.get(key)).getValue();
                        biggestValue += value;
                    }
                }
                break;
            default:
                return 0;
        }
        return biggestValue;
    }

    @Override
    public int travelDiagonally(Direction dir, EntityPosition entityPosition, InsectColor color,
                                Map<String, BoardEntity> boardData, int boardSize) {
        int x = this.entityPosition.x;
        int y = this.entityPosition.y;
        String thisKey = x + "." + y;
        String key;
        switch (dir) {
            case NE:
                while (y < boardSize && x > 0) {
                    y += step;
                    x -= step;
                    key = x + "." + y;
                    if (boardData.get(key) == null) {
                        continue;
                    } else if (boardData.get(key) instanceof FoodPoint) {
                        value += ((FoodPoint) boardData.get(key)).getValue();
                        boardData.remove(key);
                    } else if (boardData.get(key) instanceof Insect) {
                        if (this.color == ((Insect) boardData.get(key)).getColor()) {
                            continue;
                        } else {
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
                        value += ((FoodPoint) boardData.get(key)).getValue();
                        boardData.remove(key);
                    } else if (boardData.get(key) instanceof Insect) {
                        if (this.color == ((Insect) boardData.get(key)).getColor()) {
                            continue;
                        } else {
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
                        value += ((FoodPoint) boardData.get(key)).getValue();
                        boardData.remove(key);
                    } else if (boardData.get(key) instanceof Insect) {
                        if (this.color == ((Insect) boardData.get(key)).getColor()) {
                            continue;
                        } else {
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
                        value += ((FoodPoint) boardData.get(key)).getValue();
                        boardData.remove(key);
                    } else if (boardData.get(key) instanceof Insect) {
                        if (this.color == ((Insect) boardData.get(key)).getColor()) {
                            continue;
                        } else {
                            boardData.remove(thisKey);
                            return value;
                        }
                    }
                }
                boardData.remove(thisKey);
                break;
            default:
                return 0;
        }
        return value;
    }

}

class Grasshopper extends Insect {

    private int value;
    private int step = 2;
    public Grasshopper(EntityPosition position, InsectColor color) {
        super(position, color);
    }
    @Override
    public void getBestDirection(Map<String, BoardEntity> boardData, int boardSize) {
        Map<Integer, Direction> values = new HashMap<>();
        ArrayList<Integer> maxValues = new ArrayList<>();
        Direction bestDirection;
        int key;
        key = getOrthogonalDirectionVisibleValue(Direction.N, this.entityPosition, boardData, boardSize);
        if (values.get(key) == null) {
            values.put(key, Direction.N);
            maxValues.add(key);
        }
        key = getOrthogonalDirectionVisibleValue(Direction.E, this.entityPosition, boardData, boardSize);
        if (values.get(key) == null) {
            values.put(key, Direction.E);
            maxValues.add(key);
        }
        key = getOrthogonalDirectionVisibleValue(Direction.S, this.entityPosition, boardData, boardSize);
        if (values.get(key) == null) {
            values.put(key, Direction.S);
            maxValues.add(key);
        }
        key = getOrthogonalDirectionVisibleValue(Direction.W, this.entityPosition, boardData, boardSize);
        if (values.get(key) == null) {
            values.put(key, Direction.W);
            maxValues.add(key);
        }

        key = Collections.max(maxValues);
        bestDirection = values.get(key);
        if (key == 0) {
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
            default:
                return;
        }
        String letter;
        letter = InsectColor.colorToString(color) + " " + getClass().getName() + " "
                + direction.getTextRepresentation() + " " + value;
        Main.write(letter);
    }

    public int getOrthogonalDirectionVisibleValue(Direction dir, EntityPosition entityPosition,
                                                  Map<String, BoardEntity> boardData, int boardSize) {
        int biggestValue = 0;
        String key;
        int value;
        int x = entityPosition.x;
        int y = entityPosition.y;
        switch (dir) {
            case N:
                for (int i = x; i > 0; i -= step) {
                    key = i + "." + y;
                    if (boardData.get(key) instanceof FoodPoint) {
                        value = ((FoodPoint) boardData.get(key)).getValue();
                        biggestValue += value;
                    }
                }
                break;
            case E:
                for (int i = y; i <= boardSize; i += step) {
                    key = x + "." + i;
                    if (boardData.get(key) instanceof FoodPoint) {
                        value = ((FoodPoint) boardData.get(key)).getValue();
                        biggestValue += value;
                    }
                }
                break;
            case S:
                for (int i = x; i <= boardSize; i += step) {
                    key = i + "." + y;
                    if (boardData.get(key) instanceof FoodPoint) {
                        value = ((FoodPoint) boardData.get(key)).getValue();
                        biggestValue += value;
                    }
                }
                break;
            case W:
                for (int i = y; i > 0; i -= step) {
                    key = x + "." + i;
                    if (boardData.get(key) instanceof FoodPoint) {
                        value = ((FoodPoint) boardData.get(key)).getValue();
                        biggestValue += value;
                    }
                }
                break;
            default:
                return 0;
        }
        return biggestValue;
    }

    public int travelOrthogonally(Direction dir, EntityPosition entityPosition, InsectColor color,
                                  Map<String, BoardEntity> boardData, int boardSize) {
        int x = this.entityPosition.x;
        int y = this.entityPosition.y;
        String thisKey = x + "." + y;
        String key;
        switch (dir) {
            case N:
                while (x > 0) {
                    x -= step;
                    key = x + "." + y;
                    if (boardData.get(key) == null) {
                        continue;
                    } else if (boardData.get(key) instanceof FoodPoint) {
                        value += ((FoodPoint) boardData.get(key)).getValue();
                        boardData.remove(key);
                    } else if (boardData.get(key) instanceof Insect) {
                        if (this.color == ((Insect) boardData.get(key)).getColor()) {
                            continue;
                        } else {
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
                        value += ((FoodPoint) boardData.get(key)).getValue();
                        boardData.remove(key);
                    } else if (boardData.get(key) instanceof Insect) {
                        if (this.color == ((Insect) boardData.get(key)).getColor()) {
                            continue;
                        } else {
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
                        value += ((FoodPoint) boardData.get(key)).getValue();
                        boardData.remove(key);
                    } else if (boardData.get(key) instanceof Insect) {
                        if (this.color == ((Insect) boardData.get(key)).getColor()) {
                            continue;
                        } else {
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
                        value += ((FoodPoint) boardData.get(key)).getValue();
                        boardData.remove(key);
                    } else if (boardData.get(key) instanceof Insect) {
                        if (this.color == ((Insect) boardData.get(key)).getColor()) {
                            continue;
                        } else {
                            boardData.remove(thisKey);
                            return value;
                        }
                    }
                }
                boardData.remove(thisKey);
                break;
            default:
                return 0;
        }
        return value;
    }
}

class DuplicateInsectException extends Exception {

    @Override
    public String getMessage() {
        return "Duplicate insects";

    }
}

class InvalidBoardSizeException extends Exception {

    @Override
    public String getMessage() {
        return "Invalid board size";
    }
}
class InvalidEntityPositionException extends Exception {

    @Override
    public String getMessage() {
        return "Invalid entity position";
    }
}

class InvalidInsectColorException extends Exception {

    @Override
    public String getMessage() {
        return "Invalid insect color";
    }
}

class InvalidInsectTypeException extends Exception {

    @Override
    public String getMessage() {
        return "Invalid insect type";
    }
}

class InvalidNumberOfFoodPointsException extends Exception {

    @Override
    public String getMessage() {
        return "Invalid number of food points";
    }
}

class InvalidNumberOfInsectsException extends Exception {

    @Override
    public String getMessage() {
        return "Invalid number of insects";
    }
}
class TwoEntitiesOnSamePositionException extends Exception {

    @Override
    public String getMessage() {
        return "Two entities in the same position";
    }
}

