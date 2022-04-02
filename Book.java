package bookstoreapp;
import java.io.*;
import java.util.Scanner;
//import java.util.ArrayList;
/**
 *
 * @author Ryan Chowdhury
 */
public class Book{
    double price;
    String name;
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
    public static void addBook(Book b, File f){
        boolean contains = false;
        try{
            Scanner reader = new Scanner(f);
            while(reader.hasNextLine()){
                String s = reader.nextLine();
                String[] data = s.split(", ");
                contains = b.getName().equals(data[0]);
            }
            reader.close();
        }catch(FileNotFoundException e){
            System.out.println("An error occured.");
        }
        if (!contains){
            try(FileWriter fw = new FileWriter(f, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter add = new PrintWriter(bw))
            {
                add.println(b.getName()+ ", " + b.getPrice());
            } catch (IOException e) {
                System.out.println("An error occured.");
            }
        }    
    }
    public static void removeBook(Book b, File f){
        try {File temp = new File("temp.txt");
            BufferedReader reader = new BufferedReader(new FileReader(f));
            BufferedWriter writer = new BufferedWriter(new FileWriter(temp));
            String remove = b.toString();
            String currentLine;

            while((currentLine = reader.readLine()) != null){
                String s = currentLine.trim();
                if(s.equals(remove)) continue;  
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            
            writer.close();
            reader.close();
            boolean success = temp.renameTo(f);
        } catch (IOException e) {
            System.out.println("An error occured.");
        }
    }
    @Override
    public String toString(){
        return(this.getName() + ", " + this.getPrice());
    }
}
