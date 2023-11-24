import java.util.Map;

public class Spider extends Insect {

    public Spider(EntityPosition position, InsectColor color){
        super(position, color);
    }

    @Override
    public void getBestDirection(Map<String, BoardEntity> boardData, int boardSize) {
        super.getBestDirection(boardData, boardSize);
    }

    @Override
    public void travelDirection(Direction direction, Map<String, BoardEntity> boardData, int boardSize) {
        super.travelDirection(direction, boardData, boardSize);
    }


}
