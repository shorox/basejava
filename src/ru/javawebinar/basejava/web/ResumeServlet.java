package ru.javawebinar.basejava.web;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.storage.Storage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;

import static ru.javawebinar.basejava.util.DateUtil.NOW;

public class ResumeServlet extends HttpServlet {

    private static final String SAVE_DIR = "img" + File.separator;
    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getStorage();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        Resume resume;
        String uuid = UUID.randomUUID().toString();
        String realSavePath = null;

        Map<String, String> map = new HashMap<>();
        List<String> listQualifications = new ArrayList<>();
        List<String> listAchievements = new ArrayList<>();

        int maxFileSize = 512 * 1024; // = 500kb
        String realPath = request.getServletContext().getRealPath("");
        String filePath = realPath + SAVE_DIR;
        String imageSavePath = null;

        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(new File(realPath + "temp"));
        ServletFileUpload upload = new ServletFileUpload(factory);

        try {
            List fileItems = upload.parseRequest(request);
            Iterator iterator = fileItems.iterator();

            while (iterator.hasNext()) {
                FileItem fi = (FileItem) iterator.next();

                if (fi.isFormField()) {
                    String fieldName = fi.getFieldName();
                    String fieldValue = new String(fi.getString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
                    switch (fieldName) {
                        case "QUALIFICATIONS":
                            if (fieldValue != null && fieldValue.trim().length() != 0) {
                                listQualifications.add(fieldValue);
                            }
                            break;
                        case "ACHIEVEMENT":
                            if (fieldValue != null && fieldValue.trim().length() != 0) {
                                listAchievements.add(fieldValue);
                            }
                            break;
                        default:
                            if (fieldName.equals("organizationCounter") || fieldName.equals("positionCounter")) {
                                int counterMap = Integer.valueOf(fieldValue);

                                fieldValue = setMaxCounter(map, fieldValue, counterMap, "organizationCounter");

                                fieldValue = setMaxCounter(map, fieldValue, counterMap, "positionCounter");
                            }
                            if (fieldName.equals("MAIL") && fieldValue.trim().length() == 0) {
                                fieldValue = "empty@mail";
                            }
                            map.put(fieldName, fieldValue);
                            break;
                    }
                }

                if (!fi.isFormField() && fi.getSize() < maxFileSize) {
                    String fileName = fi.getName();
                    String uuidFileName = null;
                    final String uuidForNameImage = map.get("uuid");
                    String uuidName = uuidForNameImage.equals("new") ? uuid : uuidForNameImage;

                    if (fileName.toUpperCase().endsWith(".GIF") ||
                            fileName.toUpperCase().endsWith(".PNG") ||
                            fileName.toUpperCase().endsWith(".JPG")) {
                        uuidFileName = uuidName + fileName.substring(fileName.length() - 4);
                    } else if (fileName.toUpperCase().endsWith(".JPEG")) {
                        uuidFileName = uuidName + fileName.substring(fileName.length() - 5);
                    }

                    if (uuidFileName != null) {
                        imageSavePath = SAVE_DIR + uuidFileName;

                        if (!uuidForNameImage.equals("new")) {
                            String resumeImage = ((Resume) storage.get(uuidForNameImage)).getImagePath();
                            if (!resumeImage.equals("img/user.jpg")) {
                                String removeFile = resumeImage.substring(4);
                                File fileRemove = new File(filePath + removeFile);
                                if (fileRemove.exists()) {
                                    FileUtils.forceDelete(fileRemove);
                                }
                            }
                        }
                        realSavePath = filePath + uuidFileName;
                        File file = new File(realSavePath);
                        fi.write(file);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        String fullName = map.get("fullName");
        String mapUuid = map.get("uuid");
        if (mapUuid.equals("new")) {
            if (imageSavePath == null) {
                imageSavePath = "img/user.jpg";
            }
            resume = new Resume(uuid, fullName, imageSavePath, realSavePath);
        } else {
            resume = (Resume) storage.get(mapUuid);
            resume.setFullName(fullName);
            if (imageSavePath != null) {
                resume.setImagePath(imageSavePath);
            }
            if (realSavePath != null) {
                resume.setRealSavePath(realSavePath);
            }
        }

        for (ContactsType type : ContactsType.values()) {
            String value = map.get(type.name());
            if (value != null && value.trim().length() != 0) {
                resume.addContact(type, value);
            } else {
                resume.getContacts().remove(type);
            }
        }

        for (SectionType typeSection : SectionType.values()) {
            switch (typeSection) {
                case PERSONAL:
                case OBJECTIVE:
                    String value1 = map.get(typeSection.name());
                    if (value1 != null && value1.trim().length() != 0) {
                        resume.addCategory(typeSection, new StringCategory(value1));
                    } else {
                        resume.getSections().remove(typeSection);
                    }
                    break;
                case QUALIFICATIONS:
                    if (!listQualifications.isEmpty()) {
                        resume.addCategory(typeSection, new ListCategory(listQualifications));
                    } else {
                        resume.getSections().remove(typeSection);
                    }
                    break;
                case ACHIEVEMENT:
                    if (!listAchievements.isEmpty()) {
                        resume.addCategory(typeSection, new ListCategory(listAchievements));
                    } else {
                        resume.getSections().remove(typeSection);
                    }
                    break;
                case EDUCATION:
                case EXPERIENCE:
                    List<Organization> listOrganizations = new ArrayList<>();
                    int organizationCounter = Integer.valueOf(map.get("organizationCounter"));
                    int positionCounter = Integer.valueOf(map.get("positionCounter"));

                    for (int i = 0; i <= organizationCounter; i++) {
                        String organizationName = map.get(typeSection.name() + "_organization" + i + "_1name");
                        if (organizationName != null) {
                            List<Organization.Position> listPositions = new ArrayList<>();
                            String organizationUrl = map.get(typeSection.name() + "_organization" + i + "_2url");
                            for (int k = 0; k <= positionCounter; k++) {
                                String title = map.get(typeSection.name() + "_organization" + i + "_position" + k + "_1title");
                                if (title != null) {
                                    String startDate = map.get(typeSection.name() + "_organization" + i + "_position" + k + "_2startDate");
                                    String endDate = map.get(typeSection.name() + "_organization" + i + "_position" + k + "_3endDate");
                                    String description = map.get(typeSection.name() + "_organization" + i + "_position" + k + "_4description");

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
                    break;
                default:
                    break;
            }
        }

        if (map.get("uuid").equals("new")) {
            storage.save(resume);
        } else {
            storage.update(resume);
        }
        response.sendRedirect("resume");
    }

    private String setMaxCounter(Map<String, String> map, String fieldValue, int counterMap, String organizationCounter2) {
        if (map.get(organizationCounter2) != null) {
            int counterOrg = Integer.valueOf(map.get(organizationCounter2));
            if (counterOrg > counterMap) {
                fieldValue = String.valueOf(counterOrg);
            }
        }
        return fieldValue;
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
                resume = new Resume();
                break;
            case "viewnoedit":
            case "noedit":
                resume = (Resume) storage.get(uuid);
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", resume);
        request.getRequestDispatcher(
                ("noedit".equals(action) ? "/WEB-INF/jsp/noedit.jsp" : "view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "edit".equals(action) ? "/WEB-INF/jsp/edit.jsp" :
                        "viewnoedit".equals(action) ? "/WEB-INF/jsp/viewnoedit.jsp" : "/WEB-INF/jsp/new.jsp")
        ).forward(request, response);
    }
}
