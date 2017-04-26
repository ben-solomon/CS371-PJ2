package javaapplication1;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class DBHandler {
    // Initial DB Connection //
    private String currentUser = "";
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
public void setUser(String user){
    this.currentUser = user;
}
public String getCurrentUser(){
    return this.currentUser;
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
public ArrayList<Ad> getAllUserActiveAds(String username){
    PreparedStatement stmt;
      try {
          ArrayList<Ad> allAds = new ArrayList();
          String sql = "SELECT * FROM advertisements WHERE User_ID=?";
          stmt = dbc.prepareStatement(sql);
          stmt.setString(1,username);
          ResultSet rs = stmt.executeQuery();
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
public ArrayList<Ad> getPendingAds(){
      try {
          ArrayList<Ad> allAds = new ArrayList();
          Statement query = st;
          String sql = "SELECT * FROM advertisements WHERE Status_ID='PN'";
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
public ArrayList<Ad> getMyModAds(String user){
      try {
          ArrayList<Ad> myAds = new ArrayList();
          Statement query = st;
          String sql = "SELECT * FROM advertisements WHERE Moderator_ID='"+ user +"' ORDER BY Status_ID";
          ResultSet rs = query.executeQuery(sql);
          while(rs.next()){
              Ad temp = new Ad();
              temp.setAll(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),rs.getString(8),rs.getString(9));
               myAds.add(temp);
          }
          return myAds;
          
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
    public ArrayList<Ad> getCustomModAds(String period,String category){
       try {
          ArrayList<Ad> allCustomAds = new ArrayList();
          Statement query = st;
          String sql = "SELECT * FROM advertisements WHERE Status_ID IN ('PN')";
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
              Ad tempx = new Ad();
              tempx.setAll(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),rs.getString(8),rs.getString(9));
               allCustomAds.add(tempx);
          }
          return allCustomAds;
          
      } catch (SQLException ex) {
          Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
      }
      return null;
   }

public ArrayList<Ad> searchAll(String text){
     ArrayList<Ad> result=new ArrayList();
     PreparedStatement stmt = null;

     String query = "select Advertisement_ID,AdvTitle,AdvDetails,AdvDateTime,Price,User_ID,Moderator_ID,Category_ID,Status_ID FROM Advertisements WHERE AdvTitle LIKE ? OR AdvDetails LIKE ?";
     try {
            stmt=dbc.prepareStatement(query);
            stmt.setString(1,"%"+text+"%"); //binding the parameter with the given string
            stmt.setString(2,"%"+text+"%"); 
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int Advertisement_ID=rs.getInt("Advertisement_ID");
                String AdvTitle =rs.getString("AdvTitle");
                String AdvDetails =rs.getString("AdvDetails");
                String AdvDateTime =rs.getString("AdvDateTime");
                String Price =rs.getString("Price");
                String User_ID =rs.getString("User_ID");
                String Moderator_ID =rs.getString("Moderator_ID");
                String Category_ID =rs.getString("Category_ID");
                String Status_ID =rs.getString("Status_ID");
                Ad searchresult=new Ad();
                searchresult.setAll(Advertisement_ID,AdvTitle,AdvDetails,AdvDateTime,Price,User_ID,Moderator_ID,Category_ID,Status_ID);
                result.add(searchresult);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return result;
        }


     
     return result;
    }
public ArrayList<Ad> searchUnclaimed(String text){
     ArrayList<Ad> result=new ArrayList();
     PreparedStatement stmt = null;

     String query = "select Advertisement_ID,AdvTitle,AdvDetails,AdvDateTime,Price,User_ID,Moderator_ID,Category_ID,Status_ID FROM Advertisements WHERE AdvTitle LIKE ? OR AdvDetails LIKE ? AND Moderator_ID = NULL";
     try {
            stmt=dbc.prepareStatement(query);
            stmt.setString(1,"%"+text+"%"); //binding the parameter with the given string
            stmt.setString(2,"%"+text+"%"); 
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int Advertisement_ID=rs.getInt("Advertisement_ID");
                String AdvTitle =rs.getString("AdvTitle");
                String AdvDetails =rs.getString("AdvDetails");
                String AdvDateTime =rs.getString("AdvDateTime");
                String Price =rs.getString("Price");
                String User_ID =rs.getString("User_ID");
                String Moderator_ID =rs.getString("Moderator_ID");
                String Category_ID =rs.getString("Category_ID");
                String Status_ID =rs.getString("Status_ID");
                Ad searchresult=new Ad();
                searchresult.setAll(Advertisement_ID,AdvTitle,AdvDetails,AdvDateTime,Price,User_ID,Moderator_ID,Category_ID,Status_ID);
                result.add(searchresult);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return result;
        }


     
     return result;
    }
public void addAd(Ad ad){
    Statement query = st;
    final String sql = "INSERT INTO advertisements (AdvTitle,AdvDetails,AdvDateTime,Price,User_ID,Moderator_ID,Category_ID,Status_ID) values('"+ad.getTitle()+"','"+ad.getDetails()+"',Now(),'"+ad.getPrice()+"','"+ this.currentUser +"',NULL,(SELECT Category_ID FROM categories WHERE CatName ='"+ad.getCategory()+"'),'PN')";
     try {
           query.executeUpdate(sql);
}
     catch (SQLException e) {
        JOptionPane.showMessageDialog(null,"Error adding Advertisement! "+e.getMessage());
        
        }
}
public void updateAd(Ad ad){
    PreparedStatement stmt = null;
    final String sql = "UPDATE advertisements set AdvTitle=?,AdvDetails=?,Price=?,Status_ID='PN',Category_ID=? WHERE Advertisement_ID=?";
     try {
         stmt = dbc.prepareStatement(sql);
         stmt.setString(1,ad.getTitle());
         stmt.setString(2,ad.getDetails());
         stmt.setString(3,ad.getPrice());
         stmt.setString(4,ad.getCategory());
         stmt.setString(5,Integer.toString(ad.getID()));
         stmt.executeUpdate();
}
     catch (SQLException e) {
        JOptionPane.showMessageDialog(null,"Error adding Advertisement! "+e.getMessage());
        
        }
}
public ArrayList<Ad> searchAllActive(String text){
     ArrayList<Ad> result=new ArrayList();
     PreparedStatement stmt = null;

     String query = "select Advertisement_ID,AdvTitle,AdvDetails,AdvDateTime,Price,User_ID,Moderator_ID,Category_ID,Status_ID FROM Advertisements WHERE (AdvTitle LIKE ? OR AdvDetails LIKE ?) AND Status_ID='AC'";
     try {
            stmt=dbc.prepareStatement(query);
            stmt.setString(1,"%"+text+"%"); //binding the parameter with the given string
            stmt.setString(2,"%"+text+"%"); 
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int Advertisement_ID=rs.getInt("Advertisement_ID");
                String AdvTitle =rs.getString("AdvTitle");
                String AdvDetails =rs.getString("AdvDetails");
                String AdvDateTime =rs.getString("AdvDateTime");
                String Price =rs.getString("Price");
                String User_ID =rs.getString("User_ID");
                String Moderator_ID =rs.getString("Moderator_ID");
                String Category_ID =rs.getString("Category_ID");
                String Status_ID =rs.getString("Status_ID");
                Ad searchresult=new Ad();
                searchresult.setAll(Advertisement_ID,AdvTitle,AdvDetails,AdvDateTime,Price,User_ID,Moderator_ID,Category_ID,Status_ID);
                result.add(searchresult);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return result;
        }


     
     return result;
    }
public String getCatID(String catID) throws SQLException{
    Statement stmt = st;
    final String sql = "SELECT (Category_ID) FROM categories WHERE CatName='"+catID+"'";
     try {
         ResultSet rs = stmt.executeQuery(sql);
         if(rs.next()){
          return rs.getString("Category_ID");
     }else{
             return "error";
             }
        
}
     catch (SQLException e) {
        //JOptionPane.showMessageDialog(null,"Error adding Advertisement! 0 "+e.getMessage());
        
        }
        return null;
}
public String getCatName(String catID) throws SQLException{
    PreparedStatement stmt = null;
    final String sql = "SELECT CatName FROM categories WHERE Category_ID=?";
    stmt=dbc.prepareStatement(sql);
    stmt.setString(1, catID);
    
     try {
         ResultSet rs =stmt.executeQuery();
         while(rs.next()){
             JOptionPane.showMessageDialog(null,rs.getString(0)+" GOT STRING getCatName()");
          return rs.getString("Category_ID");
     }
}
     catch (SQLException e) {
        //JOptionPane.showMessageDialog(null,"Error adding Advertisement! 1 "+e.getMessage());
        
        }
        return null;
}
public HashMap<String,String> getCatMap(){
    HashMap<String,String> catmap = new HashMap();
     Statement stmt = st;
     String sql = "SELECT * from categories";
     try{
             ResultSet rs = stmt.executeQuery(sql);
             while(rs.next()){
             catmap.put(rs.getString("Category_ID"),rs.getString("CatName"));
             }
             return catmap;
          
      } catch (SQLException ex) {
          //Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
         JOptionPane.showMessageDialog(null, "error getting dropdown");
      }   
     return catmap;
}
public HashMap<String,String> getCatMap2(){
    HashMap<String,String> catmap = new HashMap();
     Statement stmt = st;
     String sql = "SELECT * from categories";
     try{
             ResultSet rs = stmt.executeQuery(sql);
             while(rs.next()){
             catmap.put(rs.getString("CatName"),rs.getString("Category_ID"));
             }
             return catmap;
          
      } catch (SQLException ex) {
          //Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
         JOptionPane.showMessageDialog(null, "error getting dropdown");
      }   
     return catmap;
}
public void claimAds(int[] AdIDs, String user){
  
          Statement query = st;
          for (int i=0;i<AdIDs.length;i++){
              String sql = "UPDATE advertisements SET Moderator_ID='"+ user +"' WHERE (Advertisement_ID='"+ AdIDs[i] +"' AND Moderator_ID is null)";
              try {
                  query.executeUpdate(sql);
              } catch (SQLException ex) {
                  Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
}
public boolean deleteAd(String id){
    Statement query = st;
    String sql = "DELETE FROM Advertisements WHERE Advertisement_ID ='"+id+"'";
        try {
            query.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            return false;
        }
}
public void approveAds(String[] id){
Statement query = st;
          for(int i=0;i<id.length;i++){
              String sql = "UPDATE Advertisements SET Status_ID='AC' WHERE Advertisement_ID='"+id[i]+"'";
              try {
                  query.executeUpdate(sql);
              } catch (SQLException ex) {
                  Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
}
public void disapproveAds(String[] id){
Statement query = st;
          for(int i=0;i<id.length;i++){
              String sql = "UPDATE Advertisements SET Status_ID='DI' WHERE Advertisement_ID='"+id[i]+"'";
              try {
                  query.executeUpdate(sql);
              } catch (SQLException ex) {
                  Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
}
public String[] getCategories() throws SQLException{
     ArrayList<String> temp = new ArrayList();
     Statement stmt = st;
     String sql = "SELECT CatName from categories";
         try{
             ResultSet rs = stmt.executeQuery(sql);
             while(rs.next()){
                temp.add(rs.getString("CatName"));
             }
             String[] list= new String[temp.size()];
             int i=0;
             for(String j:temp){
                 list[i]=j;i+=1;
             }
             return list;
          
      } catch (SQLException ex) {
          //Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
          //JOptionPane.showMessageDialog(null, "error getting dropdown");
      }
      return null;
    }

}


