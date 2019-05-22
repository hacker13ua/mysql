import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlMain {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/brain",
                "root",
                "root");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
                getQuery("3; "));
        while (rs.next()) {
            System.out.println(rs.getMetaData().getColumnName(1));
            System.out.println(rs.getInt("id") +
                    "  " + rs.getString(2) +
                    "  " + rs.getString(3));
        }
        con.close();

    }

    static String getQuery(String param) {
        return "select * " +
                " from users where id >" + param;
    }

}
