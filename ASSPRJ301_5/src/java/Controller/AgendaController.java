/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import Dal.EmployeeDAO;
import Dal.ScheduleDAO;
import Model.Employee;
import Model.RequestDTO;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 *
 * @author admin
 */
public class AgendaController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AgendaController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AgendaController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            // Lấy khoảng thời gian từ request (nếu có), mặc định là 9 ngày đầu tháng 1
            String fromDateStr = request.getParameter("fromDate");
            String toDateStr = request.getParameter("toDate");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            
            Date fromDate = fromDateStr != null ? new Date(sdf.parse(fromDateStr).getTime()) 
                    : Date.valueOf("2025-01-01");
            Date toDate = toDateStr != null ? new Date(sdf.parse(toDateStr).getTime()) 
                    : Date.valueOf("2025-01-09");

            // Đảm bảo fromDate không lớn hơn toDate
            if (fromDate.after(toDate)) {
                Date temp = fromDate;
                fromDate = toDate;
                toDate = temp;
            }

            // Lấy danh sách nhân viên
            EmployeeDAO employeeDAO = new EmployeeDAO();
            List<Employee> employees = employeeDAO.getAllEmployee();

            // Lấy danh sách yêu cầu nghỉ phép đã được phê duyệt
            ScheduleDAO scheduleDAO = new ScheduleDAO();
            List<RequestDTO> approvedRequests = scheduleDAO.getApprovedRequestsInRange(fromDate, toDate);

            // Tạo danh sách các ngày để hiển thị
            List<Date> dateRange = new ArrayList<>();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fromDate);
            while (!calendar.getTime().after(toDate)) {
                dateRange.add(new Date(calendar.getTimeInMillis()));
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }

            // Gắn dữ liệu vào request
            request.setAttribute("employees", employees);
            request.setAttribute("approvedRequests", approvedRequests);
            request.setAttribute("dateRange", dateRange);

            // Chuyển tiếp đến Agenda.jsp
            request.getRequestDispatcher("Agenda.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error loading agenda");
        }
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       doGet(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
