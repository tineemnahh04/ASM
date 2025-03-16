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

    public List<Request> getAllRequest() {
        List<Request> list = new ArrayList<>();
        String sql = "select * from [Request]";
        try {
            PreparedStatement st = db.connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Request r = new Request();
                r.setId(rs.getInt(1));
                r.setEmployeeId(rs.getInt(2));
                r.setDateTo(rs.getDate(3));
                r.setDateFrom(rs.getDate(4));
                r.setDateCreate(rs.getDate(5));
                r.setReason(rs.getString(6));
                r.setStatus(rs.getString(7));
                list.add(r);
            }
        } catch (SQLException ex) {
        }
        return list;
    }

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
        String sql = "DELETE FROM [dbo].[Request]\n" + "      WHERE ?";
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
            st.setDate(2, new java.sql.Date(request.getDateFrom().getTime()));
            st.setDate(2, new java.sql.Date(request.getDateCreate().getTime()));
            st.setString(5, request.getReason());
            st.setString(6, request.getStatus());
            result = st.executeUpdate();
        } catch (SQLException ex) {
        }
        return result;
    }

    public List<RequestDTO> getRequestbyManagerID() {
        List<RequestDTO> list = new ArrayList<>();
        String sql = "select r.Id,r.DateCreate,r.DateFrom,r.DateTo,r.Reason,r.Status,e.Id,e.Name from Request r inner join Employee  e on r.EmployeeId = e.Id where e.Parentemployee =2";
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

    public List<Request> getRequestsForManager(int managerId) {
        List<Request> list = new ArrayList<>();
        String sql = "SELECT r.Id, r.EmployeeId, r.DateCreate, r.DateFrom, r.DateTo, r.Reason, r.Status "
                + "FROM Request r "
                + "INNER JOIN Employee e ON r.EmployeeId = e.Id "
                + "WHERE e.ParentEmployee = ? OR e.Id = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, managerId);
            st.setInt(2, managerId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Request r = new Request();
                r.setId(rs.getInt("Id"));
                r.setEmployeeId(rs.getInt("EmployeeId"));
                r.setDateCreate(rs.getDate("DateCreate"));
                r.setDateFrom(rs.getDate("DateFrom"));
                r.setDateTo(rs.getDate("DateTo"));
                r.setReason(rs.getString("Reason"));
                r.setStatus(rs.getString("Status"));
                list.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
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

    public List<RequestDTO> UpdateStatusRequest(String Status, int EmployeeId) {
        List<RequestDTO> list = new ArrayList<>();
        String sql = "update Request SET  Status = '?' where  EmployeeId = ?";
        try {
            PreparedStatement st = db.connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            st.setString(1, Status);
            st.setInt(2, EmployeeId);
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

    public int updateRequest(Request r) {
        int result = -1;
        String sql = "UPDATE Request SET dateTo = ?, dateFrom = ?, dateCreate = ?, reaason = ?, status = ? WHERE id = ?";
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

    public List<Request> getRequestbyEmployeeId(int employeeId) {
        List<Request> list = new ArrayList<>();
        String sql = "select r.Id,r.DateCreate,r.DateFrom,r.DateTo,r.Reason,r.Status from Request r where r.EmployeeId=?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, employeeId);
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

    public static void main(String[] args) {
        RequestDAO dao = new RequestDAO();

        // Gọi phương thức getAllRequest()
        List<Request> list = dao.getAllRequest();

        // Kiểm tra xem có lấy được dữ liệu không
        if (list.isEmpty()) {
            System.out.println("Không có đơn nghỉ phép nào trong database!");
        } else {
            System.out.println("Danh sách đơn nghỉ phép:");
            for (Request r : list) {
                System.out.println(r);
            }
        }
    }

}