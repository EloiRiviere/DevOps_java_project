/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author Rivière Eloi
 */
public class Base {    
    public static int postNewScore(int scorej1, int scorej2){
        int result = 0;
        
        String classname = "oracle.jdbc.driver.OracleDriver";
        Exception e = new Exception();
        try
        {
            Class.forName(classname);
            String url = "jdbc:oracle:thin:@iutdoua-oracle.univ-lyon1.fr:1521:orcl";
            
            // Connexion
            Connection connection = DriverManager.getConnection(url,"p1602081","266644"); 
            
            // Objet gérant les requêtes
            Statement statement = connection.createStatement();
            
            String requete = "insert into culdechouette values(sysdate," + scorej1 + ", " + scorej2 + ")";
            
            result = statement.executeUpdate(requete);
            connection.commit();
            
            statement.close();           
            
        }
        catch(ClassNotFoundException cnfe)
        {
            e = cnfe;
            System.err.println("Classe inexistante : " + classname);
        }
        catch(SQLException sqle)
        {
            e = sqle;
            System.err.println("Erreur SQL : DriverManager.getConnection()");
        }
        catch(Exception exception)
        {
            System.err.println("Erreur inconnue");
        }
      
        return result;
    }
}
