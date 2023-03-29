package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author FPT University - PRJ301
 */
public class DBContext {

    protected static  Connection connection;

     static {
        try {   
            String user = "root";
            String pass = "12345678";
            String url = "jdbc:mysql://localhost:3306/swp_dtb";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public DBContext()
    {
        
    }

    public static void main(String[] args) {
        System.out.println(new DBContext().connection);
    }
}
