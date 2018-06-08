package ru.javawebinar.basejava.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResumeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //  String uuid = "";
        String uuid = "uuid1";
        String name = "Грыцюк Ирина";
        String PERSONAL = "Личные качества";
        String OBJECTIVE = "Позиция";
        String ACHIEVEMENT = "Достижения";
        String QUALIFICATIONS = "Квалификация";
        String listContacts = "Cписок контактов";

        if (uuid.equals("uuid1")) {
            request.setAttribute("name", name);
            request.setAttribute("personal", PERSONAL);
            request.setAttribute("objective", OBJECTIVE);
            request.setAttribute("achievement", ACHIEVEMENT);
            request.setAttribute("qualifications", QUALIFICATIONS);
            request.setAttribute("contacts", listContacts);
        }

        request.getRequestDispatcher("mypage.jsp").forward(request, response);
    }
}
