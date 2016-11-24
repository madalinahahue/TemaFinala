import java.sql.*;

/**
 * Created by mhahue on 11/23/2016.
 */
public class TestingDatabase  {

    public  void connectionToDatabase() throws  ClassNotFoundException, SQLException {
        //Connection URL Syntax: "jdbc:mysql://ipaddress:portnumber/db_name"
        String dbUrl = "jdbc:mysql://192.168.100.125:3306/drupal";

        //Database Username
        String username = "root";

        //Database Password
        String password = "root";

        //Query to Execute
        String query = "select *  from users;";
        String query2 = "SELECT name, mail FROM users WHERE name LIKE '%madalina%' AND mail like '%ceva%' ";

        //Load mysql jdbc driver
        Class.forName("com.mysql.jdbc.Driver");

        //Create Connection to DB
        Connection con = DriverManager.getConnection(dbUrl,username,password);

        //Create Statement Object
        Statement stmt = con.createStatement();
        Statement stmt1 = con.createStatement();

        // Execute the SQL Query. Store results in ResultSet
        ResultSet rs= stmt.executeQuery(query);
        ResultSet rs1 = stmt1.executeQuery(query2);

        System.out.println("****************************************************");
        System.out.println("\n DATABASE");
        System.out.println("****************************************************");

        System.out.println("All users from database:");
        // While Loop to iterate through all data and print results
        while (rs.next()){
            String myName = rs.getString("name");
            String myMail = rs.getString("mail");
            System. out.println(myName + "-->" + myMail);
        }

        System.out.println("****************************************************");
        System.out.println("Searching for a specific new entry...");
        while(rs1.next()) {
           String newUser = rs1.getString("name");
            String newMail = rs1.getString("mail");
            System.out.println(newUser + "------->"+ newMail);
        }
        // closing DB Connection
        con.close();
    }

}
