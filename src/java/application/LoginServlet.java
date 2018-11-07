/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import business.Login;
import business.Registration;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sarth
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/Login"})
public class LoginServlet extends HttpServlet {

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
        if (method.equalsIgnoreCase("POST")) {
            Login login = new Login();

            login.setUsername(request.getParameter("username"));

            login.setPassword(request.getParameter("password"));

            boolean isLoginPossible = loginUser(login);

            if (isLoginPossible) {
                request.setAttribute("message", "Login Successfully!");
                request.setAttribute("username", login.getUsername());
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
                requestDispatcher.include(request, response);
            } else {
                request.setAttribute("message", "Username and Password do not match! Please try again later!");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("Pages/login.jsp");
                requestDispatcher.include(request, response);
            }
        } else {
            request.removeAttribute("message");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("Pages/login.jsp");
            requestDispatcher.include(request, response);
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
        return "Short description";
    }// </editor-fold>

    private boolean loginUser(Login credentials) {
        Statement statement;
        Connection connection;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String connectionURL = "jdbc:derby://localhost:1527/account_app";
            connection = DriverManager.getConnection(connectionURL, "smk178", "smk178");
            statement = connection.createStatement();
            String insertQuery = "SELECT COUNT(*) AS Total FROM USERS WHERE "
                    + "Username = '" + credentials.getUsername()
                    + "' AND Password = '" + credentials.getPassword() + "'";
            ResultSet result = statement.executeQuery(insertQuery);
            if (result.next()) {
                int total = result.getInt("Total");
                return total > 0;
            }else{
                return false;
            }
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
            return false;
        } catch (SQLException sqlException) {
            return false;
        }
    }
}
