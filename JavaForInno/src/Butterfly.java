import java.util.Map;

public class Butterfly extends Insect implements OrthogonalMoving{

    public Butterfly(EntityPosition position, InsectColor color){
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

    @Override
    public int getOrthogonalDirection(Direction dir, EntityPosition entityPosition, Map<String, BoardEntity> boardData, int boardSize) {
        return 0;
    }

    @Override
    public int travelOrthogonally(Direction dir, EntityPosition entityPosition, InsectColor color, Map<String, BoardEntity> boardData, int boardSize) {
        return 0;
    }
}
