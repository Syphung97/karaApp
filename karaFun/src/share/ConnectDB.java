

package share;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sy Phung
 */
public class ConnectDB {
    public static Connection conn;
    private String username = "root";
    String url = "jdbc:mysql://localhost:3306/karafun";
    String dbClass = "com.mysql.jdbc.Driver";
    public ConnectDB() {
      if(conn == null){
          try {
              try {
                  Class.forName(dbClass);
              } catch (ClassNotFoundException ex) {
                  Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
              }
              conn = (Connection) DriverManager.getConnection(url,username,"");
              System.out.println("success");
          } catch (SQLException ex) {
              Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
      
    }
}


