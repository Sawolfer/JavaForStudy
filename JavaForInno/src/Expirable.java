public interface Expirable {


    boolean hasExpired();
    void reissue();

}
