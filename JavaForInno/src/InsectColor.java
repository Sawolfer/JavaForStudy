public enum InsectColor {
    RED,
    GREEN,
    BLUE,
    YELLOW;

    public static InsectColor toColor(String s){
        s = s.toLowerCase();
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
                Main.finish(Main.invalidICE.getMessage());
                return null;
        }
    }
}
