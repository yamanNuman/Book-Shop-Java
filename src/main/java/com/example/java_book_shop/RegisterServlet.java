package com.example.java_book_shop;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet ( "/register")
public class RegisterServlet extends HttpServlet {
    private static final String query = "INSERT INTO book(book_name,book_edition,book_price) VALUES (?,?,?)";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get PrintWriter
        PrintWriter printWriter = resp.getWriter();
        //set ContentType
        resp.setContentType("text/html");
        //Get the Book Info
        String bookName = req.getParameter("bookName");
        String bookEdition = req.getParameter("bookEdition");
        float bookPrice = Float.parseFloat(req.getParameter("bookPrice"));
        //JDBC
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshop","ymnN","123gsnuman");
            PreparedStatement ps = connect.prepareStatement(query);
            ps.setString(1,bookName);
            ps.setString(2,bookEdition);
            ps.setFloat(3,bookPrice);
            int count = ps.executeUpdate();
            if(count == 1){
                printWriter.println("<h2>Record is inserted successfully.</h2>");
            }else {
                printWriter.println("<h2>Record is not inserted successfully.</h2>");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            printWriter.println("<h1>"+e.getMessage()+"</h1>");
        }
        printWriter.println("<a href='home.html'>Home</a>");
        printWriter.println("<br>");
        printWriter.println("<a href='bookList'>Book List</a>");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
