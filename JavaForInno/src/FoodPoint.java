public class FoodPoint extends BoardEntity{

    protected int value;

    public FoodPoint(EntityPosition position, int value){
        this.entityPosition = new EntityPosition(position.x, position.y);
        this.value = value;
    }

    public int getValue(){
        return value;
    }

}
