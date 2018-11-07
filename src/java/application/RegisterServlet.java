/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import business.Registration;
import java.io.IOException;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author SMK178
 */
public class RegisterServlet extends HttpServlet {

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
        String method = request.getMethod();
        if (method.toUpperCase().equals("POST")) {
            Registration accountRegistration = new Registration();
            accountRegistration.setUsername(request.getParameter("username"));
            accountRegistration.setPassword(request.getParameter("password"));
            accountRegistration.setConfirm(request.getParameter("confirm"));
            if (!accountRegistration.isValid()) {
                request.getSession().setAttribute("registration", accountRegistration);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("Pages/register.jsp");
                requestDispatcher.include(request, response);
            } else {
                boolean userCreated = createUser(accountRegistration);
                if (userCreated) {
                    request.getSession().setAttribute("message", "Account Created Successfully!");
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("Pages/login.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    request.getSession().setAttribute("message", "Username already exists!");
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("Pages/register.jsp");
                    requestDispatcher.include(request, response);
                }
            }
        } else {
            request.getSession().removeAttribute("registration");
            request.getSession().removeAttribute("message");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("Pages/register.jsp");
            requestDispatcher.forward(request, response);
        }
    }

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
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Registration Page";
    }// </editor-fold>

    private boolean createUser(Registration accountRegistration) {
        Statement statement;
        Connection connection;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String connectionURL = "jdbc:derby://localhost:1527/account_app";
            connection = DriverManager.getConnection(connectionURL, "smk178", "smk178");
            statement = connection.createStatement();
            String insertQuery = "INSERT INTO USERS (Username,Password)"
                    + " VALUES ('"
                    + accountRegistration.getUsername() + "','"
                    + accountRegistration.getPassword() + "')";
            int rows = statement.executeUpdate(insertQuery);
            return rows > 0;
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
            return false;
        } catch (SQLException sqlException) {
            return false;
        }
    }

}
