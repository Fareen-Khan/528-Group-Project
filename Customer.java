/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;
/**
 *
 * @author akfar
 */
public class Customer {
    // ---- INSTANCE VARIABLES ----
    //Add Purchase Class as an instance variable
    private String passWord;
    public String userName; 
    private int points;
    public String name;
    private State myState = new Silver();
    
    // ---- CONSTRUCTOR ----
    public Customer(String name) {
        this.name = name;
    }
    // ---- METHODS ----
    
    //Dont need to override viewBooks

    private void addBook(String bookName, double price){
        //Does nothing in the customer class
    }
    private void removeBook(String bookName){
        //Does nothing as it's in the customer class
    }
    public void forward(){
        myState.forward(this);
    }
    public void backward(){
        myState.backward(this);
    }
    public String getState(){
        return myState.getName();
    }
    public void setState(State s){
        myState = s;
    }
    public int getPoints(){
        return points;
    }
    public void setPoints(int p){
        this.points = p;
    }
}
