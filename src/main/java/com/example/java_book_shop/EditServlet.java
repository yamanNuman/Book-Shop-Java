package com.example.java_book_shop;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/editUrl")
public class EditServlet extends HttpServlet {
    private static final String query = "UPDATE book SET book_name=?,book_edition=?,book_price=? WHERE id=?";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        int id  = Integer.parseInt(req.getParameter("id"));
        String bookName = req.getParameter("bookName");
        String bookEdition = req.getParameter("bookEdition");
        float bookPrice = Float.parseFloat(req.getParameter("bookPrice"));
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshop","ymnN","123gsnuman");
            PreparedStatement ps = connect.prepareStatement(query);
            ps.setString(1,bookName);
            ps.setString(2,bookEdition);
            ps.setFloat(3,bookPrice);
            ps.setInt(4,id);
            int count = ps.executeUpdate();
            if(count == 1){
                printWriter.println("<h2>Record is Edited Successfully</h2>");
            } else {
                printWriter.println("<h2>Record is not Edited Successfully</h2>");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        printWriter.println("<div style='text-align:center'><a href='home.html' align='center'>Home</a></div>");
        printWriter.println("<div style='text-align:center'><a href='bookList' align='center'>Book List</a></div>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
