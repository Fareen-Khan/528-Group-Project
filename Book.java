package bookstoreapp;
import java.util.ArrayList;
/**
 *
 * @author Ryan Chowdhury
 */
public class Book{
    double price;
    String name;
    public static ArrayList<Book> books = new ArrayList();
    public Book(String bookName, double price){
        this.name = bookName;
        this.price = price;
    }
    public String getName(){
        return this.name;
    }
    public double getPrice(){
        return this.price;
    }
    private void addBook(Book b){
        boolean contains = false;
        for(Book x : books){
            contains = x.getName().equals(b.getName());
        }
        if (!contains){books.add(b);}
    }
    private void removeBook(Book b){
        for(Book x : books){
            if(x.getName().equals(b.getName())){
                books.remove(b);
            }
        }   
    }
}