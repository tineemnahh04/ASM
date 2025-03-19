/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dal.RequestDAO;
import Model.Request;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;

/**
 *
 * @author admin
 */
public class EditRequest extends HttpServlet {

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
            out.println("<title>Servlet EditRequest</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditRequest at " + request.getContextPath() + "</h1>");
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
        String idStr = request.getParameter("id");
        if (idStr != null) {
            int id = Integer.parseInt(idStr);
            RequestDAO requestDAO = new RequestDAO();
            Request req = requestDAO.getRequestbyId(id); // Phương thức này cần có trong DAO
            request.setAttribute("request", req);
            // Forward đến trang edit form (editRequest.jsp)
            request.getRequestDispatcher("EditForm.jsp").forward(request, response);
        } else {
            response.sendRedirect("View");
        }
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
        try {
            int ID = Integer.parseInt(request.getParameter("id"));
            String reason = request.getParameter("reason");
            String dateFrom = request.getParameter("dateFrom");
            String dateTo = request.getParameter("dateTo");


            Request updatedRequest = new Request();
            updatedRequest.setId(ID);
            updatedRequest.setReason(reason);
            updatedRequest.setDateFrom(Date.valueOf(dateFrom));
            updatedRequest.setDateTo(Date.valueOf(dateTo));
            // Set thêm các trường khác nếu cần

            RequestDAO requestDAO = new RequestDAO();
            requestDAO.updateRequest(updatedRequest); // Phương thức updateRequest cần được cài đặt trong DAO

            response.sendRedirect("View");
        } catch (Exception e) {
            e.printStackTrace();
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
