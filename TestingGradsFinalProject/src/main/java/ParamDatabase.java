import java.sql.*;

/**
 * Created by mhahue on 11/24/2016.
 */
public class ParamDatabase {



    public String retrieveUser(String nameUser, String mailUser) throws SQLException, ClassNotFoundException {
            String dbUrl = "jdbc:mysql://192.168.100.125:3306/drupal";

            //Database Username
            String username = "root";

            //Database Password
            String password = "root";

            //Query to Execute
        String query2="SELECT name, mail FROM users WHERE name='" + nameUser + "' AND mail='" + mailUser + "'";

            //Load mysql jdbc driver
            Class.forName("com.mysql.jdbc.Driver");

            //Create Connection to DB
            Connection con = DriverManager.getConnection(dbUrl,username,password);

            //Create Statement Object
            Statement stmt1 = con.createStatement();

            // Execute the SQL Query. Store results in ResultSet

            ResultSet rs1 = stmt1.executeQuery(query2);

            System.out.println("****************************************************");
            System.out.println("Searching for a specific new entry...");
            while(rs1.next()) {
                nameUser = rs1.getString("name");
                mailUser = rs1.getString("mail");
                System.out.println(nameUser + "------->"+ mailUser);
            }
            // closing DB Connection
            con.close();

        return query2;
        }


}
