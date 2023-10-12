package com.example.eventmanagementapp.Controller;

import com.example.eventmanagementapp.Services.EventService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "",loadOnStartup = 1)
public class DefaultServlet extends HttpServlet {
    @Override
    public void init(){
        EventService eventService=new EventService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        response.getWriter().println("Hello from DefaultServlet!");
    }
}
