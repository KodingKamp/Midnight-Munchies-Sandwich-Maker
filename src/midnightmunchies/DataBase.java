package midnightmunchies;

import com.sun.rowset.CachedRowSetImpl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Class for DataBase Methods used to group all methods dealing with database interaction.
public class DataBase {

  //------- Variables ------
  private static final String dbUrl = "jdbc:derby:lib/books";
  private static Connection connection = null;

  // Method used to connect to the database, called from other methods in this class.
  private static void connect() {
    try {
      connection = DriverManager.getConnection(
          dbUrl, "deitel", "deitel");
    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
    }
  }

  // Method used to disconnect from the database, if connected.
  public static void disconnect() {
    try {
      if (connection != null && !connection.isClosed()) {
        connection.close();
      }
    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
    }
  }

  // Method to execute an SQL statement
  public static void executeQuery(String sqlStatment) {
    //--- Local variable ---
    Statement statement = null;
    try {
      // Call to connect method to connect to the database and
      // then execute statement passed through argument of method.
      connect();
      statement = connection.createStatement();
      statement.executeUpdate(sqlStatment);
    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
    } finally {
      // Afterwards, close statement variable
      if (statement != null) {
        try {
          statement.close();
        } catch (SQLException sqlException) {
          sqlException.printStackTrace();
        }
      }
      // Once done with execution, call to disconnect method to disconnect from database
      disconnect();
    }
  }

  // Method to execute SQL statement with expected Result Set type return value.
  public static ResultSet executeQueryResultSet(String sqlQuery) {
    //--- Local Variables ---
    Statement statement = null;
    ResultSet resultSet = null;
    CachedRowSetImpl crs = null;

    try {
      // Call to connect method to connect to the database and
      // then execute statement passed through argument of method.
      connect();
      statement = connection.createStatement();
      resultSet = statement.executeQuery(sqlQuery);
      // Store resultSet obtained from statement execution into cache
      // to use to return value.
      crs = new CachedRowSetImpl();
      crs.populate(resultSet);
    } catch (SQLException sqlException) {
      sqlException.printStackTrace();
    } finally {
      try {
        // Closet statement and resultSet after execution of SQL statement
        if (statement != null) {
          statement.close();
        }
        if (resultSet != null) {
          resultSet.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
      // Disconnect from the database
      disconnect();
    }
    // Return obtained resultSet stored in cache.
    return crs;
  }
}

