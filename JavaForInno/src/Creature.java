public abstract class Creature {

    private String name = null;
    private boolean isAlive = false;

    public abstract void bear();
    public abstract void die();

    public  String getName(){return name;};

    public void setName(String Name){
        name = Name;
    }
    public void shoutName(){
        if (name!=null){
            System.out.printf(name);
        }

    }
}
