package javaapplication1;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DBHandler {
    // Initial DB Connection //
  private Connection dbc = null;
  private Statement st = null;
  public enum period {threeMonths,sixMonths,twelveMonths,life;}
  public enum category{cat("Cars and Trucks"),hou("Housing"),elc("Electronics"),cca("Child Care"),all("");
  private String text;
  category(String text){
     this.text = text;
  }
  public String getText(){
      return this.text;
  }
  }
  private period PERIOD;
  private category CATEGORY;
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
              String sql = "SELECT * FROM moderators WHERE User_ID='"+ moderator +"'"; 
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
public ArrayList<Ad> getAllActiveAds(){
      try {
          ArrayList<Ad> allAds = new ArrayList();
          Statement query = st;
          String sql = "SELECT * FROM advertisements WHERE Status_ID='AC'";
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
// Method to get ads based on time and category selected
   public ArrayList<Ad> getCustomAds(String period,String category){
       try {
          ArrayList<Ad> allCustomAds = new ArrayList();
          Statement query = st;
          String sql = "SELECT * FROM advertisements WHERE Status_ID='AC'";
          switch(category.trim()){
              case "Cars and Trucks":
                  sql += " AND Category_ID = 'CAT'";
                  break;
              case "Child Care":
                  sql += " AND Category_ID = 'CCA'";
                  break;
              case "Housing":
                  sql += " AND Category_ID = 'HOU'";
                  break;
               
              case "Electronics":
                  sql += " AND Category_ID = 'ELC'";
                  break;
              case "All":
                  break;
              default:
                  break;
          }
          switch(period.trim()){
              case "3 Months":
                  sql += " AND AdvDateTime > DATE_SUB(DATE(now()), INTERVAL 3 MONTH)";
                  break;
              case "6 Months":
                  sql += " AND AdvDateTime > DATE_SUB(DATE(now()), INTERVAL 6 MONTH)";
                  break;
              case "12 Months":
                  sql += " AND AdvDateTime > DATE_SUB(DATE(now()), INTERVAL 12 MONTH)";
                  break;            
              case "All":
                  break;
              default:                 
                  break;
          }
          ResultSet rs = query.executeQuery(sql);         
          while(rs.next()){
              Ad temp = new Ad();
              temp.setAll(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),rs.getString(8),rs.getString(9));
               allCustomAds.add(temp);
          }
          return allCustomAds;
          
      } catch (SQLException ex) {
          Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
      }
      return null;
   }
}
