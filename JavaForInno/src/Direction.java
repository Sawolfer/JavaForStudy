public enum Direction {
    N("North"),
    E("East"),
    S("South"),
    W("West"),
    NE("North-East"),
    SE("South-East"),
    SW("South-West"),
    NW("North-West");

    private String textRepresentation;

    Direction(String text) {
        this.textRepresentation = text;
    }

    public String getTextRepresentation() {
        return textRepresentation;
    }
}