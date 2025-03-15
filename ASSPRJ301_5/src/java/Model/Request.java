/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.*;

/**
 *
 * @author admin
 */
public class Request {
    private int id;
    private int employeeId;
    private Date dateTo;
    private Date dateFrom;
    private Date dateCreate;
    private String reason;
    private String status;

    public Request() {
    }

    public Request(int id, int employeeId, Date dateTo, Date dateFrom, Date dateCreate, String reason, String status) {
        this.id = id;
        this.employeeId = employeeId;
        this.dateTo = dateTo;
        this.dateFrom = dateFrom;
        this.dateCreate = dateCreate;
        this.reason = reason;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Request{" + "id=" + id + ", employeeId=" + employeeId + ", dateTo=" + dateTo + ", dateFrom=" + dateFrom + ", dateCreate=" + dateCreate + ", reason=" + reason + ", status=" + status + '}';
    }
    
}
