package lab8_2;

import com.sun.javafx.geom.Area;

public class Circle extends Elipse{
    protected Circle(int A) {
        super(A, A);
    }

    @Override
    protected double Perimiter() {
        return (2*3.14*a);
    }

    @Override
    protected double Area() {
        return (3.14*a*a);
    }
}
