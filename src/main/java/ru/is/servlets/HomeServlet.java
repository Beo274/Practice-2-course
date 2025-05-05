//package ru.is.servlets;
//
//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet("/home")
//public class HomeServlet extends HttpServlet {
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//        RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/home.jsp");
//        dispatcher.forward(req,resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//    }
//}
