/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Admin
 */
public class DBHandler {
    // Initial DB Connection //
  public Connection dbc = null;
  public Statement st = null;
  DBHandler(){
    try{
        Class.forName("com.mysql.jdbc.Driver");
        dbc = DriverManager.getConnection("jdbc:mysql://localhost:3306/project2","root","tru$tn01");
        st = dbc.createStatement();
        System.out.println("conn. success!");
        
    } catch(SQLException e){
        System.out.println("conn. failed general");
    } catch (ClassNotFoundException ex) {
          Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
          System.out.println("conn. failed");
          
      }
  }
  // check if user exists
  public boolean checkUser(String username){
      Statement query = st;
      if (!username.contains(" ")){
          try {
              String sql = "SELECT * FROM users WHERE User_ID='"+ username +"'"; 
              ResultSet rs = query.executeQuery(sql);
              return rs.next();
          } catch (SQLException ex) {
              Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
          }
 
  }
      return false;
  }
  // check if moderator exists
  public boolean checkModerator(String moderator){
     Statement query = st;
      if (!moderator.contains(" ")){
          try {
              String sql = "SELECT * FROM users WHERE User_ID='"+ moderator +"'"; 
              ResultSet rs = query.executeQuery(sql);
              return rs.next();
          } catch (SQLException ex) {
              Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
          }
 
  }
      return false;
  }
  // get all Ads in DB
public ArrayList<Ad> getAllAds(){
      try {
          ArrayList<Ad> allAds = new ArrayList();
          Statement query = st;
          String sql = "SELECT * FROM advertisements";
          ResultSet rs = query.executeQuery(sql);
          while(rs.next()){
              Ad temp = new Ad();
              temp.setAll(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),rs.getString(8),rs.getString(9));
               allAds.add(temp);
          }
          return allAds;
          
      } catch (SQLException ex) {
          Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
      }
      return null;
}
// Add more methods here //...
    
}
