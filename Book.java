package bookstoreapp;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
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
        if (!contains){
            books.add(b);
            try{
                FileWriter in = new FileWriter("books.txt");
                in.write(b.getName() + ", " + b.getPrice());
                in.close();
            }catch(IOException e){
                System.out.println("An error occured");
            }
        }    
    }
    private void removeBook(Book b){
        File f = new File("books.txt");
        for(Book x : books){
            if(x.getName().equals(b.getName())){
                books.remove(b);
                try{
                    Scanner reader = new Scanner(f);
                    FileWriter in = new FileWriter("books.txt");
                    while(reader.hasNextLine()){
                        String s = reader.nextLine();
                        String[] data = s.split(", ");
                        if(b.getName().equals(data[0])){
                            in.write("");
                        }
                    }
                }catch(IOException e){
                    System.out.println("An error occured.");
                }
            }
        }   
    }
}
