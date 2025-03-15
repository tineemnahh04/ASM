/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author admin
 */
public class Employee {
    private int id;
    private String name;
    private Date dob;
    private String email;
    private String phone;
    private Integer parentEmployee;

    public Employee() {
    }

    public Employee(int id, String name, Date dob, String email, String phone, Integer parentEmployee) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.phone = phone;
        this.parentEmployee = parentEmployee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getParentEmployee() {
        return parentEmployee;
    }

    public void setParentEmployee(Integer parentEmployee) {
        this.parentEmployee = parentEmployee;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", name=" + name + ", dob=" + dob + ", email=" + email + ", phone=" + phone + ", parentEmployee=" + parentEmployee + '}';
    }
    
}
