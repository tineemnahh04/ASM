/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Model.Role;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author admin
 */
public class RoleDAO extends DBContext{
    DBContext db=new DBContext();
    
    public List<Role> getAllRole() {
    List<Role> list = new ArrayList<>();
    String sql = "select * from [Role]";
    try (PreparedStatement st = db.connection.prepareStatement(sql);
         ResultSet rs = st.executeQuery()) {
        while (rs.next()) {
            Role rl = new Role();
            rl.setId(rs.getInt(1));
            rl.setName(rs.getString(2));
            rl.setDescription(rs.getString(3));
            list.add(rl);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}
    public static void main(String[] args) {
        RoleDAO rl=new RoleDAO();
        List<Role> l=rl.getAllRole();
        System.out.println(l.get(0));
}
}
