package project;
import java.util.HashMap;
/**
 *
 * @author Ryan Chowdhury
 */
public abstract class Library {
  public HashMap<String, Double> books = new HashMap<String, Double>();
  private HashMap<Customer, Integer> customers = new HashMap<Customer, Integer>();
  private void addBook(String bookName, double price){
      if (books.containsKey(bookName)){
          System.out.println("Book already exists!");//replace with proper error message later   
      }else{
        books.put(bookName, price);      
      }
  }
  private void removeBook(String bookName){
      books.remove(bookName);
  }
  public double getPrice(String bookName){
      return books.get(bookName);
  }
  public String viewBooks(){
      String view = "";
      for(String key : books.keySet()){
          view += "Book: " + key + "Price: $" + books.get(key) + "\n";
      } 
      return view;
  }
}
