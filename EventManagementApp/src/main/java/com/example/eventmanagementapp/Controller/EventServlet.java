package com.example.eventmanagementapp.Controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello")
public class EventServlet extends HttpServlet {
    protected void doGet(HttpServletRequest  req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        PrintWriter out=res.getWriter();
        out.print("<html><body>");
        out.print("<h1>nouhaila</h1>");
        out.print("</body></html>");
    }
}
