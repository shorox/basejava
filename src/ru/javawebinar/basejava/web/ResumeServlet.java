package ru.javawebinar.basejava.web;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResumeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html: charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write("<html><body>");
        out.write( "<table><tr><td>");
        out.write( "</td></tr></table>");
        out.write( "</body></html>");
        out.flush();
        out.close();
    }
}
