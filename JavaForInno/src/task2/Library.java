package task2;
import java.util.Scanner;

public class Library {


    public static void  main(String[] args){
        Book[] library = new Book[100];
//        switch (currentString){
//            case ("+Book") :
//                String Name = s.next();
//                String AName = s.next();
//                String AEmail = s.next();
//                char AGender = s.next().charAt(10);
//                double Price = s.nextDouble();
//                int Qty = 1;
//                Qty = s.nextInt();
//                Author author = new Author().author(AName, AEmail, AGender);
//                for ( int i=0; i<100; i++){
//                    if (library[i].name == Name){
//                        library[i].qty ++;
//                    }
//                    else if(library[i].name == "null"){
//                        library[i].book(Name, author, Price, Qty);
//                    }
//                }
//
//        }
        Author author = new Author("Joe", "pavapepa@gmail.com", 'm' );
        Book book = new Book("error 404", author, 15, 2);

        System.out.printf("%s", library[0].getName());
    }
}
