import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * TODO: write javadoc
 *
 * @author Evgeniy Surovskiy
 */
public class PrepStatementExample {
    public static void main(String[] args) throws Exception {
        final byte[] bytes = new byte[2];
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/brain",
                "root",
                "root");
        try {

            con.setAutoCommit(false);
            PreparedStatement stmt = con.prepareStatement(
                    "insert into users(id, firstName, lastName) " +
                            "values (?, ?, ?)");
            stmt.setInt(1, 110);
            stmt.setString(2, "FirstName");
            stmt.setString(3, "LastName");
            System.out.println(stmt.executeUpdate());

//            System.out.println(bytes[5]);

            stmt.setInt(1, 111);
            stmt.setString(2, "FirstName2");
            stmt.setString(3, "LastName2");
            System.out.println(stmt.executeUpdate());


        }catch (Exception e){
            System.out.println("ROLLBACK");
            con.rollback();
        }finally {
            con.commit();
        }
        con.close();
    }
}
