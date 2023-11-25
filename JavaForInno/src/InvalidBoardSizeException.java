public class InvalidBoardSizeException extends Exception{

    public InvalidBoardSizeException(String message){
        super(message);
    }
    @Override
    public String getMessage() {
        return "Invalid board size";
    }
}
