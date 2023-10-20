package lab8_2;

public abstract class Elipse {

    int a;
    int b;

    protected Elipse (int A, int B){
        a= A;
        b =B;
    }
    protected double  Perimiter(){
        return (4*(3.14*a*b + (a-b)*(a-b))/ (a+b));
    }

    protected double Area(){
        return (3.14 * a * b );
    }

}
