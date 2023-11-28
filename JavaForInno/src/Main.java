
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

    /**
     * all necessary variables like
     * all extensions
     * buffers for read and write data
     * gameBoard
     * variables d n m
     * and other variables that are used in code for checking variables
     * and list of all initialised insects
     */
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


    /**
     * in this function we open input and output files, parse all input data, verify it and create all insects and
     * food points. Here we also start game
     * @param args - input arguments
     */
    public static void main(String[] args) {
        try {
            bw = new BufferedWriter(new FileWriter("output.txt"));
            br = new BufferedReader(new FileReader("input.txt"));
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
                insect.returnAnswer(gameBoard.getBoardData(), d);
            }
            finish();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * close all files and exit program
     */
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

    /**
     * also close all files but also write exception string in output file and exit program
     * @param exception - exception string that will be  written in file
     */
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

    /**
     * function that write data to output file in successful case
     * @param letter - string that will be  written
     */
    public static void write(String letter) {
        try {
            bw.write(letter);
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

/**
 * class that represent position of entity at the board
 */
abstract class BoardEntity {
    protected EntityPosition entityPosition;

}

/**
 * class of the board about it size and entities on it
 * its also has functions to add, get entity
 * and functions to get direction of insect and sum of food on its road
 */
class Board {
    private Map<String, BoardEntity> boardData;
    private int size;

    /**
     * constructor for Board
     * @param boardSize - size of board
     */
    public Board(int boardSize) {
        this.size = boardSize;
        this.boardData = new HashMap<>();
    }

    /**
     * Adds a BoardEntity to the boardData map if there is no entity at this position yet
     * @param entity - the BoardEntity to add to the boardData map
     */

    public void addEntity(BoardEntity entity) {
        String key = entity.entityPosition.toKey();
        if (boardData.get(key) != null) {
            Main.finish(Main.tESPE.getMessage());
            System.out.println(boardData.get(key));
        } else {
            boardData.put(key, entity);
        }
    }

    /**
     * Returns the boardData map.
     *
     * @return the boardData map
     */

    public Map<String, BoardEntity> getBoardData() {
        return boardData;
    }

    /**
     * Returns the BoardEntity at the specified EntityPosition.
     *
     * @param position - the EntityPosition to retrieve the BoardEntity for
     * @return the BoardEntity at the specified position, or null if none exists
     */
    public BoardEntity getEntity(EntityPosition position) {
        String key = position.toKey();
        return boardData.get(key);
    }

    /**
     * returns the Direction for the specified Insect.
     *
     * @param insect - the Insect to determine the direction for
     * @return the Direction of the Insect
     */
    public Direction getDirection(Insect insect) {
        // Implementation
        return null;
    }

    /**
     * returns the sum of food on road for insect
     *
     * @param insect - the Insect to calculate the direction sum for
     * @return the sum of directions for the specified Insect
     */
    public int getDirectionSum(Insect insect) {
        // Implementation
        return 0;
    }
}

/**
 * class that describes position of entity on the board
 */
class EntityPosition {
    protected int x;
    protected int y;

    /**
     * constructor of Entity position
     *
     * @param x - X coordinate
     * @param y - Y coordinate
     */
    public EntityPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * convert entity position to key for map
     * @return key for map
     */
    public String toKey() {
        String key = x + "." + y;
        return key;
    }
}

/**
 * class that describes foodpoint
 * its position, value and functions for it
 */
class FoodPoint extends BoardEntity {

    protected int value;

    /**
     * constructor for FoodPoint
     * @param position - position of food point
     * @param value - value of food point
     */
    public FoodPoint(EntityPosition position, int value) {
        this.entityPosition = new EntityPosition(position.x, position.y);
        this.value = value;
    }

    /**
     * function for getting value of food point
     * @return value of food point
     */
    public int getValue() {
        return value;
    }

}

/**
 * all possible directions
 */
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

    /**
     * function that convert string to enum value
     * @param text -
     */
    Direction(String text) {
        this.textRepresentation = text;
    }

    /**
     * function that convert enum direction to string
     * @return string representation of direction
     */
    public String getTextRepresentation() {
        return textRepresentation;
    }
}

/**
 * all possible colors
 */
enum InsectColor {
    RED,
    GREEN,
    BLUE,
    YELLOW;

    /**
     * convert string to color
     * @param s - name of color
     * @return enum color
     */
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

    /**
     * convert color to string
     * @param color - enum color that will be  converted
     * @return string name of color
     */
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

/**
 * class of insect with all functions and params of insects
 */
class Insect extends BoardEntity {

    protected InsectColor color;
    protected int value;

    /**
     * constructor of Insect
     * @param position - position of Insect at the board
     * @param color - color of insect
     */
    public Insect(EntityPosition position, InsectColor color) {
        this.entityPosition = new EntityPosition(position.x, position.y);
        this.color = color;
    }

    /**
     * function that will be  used later to find direction with the biggest value of food
     * @param boardData - data of the board
     * @param boardSize - size of the board
     */
    public Direction getBestDirection(Map<String, BoardEntity> boardData, int boardSize) {
        return null;
    }

    /**
     * function that will be used later to travel insect at the same direction
     * @param direction - direction along which it will be  move
     * @param boardData - data of the board
     * @param boardSize - size of the board
     */
    public int travelDirection(Direction direction, Map<String, BoardEntity> boardData, int boardSize) {
        return 0;
    }

    /**
     * this function return string with color, type, direction and food value that collected insect
     * @param boardData - data of the board
     * @param boardSize - size of the board
     */
    public void returnAnswer(Map<String, BoardEntity> boardData, int boardSize) {
        Direction direction = getBestDirection(boardData, boardSize);
        String letter;
        letter = InsectColor.colorToString(color) + " " + getClass().getName() + " "
                + direction.getTextRepresentation() + " " + travelDirection(direction, boardData, boardSize);
        Main.write(letter);
    }

    /**
     * function that will get color of Insect
     * @return color of insect
     */
    public InsectColor getColor() {
        return color;
    }

    /**
     * function that will get type of Insect
     * @return type of insect ant/butterfly/spider/grasshopper
     */
    public Class getTypeOfInsect() {
        return this.getClass();
    }

}
/**
 * This interface provides methods for orthogonal movement of entities on a board.
 */
interface OrthogonalMoving {

    int STEP = 1;

    /**
     * Returns the visible value in the specified direction from the entity's position.
     *
     * @param dir - the direction in which to check for visibility
     * @param entityPosition - the position of the entity on the board
     * @param boardData - data of the board
     * @param boardSize - size of the board
     * @return the visible value in the specified direction
     */
    public default int getOrthogonalDirectionVisibleValue(Direction dir, EntityPosition entityPosition,
                                           Map<String, BoardEntity> boardData, int boardSize) {
        int biggestValue = 0;
        String key;
        int value;
        int x = entityPosition.x;
        int y = entityPosition.y;
        switch (dir) {
            case N:
                for (int i = x; i > 0; i -= STEP) {
                    key = i + "." + y;
                    if (boardData.get(key) instanceof FoodPoint) {
                        value = ((FoodPoint) boardData.get(key)).getValue();
                        biggestValue += value;
                    }
                }
                break;
            case E:
                for (int i = y; i <= boardSize; i += STEP) {
                    key = x + "." + i;
                    if (boardData.get(key) instanceof FoodPoint) {
                        value = ((FoodPoint) boardData.get(key)).getValue();
                        biggestValue += value;
                    }
                }
                break;
            case S:
                for (int i = x; i <= boardSize; i += STEP) {
                    key = i + "." + y;
                    if (boardData.get(key) instanceof FoodPoint) {
                        value = ((FoodPoint) boardData.get(key)).getValue();
                        biggestValue += value;
                    }
                }
                break;
            case W:
                for (int i = y; i > 0; i -= STEP) {
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

    /**
     * Move the entity orthogonally in the specified direction and returns the new position.
     *
     * @param dir - the direction in which to move the entity
     * @param entityPosition - the current position of the entity
     * @param color - the color of the insect
     * @param boardData - data of the board
     * @param boardSize - size of the board
     * @return the new position of the entity after moving orthogonally
     */
    public default int travelOrthogonally(Direction dir, EntityPosition entityPosition, InsectColor color,
                           Map<String, BoardEntity> boardData, int boardSize) {
        int value = 0;
        int x = entityPosition.x;
        int y = entityPosition.y;
        String thisKey = x + "." + y;
        String key;
        switch (dir) {
            case N:
                while (x > 0) {
                    x -= STEP;
                    key = x + "." + y;
                    if (boardData.get(key) == null) {
                        continue;
                    } else if (boardData.get(key) instanceof FoodPoint) {
                        value += ((FoodPoint) boardData.get(key)).getValue();
                        boardData.remove(key);
                    } else if (boardData.get(key) instanceof Insect) {
                        if (color == ((Insect) boardData.get(key)).getColor()) {
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
                    y += STEP;
                    key = x + "." + y;
                    if (boardData.get(key) == null) {
                        continue;
                    } else if (boardData.get(key) instanceof FoodPoint) {
                        value += ((FoodPoint) boardData.get(key)).getValue();
                        boardData.remove(key);
                    } else if (boardData.get(key) instanceof Insect) {
                        if (color == ((Insect) boardData.get(key)).getColor()) {
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
                    x += STEP;
                    key = x + "." + y;
                    if (boardData.get(key) == null) {
                        continue;
                    } else if (boardData.get(key) instanceof FoodPoint) {
                        value += ((FoodPoint) boardData.get(key)).getValue();
                        boardData.remove(key);
                    } else if (boardData.get(key) instanceof Insect) {
                        if (color == ((Insect) boardData.get(key)).getColor()) {
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
                    y -= STEP;
                    key = x + "." + y;
                    if (boardData.get(key) == null) {
                        continue;
                    } else if (boardData.get(key) instanceof FoodPoint) {
                        value += ((FoodPoint) boardData.get(key)).getValue();
                        boardData.remove(key);
                    } else if (boardData.get(key) instanceof Insect) {
                        if (color == ((Insect) boardData.get(key)).getColor()) {
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
/**
 * This interface provides methods for diagonal movement of entities on a board.
 */
interface DiagonalMoving {
    int STEP = 1;
    /**
     * Returns the visible value in the specified direction from the entity's position.
     *
     * @param dir - the direction in which to check for visibility
     * @param entityPosition - the position of the entity on the board
     * @param boardData - data of the board
     * @param boardSize - size of the board
     * @return the visible value in the specified direction
     */
    public default int getDiagonalDirectionVisibleValue(Direction dir, EntityPosition entityPosition,
                                         Map<String, BoardEntity> boardData, int boardSize) {
        int biggestValue = 0;
        String key;
        int value;
        int x = entityPosition.x;
        int y = entityPosition.y;
        switch (dir) {
            case NE:
                while (x > 0 && y < boardSize) {
                    x -= STEP;
                    y += STEP;
                    key = x + "." + y;
                    if (boardData.get(key) instanceof FoodPoint) {
                        value = ((FoodPoint) boardData.get(key)).getValue();
                        biggestValue += value;
                    }
                }
                break;
            case SE:
                while (x < boardSize && y < boardSize) {
                    x += STEP;
                    y += STEP;
                    key = x + "." + y;
                    if (boardData.get(key) instanceof FoodPoint) {
                        value = ((FoodPoint) boardData.get(key)).getValue();
                        biggestValue += value;
                    }
                }
                break;
            case SW:
                while (x < boardSize && y > 0) {
                    x += STEP;
                    y -= STEP;
                    key = x + "." + y;
                    if (boardData.get(key) instanceof FoodPoint) {
                        value = ((FoodPoint) boardData.get(key)).getValue();
                        biggestValue += value;
                    }
                }
                break;
            case NW:
                while (x > 0 && y > 0) {
                    x -= STEP;
                    y -= STEP;
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

    /**
     * Move the entity diagonally in the specified direction and returns the new position.
     *
     * @param dir - the direction in which to move the entity
     * @param entityPosition - the current position of the entity
     * @param color - the color of the insect
     * @param boardData - data of the board
     * @param boardSize - size of the board
     * @return the new position of the entity after moving orthogonally
     */
    public default int travelDiagonally(Direction dir, EntityPosition entityPosition, InsectColor color,
                         Map<String, BoardEntity> boardData, int boardSize) {
        int value = 0;
        int x = entityPosition.x;
        int y = entityPosition.y;
        String thisKey = x + "." + y;
        String key;

        switch (dir) {
            case NE:
                while (y < boardSize && x > 0) {
                    y += STEP;
                    x -= STEP;
                    key = x + "." + y;
                    if (boardData.get(key) == null) {
                        continue;
                    } else if (boardData.get(key) instanceof FoodPoint) {
                        value += ((FoodPoint) boardData.get(key)).getValue();
                        boardData.remove(key);
                    } else if (boardData.get(key) instanceof Insect) {
                        if (color == ((Insect) boardData.get(key)).getColor()) {
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
                    y += STEP;
                    x += STEP;
                    key = x + "." + y;
                    if (boardData.get(key) == null) {
                        continue;
                    } else if (boardData.get(key) instanceof FoodPoint) {
                        value += ((FoodPoint) boardData.get(key)).getValue();
                        boardData.remove(key);
                    } else if (boardData.get(key) instanceof Insect) {
                        if (color == ((Insect) boardData.get(key)).getColor()) {
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
                    y -= STEP;
                    x += STEP;
                    key = x + "." + y;
                    if (boardData.get(key) == null) {
                        continue;
                    } else if (boardData.get(key) instanceof FoodPoint) {
                        value += ((FoodPoint) boardData.get(key)).getValue();
                        boardData.remove(key);
                    } else if (boardData.get(key) instanceof Insect) {
                        if (color == ((Insect) boardData.get(key)).getColor()) {
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
                    y -= STEP;
                    x -= STEP;
                    key = x + "." + y;
                    if (boardData.get(key) == null) {
                        continue;
                    } else if (boardData.get(key) instanceof FoodPoint) {
                        value += ((FoodPoint) boardData.get(key)).getValue();
                        boardData.remove(key);
                    } else if (boardData.get(key) instanceof Insect) {
                        if (color == ((Insect) boardData.get(key)).getColor()) {
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

/**
 * class ant that implement all moving and game logic of ant
 * also consist its params
 */
class Ant extends Insect implements OrthogonalMoving, DiagonalMoving {

    private int step = 1;
    private int value;

    /**
     * constructor of Ant
     * @param position - position of insect at the board
     * @param color - color of insect
     */
    public Ant(EntityPosition position, InsectColor color) {
        super(position, color);
    }
    /**
     * function to find direction with the biggest value of food
     * @param boardData - data of the board
     * @param boardSize - size of the board
     */
    @Override
    public Direction getBestDirection(Map<String, BoardEntity> boardData, int boardSize) {
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
//            travelDirection(Direction.N, boardData, boardSize);
            return Direction.N;
        } else {
//            travelDirection(bestDirection, boardData, boardSize);
            return bestDirection;
        }
    }
    /**
     * function to travel insect at the same direction
     * @param direction - direction along which it will be  move
     * @param boardData - data of the board
     * @param boardSize - size of the board
     */
    @Override
    public int travelDirection(Direction direction, Map<String, BoardEntity> boardData, int boardSize) {
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
                return 0;
        }
        return value;
    }

    /**
     * this function return string with color, type, direction and food value that collected insect
     * @param boardData - data of the board
     * @param boardSize - size of the board
     */
    @Override
    public void returnAnswer(Map<String, BoardEntity> boardData, int boardSize) {
        super.returnAnswer(boardData, boardSize);
    }
}

/**
 * class of butterfly that implement all functions ike moving or finding the best direction
 * also consist all params
 */
class Butterfly extends Insect implements OrthogonalMoving {

    private int step = 1;
    private int value;

    /**
     * constructor of butterfly
     * @param position - position of butterfly at the board
     * @param color - the color of insect
     */
    public Butterfly(EntityPosition position, InsectColor color) {
        super(position, color);
    }

    /**
     * function to find direction with the biggest value of food
     * @param boardData - data of the board
     * @param boardSize - size of the board
     */
    @Override
    public Direction getBestDirection(Map<String, BoardEntity> boardData, int boardSize) {
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
            return Direction.N;
        } else {
            return bestDirection;
        }
    }

    /**
     * function to travel insect at the same direction
     * @param direction - direction along which it will be move
     * @param boardData - data of the board
     * @param boardSize - size of the board
     */
    @Override
    public int travelDirection(Direction direction, Map<String, BoardEntity> boardData, int boardSize) {
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
                return 0;
        }
        return value;
    }
    /**
     * this function return string with color, type, direction and food value that collected insect
     * @param boardData - data of the board
     * @param boardSize - size of the board
     */
    @Override
    public void returnAnswer(Map<String, BoardEntity> boardData, int boardSize) {
        super.returnAnswer(boardData, boardSize);
    }
}

/**
 * class of spider with all functions like moving and finding way with the biggest value
 * also consist all params
 */
class Spider extends Insect implements DiagonalMoving {

    private int step = 1;
    private int value;

    /**
     * constructor of spider
     * @param position - position of spider
     * @param color - color of spider
     */
    public Spider(EntityPosition position, InsectColor color) {
        super(position, color);
    }
    /**
     * function to find direction with the biggest value of food
     * @param boardData - data of the board
     * @param boardSize - size of the board
     */
    @Override
    public Direction getBestDirection(Map<String, BoardEntity> boardData, int boardSize) {
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
            return Direction.NE;
        } else {
            return bestDirection;
        }
    }

    /**
     * function to travel insect at the same direction
     * @param direction - direction along which it will be move
     * @param boardData - data of the board
     * @param boardSize - size of the board
     */
    @Override
    public int travelDirection(Direction direction, Map<String, BoardEntity> boardData, int boardSize) {
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
                return 0;
        }
        return value;
    }
    /**
     * this function return string with color, type, direction and food value that collected insect
     * @param boardData - data of the board
     * @param boardSize - size of the board
     */
    @Override
    public void returnAnswer(Map<String, BoardEntity> boardData, int boardSize) {
        super.returnAnswer(boardData, boardSize);
    }
}

/**
 * class that implement all functions of grasshopper like moving and finding the best way
 * also consist all params
 */
class Grasshopper extends Insect {

    private int value;
    private int step = 2;

    /**
     * constructor of grasshopper
     * @param position - position of grasshopper at the board
     * @param color - color of grasshopper
     */
    public Grasshopper(EntityPosition position, InsectColor color) {
        super(position, color);
    }

    /**
     * function to find direction with the biggest value of food
     * @param boardData - data of the board
     * @param boardSize - size of the board
     */
    @Override
    public Direction getBestDirection(Map<String, BoardEntity> boardData, int boardSize) {
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
            return Direction.N;
        } else {
            return bestDirection;
        }
    }

    /**
     * function to travel insect at the same direction
     * @param direction - direction along which it will be move
     * @param boardData - data of the board
     * @param boardSize - size of the board
     */
    @Override
    public int travelDirection(Direction direction, Map<String, BoardEntity> boardData, int boardSize) {
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
                return 0;
        }
        return value;
    }

    /**
     * this function return string with color, type, direction and food value that collected insect
     * @param boardData - data of the board
     * @param boardSize - size of the board
     */
    @Override
    public void returnAnswer(Map<String, BoardEntity> boardData, int boardSize) {
        super.returnAnswer(boardData, boardSize);
    }

    /**
     * Returns the visible value in the specified direction from the entity's position.
     *
     * @param dir - the direction in which to check for visibility
     * @param entityPosition - the position of the entity on the board
     * @param boardData - data of the board
     * @param boardSize - size of the board
     * @return the visible value in the specified direction
     */
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

    /**
     * Move the entity orthogonally in the specified direction and returns the new position.
     *
     * @param dir - the direction in which to move the entity
     * @param entityPosition - the current position of the entity
     * @param color - the color of the insect
     * @param boardData - data of the board
     * @param boardSize - size of the board
     * @return the new position of the entity after moving orthogonally
     */
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

/**
 * exception that is thrown when insect is duplicated
 */
class DuplicateInsectException extends Exception {

    /**
     * get exception message
     * @return exception message
     */
    @Override
    public String getMessage() {
        return "Duplicate insects";

    }
}

/**
 * exception that is throw when an invalid board size is provided
 */
class InvalidBoardSizeException extends Exception {
    /**
     * get exception message
     * @return exception message
     */
    @Override
    public String getMessage() {
        return "Invalid board size";
    }
}

/**
 * exception that is there are two entities in the same position
 */
class InvalidEntityPositionException extends Exception {
    /**
     * get exception message
     * @return exception message
     */
    @Override
    public String getMessage() {
        return "Invalid entity position";
    }
}

/**
 * exception if insect with a non-existent color
 */
class InvalidInsectColorException extends Exception {
    /**
     * get exception message
     * @return exception message
     */
    @Override
    public String getMessage() {
        return "Invalid insect color";
    }
}

/**
 * exception if insect with a non-existent type
 */
class InvalidInsectTypeException extends Exception {
    /**
     * get exception message
     * @return exception message
     */
    @Override
    public String getMessage() {
        return "Invalid insect type";
    }
}

/**
 * exception thrown when an invalid count of food points is provided
 */
class InvalidNumberOfFoodPointsException extends Exception {
    /**
     * get exception message
     * @return exception message
     */
    @Override
    public String getMessage() {
        return "Invalid number of food points";
    }
}

/**
 * exception thrown when an invalid number of insects is provided
 */
class InvalidNumberOfInsectsException extends Exception {
    /**
     * get exception message
     * @return exception message
     */
    @Override
    public String getMessage() {
        return "Invalid number of insects";
    }
}

/**
 * exception thrown when
 */
class TwoEntitiesOnSamePositionException extends Exception {
    /**
     * get exception message
     * @return exception message
     */
    @Override
    public String getMessage() {
        return "Two entities in the same position";
    }
}

