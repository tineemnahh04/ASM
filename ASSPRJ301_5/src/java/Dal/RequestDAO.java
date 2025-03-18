/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Model.Request;
import Model.RequestDTO;
import Model.ScheduleDTO;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

/**
 *
 * @author admin
 */
public class RequestDAO extends DBContext {

    DBContext db = new DBContext();

    public int insert(Request request) {
        int result = -1;
        String sql = "INSERT INTO [dbo].[Request] (EmployeeId, DateTo, DateFrom, DateCreate, Reason, Status) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement st = db.connection.prepareStatement(sql)) {
            st.setInt(1, request.getEmployeeId());
            st.setDate(2, request.getDateTo());
            st.setDate(3, request.getDateFrom());
            st.setDate(4, request.getDateCreate());
            st.setString(5, request.getReason());
            st.setString(6, request.getStatus());

            // Thực hiện lệnh INSERT
            result = st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public int Delete(int Id) {
        int result = -1;
        String sql = "DELETE FROM [dbo].[Request] WHERE Id = ?";
        try {
            PreparedStatement st = db.connection.prepareStatement(sql);
            st.setInt(1, Id);
            result = st.executeUpdate();
        } catch (SQLException ex) {
        }
        return result;
    }

    private int Update(Request request) {
        int result = -1;
        String sql = "UPDATE [dbo].[Request]\n"
                + "      SET [EmployeeId] = ?\n"
                + "      ,[DateTo] = ?\n"
                + "      ,[DateFrom] = ?\n"
                + "      ,[DateCreate] = ?\n"
                + "      ,[Reason] = ?\n"
                + "      ,[Status] = ?\n"
                + " WHERE Id = ?";
        try {
            PreparedStatement st = db.connection.prepareStatement(sql);
            st.setInt(1, request.getEmployeeId());
            st.setDate(2, new java.sql.Date(request.getDateTo().getTime()));
            st.setDate(3, new java.sql.Date(request.getDateFrom().getTime()));
            st.setDate(4, new java.sql.Date(request.getDateCreate().getTime()));

            st.setString(5, request.getReason());
            st.setString(6, request.getStatus());
            result = st.executeUpdate();
        } catch (SQLException ex) {
        }
        return result;
    }

   public List<RequestDTO> getRequestbyManagerID(int managerId) {
        List<RequestDTO> list = new ArrayList<>();
        String sql = "SELECT r.Id, r.DateCreate, r.DateFrom, r.DateTo, r.Reason, r.Status, e.Id AS eId, e.Name AS eName " +
                     "FROM Request r " +
                     "INNER JOIN Employee e ON r.EmployeeId = e.Id " +
                     "WHERE r.EmployeeId = ?"; // Lọc theo EmployeeId của Manager
        try (PreparedStatement st = db.connection.prepareStatement(sql)) {
            st.setInt(1, managerId); // Truyền Manager ID (ví dụ: 2)
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                RequestDTO r = new RequestDTO();
                r.setId(rs.getInt("Id"));
                r.setDateCreate(rs.getDate("DateCreate"));
                r.setDateFrom(rs.getDate("DateFrom"));
                r.setDateTo(rs.getDate("DateTo"));
                r.setReason(rs.getString("Reason"));
                r.setStatus(rs.getString("Status"));
                r.seteId(rs.getInt("eId"));
                r.seteName(rs.getString("eName"));
                list.add(r);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<RequestDTO> getAllRequests() {
        List<RequestDTO> requestList = new ArrayList<>();
        String sql = "SELECT id, employee_name, date_create, date_from, date_to, reason, status FROM Requests";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                RequestDTO request = new RequestDTO();
                request.setId(rs.getInt("id"));
                request.seteName(rs.getString("employee_name"));
                request.setDateCreate(rs.getDate("date_create"));
                request.setDateFrom(rs.getDate("date_from"));
                request.setDateTo(rs.getDate("date_to"));
                request.setReason(rs.getString("reason"));
                request.setStatus(rs.getString("status"));

                requestList.add(request);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return requestList;
    }


    public Request getRequestListById(int id) {
        String sql = "SELECT * FROM [Request] WHERE Id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Request r = new Request();
                r.setId(rs.getInt("Id"));
                r.setEmployeeId(rs.getInt("EmployeeId"));
                r.setDateTo(rs.getDate("DateTo"));
                r.setDateFrom(rs.getDate("DateFrom"));
                r.setDateCreate(rs.getDate("DateCreate"));
                r.setReason(rs.getString("Reason"));
                r.setStatus(rs.getString("Status"));
                return r;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<RequestDTO> getRequestsForManager(int managerId) {
    List<RequestDTO> requestList = new ArrayList<>();
    String sql = "SELECT id, employee_name, date_create, date_from, date_to, reason, status " +
                 "FROM Requests WHERE manager_id = ?";

    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setInt(2, managerId);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                RequestDTO request = new RequestDTO();
                request.setId(rs.getInt("id"));
                request.seteName(rs.getString("employee_name"));
                request.setDateCreate(rs.getDate("date_create"));
                request.setDateFrom(rs.getDate("date_from"));
                request.setDateTo(rs.getDate("date_to"));
                request.setReason(rs.getString("reason"));
                request.setStatus(rs.getString("status"));

                requestList.add(request);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return requestList;
}


    public List<ScheduleDTO> getAllSchedules() {
        List<ScheduleDTO> list = new ArrayList<>();
        String sql = "SELECT s.Id, s.Date, s.Status, e.Name "
                + "FROM Schedule s "
                + "INNER JOIN Employee e ON s.EmployeeId = e.Id";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ScheduleDTO s = new ScheduleDTO(
                        rs.getInt("Id"),
                        rs.getDate("Date"),
                        rs.getBoolean("Status"),
                        rs.getString("Name")
                );
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<RequestDTO> getRequestsForAdmin() {
        List<RequestDTO> list = new ArrayList<>();
        String sql = "SELECT r.Id, r.DateCreate, r.DateFrom, r.DateTo, r.Reason, r.Status, e.Id AS eId, e.Name AS eName "
                + "FROM Request r "
                + "INNER JOIN Employee e ON r.EmployeeId = e.Id "
                + "INNER JOIN Account a ON e.Id = a.EmployeeId "
                + "WHERE a.RoleId = 2"; // Chỉ lấy đơn của Quản lý
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                RequestDTO r = new RequestDTO(
                        rs.getInt("Id"),
                        rs.getDate("DateCreate"),
                        rs.getDate("DateFrom"),
                        rs.getDate("DateTo"),
                        rs.getString("Reason"),
                        rs.getString("Status"),
                        rs.getInt("eId"),
                        rs.getString("eName")
                );
                list.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int UpdateStatusRequest(String Status, int EmployeeId) {
        int result = -1;
        String sql = "UPDATE Request SET Status = ? WHERE EmployeeId = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, Status);
            st.setInt(2, EmployeeId);
            result = st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public int updateRequest(Request r) {
        int result = -1;
        String sql = "UPDATE Request SET dateTo = ?, dateFrom = ?, dateCreate = ?, reason = ?, status = ? WHERE id = ?";

        try (PreparedStatement st = db.connection.prepareStatement(sql)) {
            st.setDate(1, r.getDateTo());
            st.setDate(1, r.getDateFrom());
            st.setDate(1, r.getDateCreate());
            st.setString(4, r.getReason());
            st.setString(5, r.getStatus());
            st.setInt(6, r.getId());
            result = st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public List<RequestDTO> getRequestbyEmployeeId() {
        List<RequestDTO> list = new ArrayList<>();
        String sql = "select r.Id,r.DateCreate,r.DateFrom,r.DateTo,r.Reason,r.Status from Request r where r.EmployeeId=?";
        try {
            PreparedStatement st = db.connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                RequestDTO r = new RequestDTO();
                r.setId(rs.getInt(1));
                r.setDateCreate(rs.getDate(2));
                r.setDateFrom(rs.getDate(3));
                r.setDateTo(rs.getDate(4));
                r.setReason(rs.getString(5));
                r.setStatus(rs.getString(6));
                r.seteId(rs.getInt(7));
                r.seteName(rs.getString(8));
                list.add(r);
            }
        } catch (SQLException ex) {
        }
        return list;
    }

    public List<RequestDTO> getRequestbyEmployeeId(int employeeId) {
        List<RequestDTO> list = new ArrayList<>();
        String sql = "SELECT r.Id, r.DateCreate, r.DateFrom, r.DateTo, r.Reason, r.Status, e.Id AS eId, e.Name AS eName "
                + "FROM Request r "
                + "INNER JOIN Employee e ON r.EmployeeId = e.Id "
                + "WHERE r.EmployeeId = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, employeeId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                RequestDTO r = new RequestDTO();
                r.setId(rs.getInt("Id"));
                r.setDateCreate(rs.getDate("DateCreate"));
                r.setDateFrom(rs.getDate("DateFrom"));
                r.setDateTo(rs.getDate("DateTo"));
                r.setReason(rs.getString("Reason"));
                r.setStatus(rs.getString("Status"));
                r.seteId(rs.getInt("eId"));
                r.seteName(rs.getString("eName"));
                list.add(r);
            }
            System.out.println("getRequestbyEmployeeId: EmployeeId = " + employeeId + ", Số đơn = " + list.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Request> getRequestbyId(int Id) {
        List<Request> list = new ArrayList<>();
        String sql = "select r.Id,r.DateCreate,r.DateFrom,r.DateTo,r.Reason,r.Status from Request r where r.EmployeeId=?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, Id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Request r = new Request();
                r.setId(rs.getInt("Id"));
                r.setDateTo(rs.getDate("DateTo"));
                r.setDateFrom(rs.getDate("DateFrom"));
                r.setDateCreate(rs.getDate("DateCreate"));
                r.setReason(rs.getString("Reason"));
                r.setStatus(rs.getString("Status"));
                list.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
