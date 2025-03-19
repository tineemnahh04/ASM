/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Model.Account;
import Model.Employee;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class EmployeeDAO extends DBContext {

    DBContext db = new DBContext();

    public List<Employee> getAllEmployee() {
        List<Employee> list = new ArrayList<>();
        String sql = "select * from [Employee]";
        try {
            PreparedStatement st = db.connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Employee e = new Employee();
                e.setId(rs.getInt(1));
                e.setName(rs.getString(2));
                e.setDob(rs.getDate(3));
                e.setEmail(rs.getString(4));
                e.setPhone(rs.getString(5));
                e.setParentEmployee(rs.getInt(6));
                list.add(e);
            }
        } catch (SQLException ex) {
        }
        return list;
    }

    public static void main(String[] args) {
        EmployeeDAO e = new EmployeeDAO();
        List<Employee> l = e.getAllEmployee();
        System.out.println(l.get(0));
    }
}
