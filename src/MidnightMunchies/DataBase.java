package midnightmunchies;

import com.sun.rowset.CachedRowSetImpl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {

  private static final String dbUrl = "jdbc:derby:lib/storage";
  private static Connection connection = null;

  public static void connect() {
    try {
      connection = DriverManager.getConnection(
          dbUrl, "deitel", "deitel");
    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
    }
  }

  public static void disconnect() {
    try {
      if (connection != null && !connection.isClosed()) {
        connection.close();
      }
    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
    }
  }

  public static void executeQuery(String sqlStatment) {
    Statement statement = null;
    try {
      connect();
      statement = connection.createStatement();
      statement.executeUpdate(sqlStatment);
    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
    } finally {
      if (statement != null) {
        try {
          statement.close();
        } catch (SQLException sqlException) {
          sqlException.printStackTrace();
        }
      }
      disconnect();
    }
  }

  public static ResultSet executeQueryResultSet(String sqlQuery) {
    Statement statement = null;
    ResultSet resultSet = null;
    CachedRowSetImpl crs = null;

    try {
      connect();
      statement = connection.createStatement();
      resultSet = statement.executeQuery(sqlQuery);
      crs = new CachedRowSetImpl();
      crs.populate(resultSet);
    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
    } finally {
      try {
        if (statement != null) {
          statement.close();
        }
        if (resultSet != null){
          resultSet.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
      disconnect();
    }
    return crs;
  }
}

