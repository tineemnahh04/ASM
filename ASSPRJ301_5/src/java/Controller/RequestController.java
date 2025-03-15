/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dal.RequestDAO;
import Model.Account;
import Model.Request;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class RequestController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
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
            out.println("<title>Servlet RequestController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RequestController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            response.sendRedirect("Login");
            return;
        }

        String action = request.getParameter("action");
        // Nếu action=edit, nghĩa là muốn sửa
        if ("edit".equals(action)) {
            // Lấy id
            String idRaw = request.getParameter("id");
            if (idRaw == null) {
                // Không có id => Quay về trang danh sách
                response.sendRedirect("Home");
                return;
            }
            int id = Integer.parseInt(idRaw);

            // Lấy thông tin request từ DB
            RequestDAO requestDAO = new RequestDAO();
            List<Request> req = requestDAO.getRequestbyId(4);  // Viết hàm này trong DAO

            if (req == null) {
                // Không tìm thấy => Quay về trang danh sách
                response.sendRedirect("Home");
                return;
            }

            // Đưa request này lên JSP để hiển thị form
            request.setAttribute("editData", req);
            request.setAttribute("isEdit", true);
            // isEdit=true để JSP biết đang sửa (chứ không phải tạo mới)

            // Forward về Form.jsp
            request.getRequestDispatcher("Form.jsp").forward(request, response);
            return;
        }

        // Mặc định nếu không có action=edit, coi như hiển thị form rỗng (tạo mới)
        // set isEdit = false => JSP biết hiển thị form tạo mới
        request.setAttribute("isEdit", false);
        request.getRequestDispatcher("Form.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    HttpSession session = request.getSession();
    Account account = (Account) session.getAttribute("account");

    // Kiểm tra nếu chưa đăng nhập
    if (account == null) {
        response.sendRedirect("Login");
        return; // Dừng xử lý
    }

    // Lấy dữ liệu từ form
    
    String dateFromStr = request.getParameter("fromDate") != null ? request.getParameter("fromDate").trim() : "";
    String dateToStr = request.getParameter("toDate") != null ? request.getParameter("toDate").trim() : "";
    String reason = request.getParameter("reason") != null ? request.getParameter("reason").trim() : "";
 // trim() là để loại bỏ khoảng trắng
    List<String> error = new ArrayList<>();

    // Kiểm tra dữ liệu trống
    if (dateFromStr.isEmpty() || dateToStr.isEmpty() || reason.isEmpty()) {
        error.add("Vui lòng nhập đầy đủ thông tin.");
    }

    Date dateFrom = null, dateTo = null, now = Date.valueOf(LocalDate.now());

    try {
        dateFrom = Date.valueOf(dateFromStr);
        dateTo = Date.valueOf(dateToStr);

        if (dateFrom.after(dateTo)) {
            error.add("Ngày bắt đầu nghỉ không thể sau ngày kết thúc nghỉ.");
        }
        if (dateTo.before(now)) {
            error.add("Ngày kết thúc nghỉ không thể là quá khứ.");
        }
        if (dateFrom.before(now)) {
            error.add("Ngày bắt đầu nghỉ không thể là quá khứ.");
        }
    } catch (IllegalArgumentException e) {
        error.add("Ngày nhập vào không hợp lệ.");
    }

    // Nếu có lỗi, hiển thị lại form với thông báo
    if (!error.isEmpty()) {
        request.setAttribute("error", error);
        request.getRequestDispatcher("Form.jsp").forward(request, response);
        return; // Dừng xử lý
    }

    // Nếu không có lỗi, tiến hành chèn vào database
    RequestDAO requestDAO = new RequestDAO();
    Request newRequest = new Request(0, account.getEmployeeId(), dateFrom, dateTo, now, reason, "Pending");

    int result = requestDAO.insert(newRequest);

    if (result > 0) {
        response.sendRedirect("Home"); // Chuyển hướng nếu thành công
    } else {
        error.add("Gửi đơn không thành công, vui lòng thử lại.");
        request.setAttribute("error", error);
        request.getRequestDispatcher("Form.jsp").forward(request, response);
    }
}

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
