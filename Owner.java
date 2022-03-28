package project;

import java.io.*;

/**
 * 
 * 
 * 
 * @author Anhr
 */

public class Owner{

    private String Username;
    private String Password;
    private String Role;
    private boolean successfulLogin;
    
    public Owner(){
        Username = "admin";
        Password = "admin";
        Role = "owner";
        
        successfulLogin = false;
    }
    
    public String getUsername(){
        return Username;
    }
    
    public String getPassword(){
        return Password;
    }
    
    public boolean getLoginSucess(){
        return this.successfulLogin;
    }
    
    /**
     * 
     * 
     * Effects: Checks if Login is possible and set success flag accordingly.
     * @param username
     * @param password
     */
    public void login(String username, String password){
        // Validate the username and password.
        if(this.Username.equals(username) && this.Password.equals(password) && this.Role.equals("owner")){
            successfulLogin = true;
        }
        
        else{
            successfulLogin = false;
        }
    }
    
    /**
     * 
     * 
     * Effects: Sets the login flag to false.
     */
    public void logout(){
        successfulLogin = false;
    }
    
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
            // Delete the file
            Customer.delete();
        }
        
        else{
            throw new IllegalArgumentException("");
        }
        
        // Delete the object by giving it a null reference
        //customer = null;
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
        
        Customer cus = new Customer("Bob", "1234");
        
        own.deleteCustomer(cus, "Bob");
        }
        catch(Exception e){ 
        }
    }
}
