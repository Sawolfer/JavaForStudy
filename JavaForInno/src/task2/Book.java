package task2;

public class Book {
    String name = "null";
    Author author;
    double price ;
    int qty=0;

    public Book (String Name, Author Author, double Price, int Qty){
        name = Name;
        author = Author;
        price = Price;
        qty = Qty;
    }
    public Book(String Name, Author Author, double Price){
        name = Name;
        author = Author;
        price = Price;
        qty = 1;
    }
    public String getName(){
        return name;

    }
    public Author getAuthor(){
        return author;
    }
    public double getPrice(){
        return price;
    }
    public int getQty(){
        return qty;
    }
}
