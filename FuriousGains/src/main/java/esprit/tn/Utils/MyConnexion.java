package esprit.tn.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnexion {

    static final String URL = "jdbc:mysql://localhost:3306/FuriousGains";
    static final String USER = "root";
    static final String PASSWORD = "";
    private Connection cnx;
    private static MyConnexion instance;

    public Connection getCnx() {
        return cnx;
    }

    private MyConnexion() {
        try {
            cnx = DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("Connection established");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }


    }

    public static MyConnexion getInstance(){
        if(instance == null)
            instance = new MyConnexion();
        return instance;

    }

}
