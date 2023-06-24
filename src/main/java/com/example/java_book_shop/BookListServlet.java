package com.example.java_book_shop;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.nimbus.State;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
@WebServlet ("/bookList")
public class BookListServlet extends HttpServlet {
    private static final String query = "SELECT ID,book_name,book_edition,book_price FROM book";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        resp.setContentType("text/html");
        String bookName = req.getParameter("bookName");
        String bookEdition = req.getParameter("bookEdition");
        String bookPrice = req.getParameter("bookPrice");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshop","ymnN","123gsnuman");
            PreparedStatement ps = connect.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            printWriter.println("<div style='text-align:center; color:black'><h2>Book List</h2></div>");
            printWriter.println("<table border='1' align='center'>");
            printWriter.println("<tr>");
            printWriter.println("<th>Book Id</th>");
            printWriter.println("<th>Book Name</th>");
            printWriter.println("<th>Book Edition</th>");
            printWriter.println("<th>Book Price</th>");
            printWriter.println("<th>Edit</th>");
            printWriter.println("<th>Delete</th>");
            printWriter.println("</tr>");
            while(rs.next()){
                printWriter.println("<tr>");
                printWriter.println("<td>" + rs.getInt(1)+"</td>");
                printWriter.println("<td>" + rs.getString(2)+"</td>");
                printWriter.println("<td>" + rs.getString(3)+"</td>");
                printWriter.println("<td>" + rs.getFloat(4)+"</td>");
                printWriter.println("<td><a href='editScreen?id=" +rs.getInt(1)+"'>Edit</a></td>");
                printWriter.println("<td><a href='deleteUrl?id=" +rs.getInt(1)+"'>Delete</a></td>");
                printWriter.println("</tr>");
            }
            printWriter.println("</table>");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        printWriter.println("<div style='text-align:center'><a href='home.html' align='center'>Home</a></div>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
