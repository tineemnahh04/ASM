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

/**
 *
 * @author admin
 */
public class DetailRequest extends HttpServlet {
   
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
            out.println("<title>Servlet DetailRequest</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DetailRequest at " + request.getContextPath () + "</h1>");
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
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            response.sendRedirect("Login");
            return;
        }

        // Lấy tham số id trên URL: detail?id=xxx
        String ID = request.getParameter("id");
        System.out.println("Received id: " + ID);
        if (ID == null || !ID.matches("\\d+")) {
            // Nếu không có id, chuyển về trang danh sách
            response.sendRedirect("Home");
            return;
        }

        int id = Integer.parseInt(ID);

        // Gọi DAO để lấy thông tin đơn theo id
        RequestDAO dao = new RequestDAO();
        Request req = dao.getOneRequestbyId(id, account.getEmployeeId());     // Bạn cần viết phương thức này trong RequestDAO

        if (req == null) {
            // Nếu không tìm thấy đơn, quay về trang danh sách
            System.out.println("Error: Request not found for ID = " + id);
            response.sendRedirect("Home");
            return;
        }

        // Đưa đối tượng Request lên attribute "detail"
        request.setAttribute("detail", req);

        // Forward sang trang detail.jsp
        request.getRequestDispatcher("Detail.jsp").forward(request, response);
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
        processRequest(request, response);
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
