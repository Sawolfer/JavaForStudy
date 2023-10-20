package lab8_2;

public class Rectangle {

    int a;
    int b;

    protected Rectangle(int A, int B){
        a= A;
        b = B;

    }

    protected int Perimiter(){
        return (a+b)*2;
    }

    protected int Area(){
        return (a*b);
    }

}
