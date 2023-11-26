import java.util.HashMap;
import java.util.Map;

public class Board {
    private Map<String, BoardEntity> boardData;
    private int size;

    public Board(int boardSize) {
        this.size = boardSize;
        this.boardData = new HashMap<>();
    }

    public void addEntity(BoardEntity entity) {
        String key = entity.entityPosition.toKey();
        if (boardData.get(key)!= null){
            Main.finish(Main.tESPE.getMessage());
            System.out.println(boardData.get(key));
        }
        else{
            boardData.put(key, entity);
        }
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