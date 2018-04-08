/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;

public class ConnectionDB 
{
    public Connection con;
    
    public ConnectionDB()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/onecode","root","");   
            
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    } 
}
