/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
/**
 *
 * @author Ben
 */
public class Main {
    public static void main(String[] args) {
    DBHandler db = new DBHandler();
    NewJFrame loginFrame = new NewJFrame(db);
    loginFrame.setVisible(true);
    
    }

}
 
