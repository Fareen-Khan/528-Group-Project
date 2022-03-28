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
public class Gold extends State {
    //Does nothing since Gold does not go forward to anything
    @Override
    public void forward(Customer c){
        
    }
    @Override
    public void backward(Customer c){
        c.setState(new Silver());
    }
}
