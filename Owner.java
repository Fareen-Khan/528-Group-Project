package project;

import java.io.*;

/**
 * 
 * 
 * 
 * @author Anhr Al-Eidani
 */

public class Owner{

    private String Username;
    private String Password;
    private String Role;    
    
    /**
     * Adds a customer.
     * 
     * 
     * @param username
     * @param password
     */
    public void addCustomer(String username, String password){
        try
        {
            // create a new file 
            File CustomerFile = new File("" + username + ".txt");
            CustomerFile.createNewFile();

            FileWriter Write = new FileWriter("" + username + ".txt");
            Write.write("" + username);
            
            // new line
            Write.write(System.getProperty( "line.separator" ));
            
            Write.write("" + password);
            
             // new line
            Write.write(System.getProperty( "line.separator" ));
            
            Write.write("customer");
            
             // new line
            Write.write(System.getProperty( "line.separator" ));
            
            Write.write("100");
            
            
            // Close the file
            Write.close();
        }
        catch(IOException e){
        }
    }
    
    /**
     * Deletes a customer.
     * 
     * 
     * @param username
     * @param password
     */
    public void deleteCustomer(String username, String password){
        File Customer = new File("" + username + ".txt");
        
        // Check if the file exists or not.
        
        if(Customer.exists()){
            Customer.delete();
        }
        
        else{
            throw new IllegalArgumentException("");
        }
    }
    
    /**
     *  Converts an instance of this object into its String equivalent.
     * 
     * @return 
     */
    @Override
    public String toString(){
        return "" + this.Username + this.Password + this.Role;
    }
    
    public static void main(String[]args){
        try
        {
        Owner own = new Owner();
        own.addCustomer("Bob", "1234");
        
        Customer cus = new Customer("Bob");
        
        own.deleteCustomer(cus.name, "Bob");
        }
        catch(Exception e){ 
        }
    }
}
