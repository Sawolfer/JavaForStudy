import java.util.Map;

public interface OrthogonalMoving {
    int getOrthogonalDirection(Direction dir, EntityPosition entityPosition, Map<String, BoardEntity> boardData, int boardSize);

    int travelOrthogonally(Direction dir, EntityPosition entityPosition, InsectColor color, Map<String, BoardEntity> boardData, int boardSize);
}