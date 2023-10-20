package lab8_2;

public class Triangle {

    int a;

    int h;

    protected Triangle(int A, int H){
        a= A;
        h =H;

    }

    protected double Perimiter(){
        return a*3;
    }

    protected double Area(){
        return 0.5 * a* h;
    }

}
