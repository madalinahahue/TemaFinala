import java.sql.SQLException;

/**
 * Created by mhahue on 11/24/2016.
 */
public class ParamDatabase {

    public String retrieveUser(String name) throws SQLException, ClassNotFoundException {

        String query2="SELECT name, mail FROM users WHERE name LIKE '% " + name + "%' AND mail LIKE '%@%' ";

        return query2;
        }


}
