package lab8_1;

import jdk.internal.org.objectweb.asm.commons.StaticInitMerger;
import org.omg.PortableInterceptor.ServerRequestInfo;

public class Animal {
    String name;
    int height;
    int weight;
    String color;

    String sound;

    protected Animal(String _name, int _height, int _weght, String _color, String _sound){
        name = _name;
        height = _height;
        weight = _weght;
        color = _color;
        sound = _sound;

    }
    protected void ToEat(){
        System.out.printf(name + " is eating");
    }
    protected void ToSleep(){
        System.out.printf(name + " is sleeping");
    }
    protected void ToMakeSound(){
        System.out.printf(name + sound);
    }
}
