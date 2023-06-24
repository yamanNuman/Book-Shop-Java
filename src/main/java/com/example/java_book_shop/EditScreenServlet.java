package com.example.java_book_shop;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet ("/editScreen")
public class EditScreenServlet extends HttpServlet {
    private static final String query = "SELECT book_name,book_edition,book_price FROM book WHERE id=?";
    private static final String query2 = "UPDATE book SET book_name=?,book_edition=?,book_price=? WHERE id=?";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        //get id
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshop","ymnN","123gsnuman");
            PreparedStatement ps = connect.prepareStatement(query);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            printWriter.println("<div style='text-align:center; color:black'><h2>Book Edit</h2></div>");
            printWriter.println("<form action='editUrl?id="+id+"' method='post'>");
            printWriter.println("<table border='1' align='center'>");
            printWriter.println("<tr>");
            printWriter.println("<td>Book Name</td>");
            printWriter.println("<td><input type='text' name='bookName' value='"+rs.getString(1)+"'></td>");
            printWriter.println("</tr>");
            printWriter.println("<tr>");
            printWriter.println("<td>Book Edition</td>");
            printWriter.println("<td><input type='text' name='bookEdition' value='"+rs.getString(2)+"'></td>");
            printWriter.println("</tr>");
            printWriter.println("<tr>");
            printWriter.println("<td>Book Price</td>");
            printWriter.println("<td><input type='text' name='bookPrice' value='"+rs.getFloat(3)+"'></td>");
            printWriter.println("</tr>");
            printWriter.println("<tr>");
            printWriter.println("<td><input type='submit' value='Edit'></td>");
            printWriter.println("<td><input type='reset' value='Reset'></td>");
            printWriter.println("</tr>");
            printWriter.println("</table>");
            printWriter.println("</form>");

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
