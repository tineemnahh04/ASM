/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author admin
 */
public class Account {
    private int id;
    private String username;
    private String password;
    private int employeeId;
    private int roleId;

    public Account() {
    }

    public Account(int id, String username, String password, int employyeeId, int roleId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.employeeId = employyeeId;
        this.roleId = roleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employyeeId) {
        this.employeeId = employyeeId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", username=" + username + ", password=" + password + ", employyeeId=" + employeeId + ", roleId=" + roleId + '}';
    }
    
            
}
