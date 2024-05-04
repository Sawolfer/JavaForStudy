import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner sc;
    static Container container = new Container();
    static CommandInvoker invoker = new CommandInvoker(container);
    public static void main(String[] args) {
        sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            String[] parts = command.split(" ");
            switch (parts[0]) {
                case "end":
                    return;
                case "createBook":
                    invoker.executeCommand(new createBookCommand(container, parts[1], parts[2], parts[3]));
                    break;
                case "createUser":
                    invoker.executeCommand(new createUserCommand(container, parts[2], parts[1]));
                    break;
                case "readBook":
                    invoker.executeCommand(new readBookCommand(container, parts[1], parts[2]));
                    break;
                case "listenBook":
                    invoker.executeCommand(new listenBookCommand(container, parts[1], parts[2]));
                    break;
                case "subscribe":
                    invoker.executeCommand(new subscribeCommand(container, parts[1]));
                    break;
                case "unsubscribe":
                    invoker.executeCommand(new unsubscribeCommand(container, parts[1]));
                    break;
                case "updatePrice":
                    invoker.executeCommand(new updatePriceCommand(container, parts[1], parts[2]));
                    break;
                default:
                    return;
            }
        }

    }
}

class Entities{}



interface BookType{
    public void read();
    public void listen();
}

class Textual implements BookType{
    @Override
    public void read() {
        System.out.println("Reading");
    }
    @Override
    public void listen() {
        System.out.println("It can not be listened");
    }
}

class Audio implements BookType{
    @Override
    public void read() {
        System.out.println("It can not be readed");
    }
    @Override
    public void listen() {
        System.out.println("Listening");
    }
}

class Multype implements BookType{
    @Override
    public void read() {
        System.out.println("Reading");
    }
    @Override
    public void listen() {
        System.out.println("Listening");
    }
}

class Book extends Entities{
    private BookType type;
    private String title;
    private String author;
    private String price;


    public Book(String title, String author, String price, BookType type) {
        this.type = type;
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

//Factory pattern - Creational

class Factory{
    public User createUser(String name, String type) {
        if (type.equals("standard")) {
            User user = new User(name, new Standard());
            return user;
        } else if (type.equals("premium")) {
            User user = new User(name, new Premium());
            return user;
        }
        throw new IllegalArgumentException("Invalid user type");
    }

    public Book createBook(String name, String author, String type, String price) {
        if (type.equals("textual")) {
            return new Book(name, author, price, new Textual());
        } else if (type.equals("audio")) {
            return new Book(name, author, price, new Audio());
        } else if (type.equals("multype")) {
            return new Book(name, author, price, new Multype());
        }
        throw new IllegalArgumentException("Invalid book type");
    }
}

class User extends Entities{
    private UserType type;
    private String name;
    private boolean subscribed = false;

    public User(String name, UserType type) {
        this.name = name;
        this.type = type;
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

    Factory factory = new Factory();

    public Container() {}

    public void createBook(String title, String author, String price){
        if (this.books.containsKey(title)) {
            System.out.println("Book already exists");
            return;
        }
        Book book = factory.createBook(title, author, "multype", price);
        this.books.put(title, book);
    }

    public void createUser(String name, String type){
        if (this.users.containsKey(name)) {
            System.out.println("User already exists");
            return;
        }
        User user = factory.createUser(name, type);
        this.users.put(name, user);
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

    public void executeCommand(Command command) {
        command.execute();
    }
}

//Command pattern - Behavioral

interface Command{
    public void execute();
}

class CommandInvoker {
    private final Container container;

    public CommandInvoker(Container container) {
        this.container = container;
    }

    public void executeCommand(Command command) {
        container.executeCommand(command);
    }
}

class createBookCommand implements Command{
    private Container container;
    private String title;
    private String author;
    private String price;

    public createBookCommand(Container container, String title, String author, String price) {
        this.container = container;
        this.title = title;
        this.author = author;
        this.price = price;
    }
    @Override
    public void execute() {
        container.createBook(title, author, price);
    }
}

class createUserCommand implements Command{
    private Container container;
    private String name;
    private String type;

    public createUserCommand(Container container, String name, String type) {
        this.container = container;
        this.name = name;
        this.type = type;
    }
    @Override
    public void execute() {
        container.createUser(name, type);
    }
}

class readBookCommand implements Command{
    private Container container;
    private String name;
    private String title;

    public readBookCommand(Container container, String name, String title) {
        this.container = container;
        this.name = name;
        this.title = title;
    }
    @Override
    public void execute() {
        container.readBook(name, title);
    }
}

class listenBookCommand implements Command{
    private Container container;
    private String name;
    private String title;

    public listenBookCommand(Container container, String name, String title) {
        this.container = container;
        this.name = name;
        this.title = title;
    }
    @Override
    public void execute() {
        container.listenBook(name, title);
    }
}

class subscribeCommand implements Command{
    private Container container;
    private String name;

    public subscribeCommand(Container container, String name) {
        this.container = container;
        this.name = name;
    }
    @Override
    public void execute() {
        container.subscribeUser(name);
    }
}

class unsubscribeCommand implements Command{
    private Container container;
    private String name;

    public unsubscribeCommand(Container container, String name) {
        this.container = container;
        this.name = name;
    }
    @Override
    public void execute() {
        container.unsubscribeUser(name);
    }
}

class updatePriceCommand implements Command{
    private Container container;
    private String title;
    private String price;

    public updatePriceCommand(Container container, String title, String price) {
        this.container = container;
        this.title = title;
        this.price = price;
    }
    @Override
    public void execute() {
        container.updatePrice(title, price);
    }
}