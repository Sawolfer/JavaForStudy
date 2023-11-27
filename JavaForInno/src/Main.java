
import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
                n = Integer.parseInt(strN);
                m = Integer.parseInt(strM);
            } catch (NumberFormatException e) {
                finish("Wrong inputs");
            }
            if (d < 4 || d > 1000) {
                finish(invalidBSE.getMessage());
            }
            gameBoard = new Board(d);
            if (n < 1 || n > 16) {
                finish(invalidNOIE.getMessage());
            }
            if (m < 1 || m > 200) {
                finish(invalidNOFPE.getMessage());
            }
            for (int i = 0; i < n; i++) {
                currentString = br.readLine();
                int tmpX;
                int tmpY;
                EntityPosition position;
                String[] words = currentString.split(" ");
                InsectColor color = InsectColor.toColor(words[0]);
                words[1] = words[1].toLowerCase();
                switch (words[1]) {
                    case ("ant"):
                        try {
                            tmpX = Integer.parseInt(words[2]);
                            tmpY = Integer.parseInt(words[3]);
                            if (tmpX > d || tmpY > d) {
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
                    case ("butterfly"):
                        try {
                            tmpX = Integer.parseInt(words[2]);
                            tmpY = Integer.parseInt(words[3]);
                            if (tmpX > d || tmpY > d) {
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
                    case ("spider"):
                        try {
                            tmpX = Integer.parseInt(words[2]);
                            tmpY = Integer.parseInt(words[3]);
                            if (tmpX > d || tmpY > d) {
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
                    case ("grasshopper"):
                        try {
                            tmpX = Integer.parseInt(words[2]);
                            tmpY = Integer.parseInt(words[3]);
                            if (tmpX > d || tmpY > d) {
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
                    value = Integer.parseInt(words[0]);
                    tmpX = Integer.parseInt(words[1]);
                    tmpY = Integer.parseInt(words[2]);
                    if (tmpX > d || tmpY > d) {
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
            finish("");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void finish(String exception) {
        try {
            bw.write(exception);
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
