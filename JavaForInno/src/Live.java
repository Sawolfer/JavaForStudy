public interface Live {

    default void Live(){
        System.out.printf(getClass().getName());
    };

}
