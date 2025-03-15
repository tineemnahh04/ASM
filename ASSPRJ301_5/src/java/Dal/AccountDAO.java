/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;
import Model.Account;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author admin
 */
public class AccountDAO extends DBContext{
    DBContext db = new DBContext();
    public Account validateUser(String username, String password ){
        String sql = "select * from Account "
                + "where Username = ? AND Password = ? ";
        try {
            PreparedStatement ps = db.connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Account acc = new Account();
                acc.setId(rs.getInt(1));
                acc.setUsername(rs.getString(2));
                acc.setPassword(rs.getString(3));
                acc.setEmployeeId(rs.getInt(4));
                acc.setRoleId(rs.getInt(5));
                return acc;
            }
        } catch (SQLException ex) {
        
    }
    return null;
}
    public List<Account> getAllAccount(){
        List<Account> list = new ArrayList<>();
        String sql = "Select * from [Account]";
        try {
            PreparedStatement st = db.connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Account a = new Account();
                a.setId(rs.getInt(1));
                a.setUsername(rs.getString(2));
                a.setPassword(rs.getString(3));
                a.setEmployeeId(rs.getInt(4));
                a.setRoleId(rs.getInt(5));
                list.add(a);
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public Account getAccount(String Username, String Password){
        String sql = "Select * from Account where Username=? and Password=?";
        try {
            PreparedStatement ps = db.connection.prepareStatement(sql);
            ps.setString(1, Username);
            ps.setString(2, Password);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Account acc = new Account();
                acc.setId(rs.getInt(1));
                acc.setUsername(rs.getString(2));
                acc.setPassword(rs.getString(3));
                acc.setEmployeeId(rs.getInt(4));
                acc.setRoleId(rs.getInt(5));
                return acc;
            }
        } catch (SQLException ex) {
        }
        return null;
    }
    public static void main(String[] args) {
        
    }
}
