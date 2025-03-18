package Dal;

import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

public class DBContext {
   protected Connection connection;

    public DBContext() {
        // Edit URL, username, password to authenticate with your MS SQL Server
        String url = "jdbc:sqlserver://DESKTOP-R3P7RNC\\SQLEXPRESS:1433;databaseName=EmployeeManagement;trustServerCertificate=true;encrypt=false;useUnicode=true;";
        String username = "sa";
        String password = "123";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Kết nối đến cơ sở dữ liệu thành công!");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Lỗi kết nối database!");
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Thêm phương thức này để trả về Connection cho các DAO sử dụng
    public Connection getConnection() {
        return connection;
    }
    
    // Đóng kết nối khi không cần thiết
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        DBContext db = new DBContext();
        if (db.getConnection() != null) {
            System.out.println("Kết nối thành công từ DBContext!");
        } else {
            System.out.println("Kết nối thất bại từ DBContext!");
        }
    }
}

