/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlinjection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author alex2
 */
public class SQLInjection {
    //Connection to DB need to change it to your localhost DB
    private static final String USERNAME = "root";
    private static final String PASSWORD= "2GetOnMySQL24!";
    private static final String CONN_STRING= "jdbc:mysql://localhost:3306/sqlinjection";
    public static void main(String[] args) {
        // TODO code application logic here
    }
       //Method for user login
   public static int login(String username, String password){
       
       Connection conn = null;
       PreparedStatement pst=null;
       ResultSet rs=null;
       int flag;
       
       for(int i = 0; i< username.length(); i++){
           if(username.charAt(i)==('\'')){
               JOptionPane.showMessageDialog(null, "The inpute has been waved due to SQL Injection risks!","Access Denied",JOptionPane.ERROR_MESSAGE); 
               return 0;
           }
       }
       
        try{
           conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
           String Sql = "Select * from username where password = '" + password + "' AND username = '" + username + "'";
           
           
           System.out.println("Connected!");
           pst = conn.prepareStatement(Sql);

           rs=pst.executeQuery();
           if(rs.next()){
               System.out.println("User has logged in successfully.");
               flag = 1;
               return flag;
           }
           else{
               JOptionPane.showMessageDialog(null, "Invalid Username or Password","Access Denied",JOptionPane.ERROR_MESSAGE);              
           }
   
        } catch (SQLException e){
            System.err.println(e);
        }
       return 0;
   }
    
}
