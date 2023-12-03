public enum DocType {

    PASSPORT(true),
    SNISL(false),
    OTHER(false);

    boolean isFixedTerm;
    DocType (boolean isFixedTerm){
        this.isFixedTerm = isFixedTerm;
    }
}
