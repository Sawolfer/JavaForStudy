public abstract class Document implements Printable{

    private DocType type;
    protected int number;
    protected String ownerName;

    public Document(DocType type, int number, String ownerName){
        this.type = type;
        this.number = number;
        this.ownerName = ownerName;
    }

    public DocType type(){
        return this.type;
    }

}
