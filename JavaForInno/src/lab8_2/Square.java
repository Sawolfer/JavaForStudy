package lab8_2;

import lab8_1.Animal;

import java.awt.geom.Area;

public class Square extends Rectangle{

    protected Square(int A){
        super(A, A);
    }

    @Override
    protected int Perimiter() {
        return (2*a);
    }

    @Override
    protected int Area() {
        return (a*a);
    }
}
