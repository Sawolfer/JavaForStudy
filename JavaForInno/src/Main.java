import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner sc;
    static Container container;
    public static void main(String[] args) {
        container = new Container();
        sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            String[] parts = command.split(" ");
            switch (parts[0]) {
                case "end":
                    return;
                case "createBook":
                    container.createBook(parts[1], parts[2], parts[3]);
                    break;
                case "createUser":
                    container.createUser(parts[2], parts[1]);
                    break;
                case "readBook":
                    container.readBook(parts[1], parts[2]);
                    break;
                case "listenBook":
                    container.listenBook(parts[1], parts[2]);
                    break;
                case "subscribe":
                    container.subscribeUser(parts[1]);
                    break;
                case "unsubscribe":
                    container.unsubscribeUser(parts[1]);
                    break;
                case "updatePrice":
                    container.updatePrice(parts[1], parts[2]);
                    break;
                default:
                    return;
            }
        }

    }
}

class Entities{}


class Book extends Entities{
    private String title;
    private String author;
    private String price;

    public Book(String title, String author, String price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public String getName() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
    public String getPrice() {
        return price;
    }
    public void updatePrice(String price) {
        this.price = price;
    }
}

interface UserType {
    public void read(String name, Book book);
    public void listen(String name, Book book);
}

class Standard implements UserType {
    @Override
    public void read(String name, Book book) {
        System.out.println(name + " reading " + book.getName() + " by " + book.getAuthor());
    }
    @Override
    public void listen(String name, Book book) {
        System.out.println("No access");
    }
}

class Premium implements UserType {
    @Override
    public void read(String name, Book book) {
        System.out.println(name + " reading " + book.getName() + " by " + book.getAuthor());
    }
    @Override
    public void listen(String name, Book book) {
        System.out.println(name + " listening " + book.getName() + " by " + book.getAuthor());
    }
}

class User extends Entities{
    
    private UserType type;
    private String name;
    private boolean subscribed = false;

    public User(String name, String type) {
        this.name = name;
        if (type.equals("standard")) {
            this.type = new Standard();
        } else if (type.equals("premium")) {
            this.type = new Premium();
        }
    }
    public String getName() {
        return name;
    }

    public void read(String name, Book book) {
        type.read(name, book);
    }
    public void listen(String name, Book book) {
        type.listen(name, book);
    }
    public void subscribe() {
        if (subscribed) {
            System.out.println("User already subscribed");
            return;
        }
        subscribed = true;
    }
    public void unsubscribe() {
        if (!subscribed) {
            System.out.println("User is not subscribed");
            return;
        }
        subscribed = false;
    }
    public boolean isSubscribed() {
        return subscribed;
    }
}


class Container{

    HashMap<String, Book> books = new HashMap<>();
    HashMap<String, User> users = new HashMap<>();
    List<User> subUsers = new ArrayList<>();

    public Container() {}

    public void createBook(String title, String author, String price){
        if (this.books.containsKey(title)) {
            System.out.println("Book already exists");
            return;
        }
        this.books.put(title, new Book(title, author, price));
    }

    public void createUser(String name, String type){
        if (this.users.containsKey(name)) {
            System.out.println("User already exists");
            return;
        }
        this.users.put(name, new User(name, type));
    }

    public void readBook(String name, String title){
        if (!this.books.containsKey(title) || !this.users.containsKey(name)) {
            return;
        }
        this.users.get(name).read(name, this.books.get(title));
    }

    public void listenBook(String name, String title){
        if (!this.books.containsKey(title) || !this.users.containsKey(name)) {
            return;
        }
        this.users.get(name).listen(name, this.books.get(title));
    }

    public void subscribeUser(String name){
        if (!this.users.containsKey(name)) {
            return;
        }
        if (!subUsers.contains(this.users.get(name))){
            subUsers.add(this.users.get(name));
        }
        this.users.get(name).subscribe();
    }

    public void unsubscribeUser(String name){
        if (!this.users.containsKey(name)){
            return;
        }
        if (subUsers.contains(this.users.get(name))){
            subUsers.remove(this.users.get(name));
        }
        this.users.get(name).unsubscribe();
    }

    public void updatePrice(String title, String price){
        if (!this.books.containsKey(title)) {
            System.out.println("Book does not exist");
            return;
        }
        this.books.get(title).updatePrice(price);

        for (User user : subUsers) {
            System.out.println(user.getName() + " notified about price update for " + title + " to " + price);
        }
    }

}
