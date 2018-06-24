package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.storage.Storage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static ru.javawebinar.basejava.util.DateUtil.NOW;

public class ResumeServlet extends HttpServlet {

    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getStorage();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");
        Resume resume = (Resume) storage.get(uuid);
        resume.setFullName(fullName);

        for (ContactsType type : ContactsType.values()) {
            String value = request.getParameter(type.name());
            if (value != null && value.trim().length() != 0) {
                resume.addContact(type, value);
            } else {
                resume.getContacts().remove(type);
            }
        }
        for (SectionType typeSection : SectionType.values()) {
            if (typeSection.name().equals("OBJECTIVE") || typeSection.name().equals("PERSONAL")) {
                String value = request.getParameter(typeSection.name());
                if (value != null && value.trim().length() != 0) {
                    resume.addCategory(typeSection, new StringCategory(value));
                } else {
                    resume.getSections().remove(typeSection);
                }
            }
            if (typeSection.name().equals("QUALIFICATIONS") || typeSection.name().equals("ACHIEVEMENT")) {
                String[] value = request.getParameterValues(typeSection.name());
                List<String> list = new ArrayList<>();
                if (value != null) {
                    for (String v : value) {
                        if (v != null && v.trim().length() != 0) {
                            list.add(v);
                        }
                    }
                }
                if (!list.isEmpty()) {
                    resume.addCategory(typeSection, new ListCategory(list));
                } else {
                    resume.getSections().remove(typeSection);
                }
            }
            if (typeSection.name().equals("EXPERIENCE") || typeSection.name().equals("EDUCATION")) {
                List<Organization> listOrganizations = new ArrayList<>();
                int organizationCounter = Integer.valueOf(request.getParameter("organizationCounter"));
                int positionCounter = Integer.valueOf(request.getParameter("positionCounter"));

                for (int i = 0; i <= organizationCounter; i++) {
                    String organizationName = request.getParameter(typeSection.name() + "_organization" + i + "_1name");
                    if (organizationName != null) {
                        List<Organization.Position> listPositions = new ArrayList<>();
                        String organizationUrl = request.getParameter(typeSection.name() + "_organization" + i + "_2url");
                        for (int k = 0; k <= positionCounter; k++) {
                            String title = request.getParameter(typeSection.name() + "_organization" + i + "_position" + k + "_1title");
                            if (title != null) {
                                String startDate = request.getParameter(typeSection.name() + "_organization" + i + "_position" + k + "_2startDate");
                                String endDate = request.getParameter(typeSection.name() + "_organization" + i + "_position" + k + "_3endDate");
                                String description = request.getParameter(typeSection.name() + "_organization" + i + "_position" + k + "_4description");

                                if (endDate != null && endDate.trim().length() != 0) {
                                    listPositions.add(new Organization.Position(LocalDate.parse(startDate), LocalDate.parse(endDate), title, description));
                                } else {
                                    listPositions.add(new Organization.Position(LocalDate.parse(startDate), NOW, title, description));
                                }
                            }
                        }
                        listOrganizations.add(new Organization(new Link(organizationName, organizationUrl), listPositions));
                    }
                }
                if (!listOrganizations.isEmpty()) {
                    resume.addCategory(typeSection, new OrganizationsCategory(listOrganizations));
                } else {
                    resume.getSections().remove(typeSection);
                }
            }
        }
        storage.update(resume);
        response.sendRedirect("resume");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        Resume resume;
        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            case "view":
            case "edit":
                resume = (Resume) storage.get(uuid);
                break;
            case "new":
                resume = new Resume(UUID.randomUUID().toString(), "");
                storage.save(resume);
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", resume);
        request.getRequestDispatcher(
                ("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "edit".equals(action) ? "/WEB-INF/jsp/edit.jsp" : "/WEB-INF/jsp/new.jsp")
        ).forward(request, response);
    }
}