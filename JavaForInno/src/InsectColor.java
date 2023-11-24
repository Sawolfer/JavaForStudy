public enum InsectColor {
    RED,
    GREEN,
    BLUE,
    YELLOW;

    public InsectColor toColor(String s){
        s.toLowerCase();
        switch (s){
            case ("red"):
                return RED;
            case ("green"):
                return GREEN;
            case ("blue"):
                return BLUE;
            case ("yellow"):
                return YELLOW;
            default:
                return null;
        }
    }
}
