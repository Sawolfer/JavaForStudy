import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {


    public static void main(String[] args) {
        Document document = new Passport("Alice",18, 11133);
        Passport passport = new Passport("Bob", 50, 33333);
        Snils snils = new Snils("Carl",7777);
        List<Document> documents = new ArrayList<>();

        documents.add(document);
        documents.add(passport);
        documents.add(snils);
        for (Document doc : documents) {
            if (doc instanceof Expirable && ((Passport)doc).hasExpired()) {
                System.out.printf("Document " + doc.number + " has expired\n");
            } else {
                doc.print();
            }
        }

    }
}