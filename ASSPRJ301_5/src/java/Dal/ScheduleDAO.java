/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Model.RequestDTO;
import Model.ScheduleDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author admin
 */
public class ScheduleDAO extends DBContext{

    DBContext db = new DBContext();

    public List<ScheduleDTO> getSchedule() {
        List<ScheduleDTO> list = new ArrayList<>();
        String sql = "select  sche.Id,sche.Date,sche.Status,e.Name from Schedule sche inner join Employee e on e.Id = sche.EmployeeId";
        try {
            PreparedStatement st = db.connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ScheduleDTO s = new ScheduleDTO();
                s.setId(rs.getInt(1));
                s.setDate(rs.getDate(2));
                s.setStatus(rs.getBoolean(3));
                s.seteName(rs.getString(4));
                list.add(s);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error retrieving schedule data", ex);
        }
        return list;
    }

    public List<ScheduleDTO> getSchedulebyManagerID(int EmployeeId) {
        List<ScheduleDTO> list = new ArrayList<>();
        String sql = "select  sche.Id,sche.Date,sche.Status,e.Name from Schedule sche inner join Employee e on e.Id"
                + " = sche.EmployeeId where e.Parentemployee = ?";
        try {
            PreparedStatement st = db.connection.prepareStatement(sql);
            st.setInt(1, EmployeeId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ScheduleDTO s = new ScheduleDTO();
                s.setId(rs.getInt(1));
                s.setDate(rs.getDate(2));
                s.setStatus(rs.getBoolean(3));
                s.seteName(rs.getString(4));
                list.add(s);
            }
        } catch (SQLException ex) {
        }
        return list;
    }
    public List<RequestDTO> getApprovedRequestsInRange(Date startDate, Date endDate) {
        List<RequestDTO> list = new ArrayList<>();
        String sql = "SELECT r.Id, r.DateCreate, r.DateFrom, r.DateTo, r.Reason, r.Status, r.EmployeeId, e.Name " +
                     "FROM Request r " +
                     "INNER JOIN Employee e ON e.Id = r.EmployeeId " +
                     "WHERE r.Status = 'Approved' " +
                     "AND (r.DateFrom <= ? AND r.DateTo >= ?)";

        try {
            PreparedStatement st = db.connection.prepareStatement(sql);
            st.setDate(1, endDate);   // Kiểm tra DateFrom <= endDate
            st.setDate(2, startDate); // Kiểm tra DateTo >= startDate
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                RequestDTO r = new RequestDTO();
                r.setId(rs.getInt("Id"));
                r.setDateCreate(rs.getDate("DateCreate"));
                r.setDateFrom(rs.getDate("DateFrom"));
                r.setDateTo(rs.getDate("DateTo"));
                r.setReason(rs.getString("Reason"));
                r.setStatus(rs.getString("Status"));
                r.seteId(rs.getInt("EmployeeId"));
                r.seteName(rs.getString("Name"));
                list.add(r);
            }

            rs.close();
            st.close();
        } catch (SQLException ex) {
            System.out.println("Error retrieving approved requests: " + ex.getMessage());
        }

        return list;
    }

    public static void main(String[] args) {
        ScheduleDAO r = new ScheduleDAO();
        List<ScheduleDTO> l = r.getSchedulebyManagerID(2);

        for (ScheduleDTO scheduleDTO : l) {
            System.out.println(scheduleDTO.getId() + " " + scheduleDTO.getDate());
        }
    }

}
