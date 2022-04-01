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
public class Silver extends State {
    private String name = "Silver";
    @Override
    public String getName(){
        return name;
    }
    @Override
    public void forward(Customer c){
        c.setState(new Gold());
    }
    //Does nothing silver has no back state
    @Override
    public void backward(Customer c){
        
    }
}
