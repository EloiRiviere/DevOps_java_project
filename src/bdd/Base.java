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
    public static void main(String args[])
    {
        String classname = "oracle.jdbc.driver.OracleDriver";
        Exception e = new Exception();
        try
        {
            Class.forName(classname);
            String url = "jdbc:oracle:thin:@iutdoua-oracle.univ-lyon1.fr:1521:orcl";
            
            // Connexion
            Connection connection = DriverManager.getConnection(url,"p1925581","477879"); 
            
            // Objet gérant les requêtes
            Statement statement = connection.createStatement();
            
            String requete = "select * from proprietaire";
            System.out.println(requete);
            
            ResultSet result = statement.executeQuery(requete);
            
            System.out.println("Numéro | Nom propriétaire | Prénom propriétaire");
            
            while(result.next())
            {
                System.out.println(result.getInt(1) + " | " + result.getString("nomproprio") + " | " + result.getString("prenomproprio"));
            }
            
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
        finally
        {
            e.printStackTrace();
        }
        
    }
}
