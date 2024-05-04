import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


/**
 * The main class that has main function that start and invoke all commands
 */
public class Main {

    static Scanner sc;
    static Container container = new Container();
    static CommandInvoker invoker = new CommandInvoker(container);
    /**
     * The main function that reads commands from the standard input and executes
     * corresponding commands. It continuously reads commands until the "end"
     * command is encountered. The supported commands are:
     * - "createBook": creates a new book with the given parameters
     * - "createUser": creates a new user with the given parameters
     * - "readBook": read the book
     * - "listenBook": listen the book
     * - "subscribe": subscribes a user
     * - "unsubscribe": unsubscribes a user
     * - "updatePrice": updates the price of a book
     *
     * @param  args  the command-line arguments
     */

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

/**
 * An interface of types of books and actions that may be done with them
 */
interface BookType{
    /**
     * read the book 
     */
    public void read();
    /**
     * listen the book
     */
    public void listen();
}
/**
 * Textual type of book
 */
class Textual implements BookType{
    /**
     * read the book
     */
    @Override
    public void read() {
        System.out.println("Reading");
    }
    /**
     * try to listen but since book is textual it can not be listened
     */
    @Override
    public void listen() {
        System.out.println("It can not be listened");
    }
}
/**
 * Audio type of book
 */
class Audio implements BookType{
    /**
     * try to read but since book is audio it can not be read
     */
    @Override
    public void read() {
        System.out.println("It can not be readed");
    }
    /**
     * listen the book
     */
    @Override
    public void listen() {
        System.out.println("Listening");
    }
}
/**
 * Audio and textual type of book
 */
class Multype implements BookType{
    /**
     * read the book
     */
    @Override
    public void read() {
        System.out.println("Reading");
    }
    /**
     *  listen the book
     */
    @Override
    public void listen() {
        System.out.println("Listening");
    }
}

/**
 * The book
 */
class Book extends Entities{
    private BookType type;
    private String title;
    private String author;
    private String price;

    /**
     * Creates a new book
     * @param title
     * @param author
     * @param price
     * @param type
     */
    public Book(String title, String author, String price, BookType type) {
        this.type = type;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    /**
     * Reads the book 
     */
    public void read() {
        type.read();
    }
    
    /**
     * Listen the book
     */
    public void listen() {
        type.listen();
    }
    
    /**
     * Return the name of the book
     * @return the name of the object
     */
    public String getName() {
        return title;
    }
    /**
     * Return the type of the book
     * @return the type of the book
     */

    public BookType getType() {
        return type;
    }
    /**
     * Returns the author of the book
     * @return the author of the object as a String
     */

    public String getAuthor() {
        return author;
    }
    
    /**
     * Returns the price of the book
     * @return the price as a string
     */
    public String getPrice() {
        return price;
    }
    
    /**
     * Updates the price of book with the given value
     * @param  price the new price
     */
    public void updatePrice(String price) {
        this.price = price;
    }
}

/**
 * Interface of types of users
 */
interface UserType {
    /**
     * Read the book
     * @param name
     * @param book
     */
    public void read(String name, Book book);
    /**
     * Listen the book
     * @param name
     * @param book
     */
    public void listen(String name, Book book);
}
/**
 * standard type of User
 */
class Standard implements UserType {
    /**
     * A method that prints the user reading a book
     * @param  name the name of the user
     * @param  book the book being read
     */

    @Override
    public void read(String name, Book book) {
        System.out.println(name + " reading " + book.getName() + " by " + book.getAuthor());
    }
    /**
     * A method that prints that user can not listen the book
     * @param  name	description of parameter
     * @param  book	description of parameter
     */

    @Override
    public void listen(String name, Book book) {
        System.out.println("No access");
    }
}
/**
 * premium type of User
 */
class Premium implements UserType {
    /**
     * A method that prints the user reading a book
     * @param  name  the name of the user reading the book
     * @param  book  the book being read
     */
    @Override
    public void read(String name, Book book) {
        System.out.println(name + " reading " + book.getName() + " by " + book.getAuthor());
    }
    
    /**
     * A method that prints the user listening a book.
     * @param  name  the name of the user listening the book
     * @param  book  the book being listened
     */
    @Override
    public void listen(String name, Book book) {
        System.out.println(name + " listening " + book.getName() + " by " + book.getAuthor());
    }
}

/**
 * Factory of users and books
 */
class Factory{
    /**
     * Creates a new User object based on the provided name and type
     *
     * @param  name  the name of the user
     * @param  type  the type of the user ("standard" or "premium")
     * @return       the newly created User object
     * @throws IllegalArgumentException if an invalid user type is provided
     */
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
    /**
     * Creates a new Book object based on the provided name, author, type, and price
     *
     * @param  name    the name of the book
     * @param  author  the author of the book
     * @param  type    the type of the book ("textual", "audio", or "multype")
     * @param  price   the price of the book
     * @return         the newly created Book object
     * @throws IllegalArgumentException if an invalid book type is provided
     */
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
/**
 * User class with implementation of methods
 */
class User extends Entities {
    private UserType type;
    private String name;
    private boolean subscribed = false;

    /**
     * Constructor of User class
     * @param name
     * @param type
     */
    public User(String name, UserType type) {
        this.name = name;
        this.type = type;
    }
    
    /**
     * Returns the name of the object
     * @return the name of the object
     */
    public String getName() {
        return name;
    }
    /**
     * Read the book
     * @param name
     * @param book
     */
    public void read(String name, Book book) {
        type.read(name, book);
    }
    /**
     * Listen the book
     * @param name
     * @param book
     */
    public void listen(String name, Book book) {
        type.listen(name, book);
    }
    /**
     * Subscribes the user
     */
    public void subscribe() {
        if (subscribed) {
            System.out.println("User already subscribed");
            return;
        }
        subscribed = true;
    }
    /**
     * Unsubscribes the user
     */
    public void unsubscribe() {
        if (!subscribed) {
            System.out.println("User is not subscribed");
            return;
        }
        subscribed = false;
    }
    /**
     * Checks if the user is subscribed
     * @return true if the user is subscribed, false otherwise
     */
    public boolean isSubscribed() {
        return subscribed;
    }
}

/**
 * class that storage all users and books
 */
class Container{

    HashMap<String, Book> books = new HashMap<>();
    HashMap<String, User> users = new HashMap<>();
    List<User> subUsers = new ArrayList<>();

    Factory factory = new Factory();

    /**
     * Constructor of the class
     */
    public Container() {}

    /**
     * Creates a new book
     * @param title
     * @param author
     * @param price
     */
    public void createBook(String title, String author, String price){
        if (this.books.containsKey(title)) {
            System.out.println("Book already exists");
            return;
        }
        Book book = factory.createBook(title, author, "multype", price);
        this.books.put(title, book);
    }
    /**
     * Creates a new user
     * @param name
     * @param type
     */
    public void createUser(String name, String type){
        if (this.users.containsKey(name)) {
            System.out.println("User already exists");
            return;
        }
        User user = factory.createUser(name, type);
        this.users.put(name, user);
    }
    /**
     * Reads the book
     * @param name
     * @param title
     */
    public void readBook(String name, String title){
        if (!this.books.containsKey(title) || !this.users.containsKey(name)) {
            return;
        }
        this.users.get(name).read(name, this.books.get(title));
    }
    /**
     * Listens the book
     * @param name
     * @param title
     */
    public void listenBook(String name, String title){
        if (!this.books.containsKey(title) || !this.users.containsKey(name)) {
            return;
        }
        this.users.get(name).listen(name, this.books.get(title));
    }
    /**
     * Subscribes the user
     * @param name
     */
    public void subscribeUser(String name){
        if (!this.users.containsKey(name)) {
            return;
        }
        if (!subUsers.contains(this.users.get(name))){
            subUsers.add(this.users.get(name));
        }
        this.users.get(name).subscribe();
    }
    /**
     * Unsubscribes the user
     * @param name
     */
    public void unsubscribeUser(String name){
        if (!this.users.containsKey(name)){
            return;
        }
        if (subUsers.contains(this.users.get(name))){
            subUsers.remove(this.users.get(name));
        }
        this.users.get(name).unsubscribe();
    }
    /**
     * Updates the price of the book
     * @param title
     * @param price
     */
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
    /**
     * Executes the command
     * @param command
     */
    public void executeCommand(Command command) {
        command.execute();
    }
}

/**
 * Interface of the command
 */
interface Command{
    /**
     * Execute the command
     */
    public void execute();
}

/**
 * Class of invoker commands 
 */
class CommandInvoker {
    private final Container container;
    /**
     * Constructor of the invoker
     * @param container
     */
    public CommandInvoker(Container container) {
        this.container = container;
    }
    /**
     * Execute the command
     * @param command
     */
    public void executeCommand(Command command) {
        container.executeCommand(command);
    }
}

/**
 * Class of create book command
 */
class createBookCommand implements Command{
    private Container container;
    private String title;
    private String author;
    private String price;
    /**
     * Constructor of the command
     * @param container
     * @param title
     * @param author
     * @param price
     */
    public createBookCommand(Container container, String title, String author, String price) {
        this.container = container;
        this.title = title;
        this.author = author;
        this.price = price;
    }
    /**
     * Execute the command
     */
    @Override
    public void execute() {
        container.createBook(title, author, price);
    }
}
/**
 * Class of create user command
 */
class createUserCommand implements Command{
    private Container container;
    private String name;
    private String type;
    /**
     * Constructor of the command
     * @param container
     * @param name
     * @param type
     */
    public createUserCommand(Container container, String name, String type) {
        this.container = container;
        this.name = name;
        this.type = type;
    }
    /**
     *  Execute the command
     */
    @Override
    public void execute() {
        container.createUser(name, type);
    }
}
/**
 * Class of read book command
 */
class readBookCommand implements Command{
    private Container container;
    private String name;
    private String title;
    /**
     * Constructor of the command
     * @param container
     * @param name
     * @param title
     */
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
/**
 * Class of listen book command
 */
class listenBookCommand implements Command{
    private Container container;
    private String name;
    private String title;
    /**
     * Constructor of the command
     * @param container
     * @param name
     * @param title
     */
    public listenBookCommand(Container container, String name, String title) {
        this.container = container;
        this.name = name;
        this.title = title;
    }
    /**
     * Execute the command
     */
    @Override
    public void execute() {
        container.listenBook(name, title);
    }
}
/**
 * Class of subscribe command
 */
class subscribeCommand implements Command{
    private Container container;
    private String name;
    /**
     * Constructor of the command
     * @param container
     * @param name
     */
    public subscribeCommand(Container container, String name) {
        this.container = container;
        this.name = name;
    }
    /**
     * Execute the command
     */
    @Override
    public void execute() {
        container.subscribeUser(name);
    }
}
/**
 * Class of unsubscribe command
 */
class unsubscribeCommand implements Command{
    private Container container;
    private String name;
    /**
     * Constructor of the command
     * @param container
     * @param name
     */
    public unsubscribeCommand(Container container, String name) {
        this.container = container;
        this.name = name;
    }
    /**
     * Execute the command
     */
    @Override
    public void execute() {
        container.unsubscribeUser(name);
    }
}
/**
 * Class of update price command
 */
class updatePriceCommand implements Command{
    private Container container;
    private String title;
    private String price;
    /**
     * Constructor of the command
     * @param container
     * @param title
     * @param price
     */
    public updatePriceCommand(Container container, String title, String price) {
        this.container = container;
        this.title = title;
        this.price = price;
    }
    /**
     * Execute the command
     */
    @Override
    public void execute() {
        container.updatePrice(title, price);
    }
}