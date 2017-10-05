/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author PRanjan
 */
public class PGConn {

    private static  Connection conn = null;

  
  

    public static Connection  getConn() {
        try {

            if (conn == null) {

                String url = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=postgres&ssl=false";
                conn =  DriverManager.getConnection(url);
                
             
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       return conn;
    }
    
    
 

}
