<%@ page import="ru.javawebinar.basejava.model.ContactsType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="css/styles.css">
    <title>Список всех резюме</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<main class="content-wrapper">
    <section class="resume-content">
        <div class="container">
            <div class="top-line">
                <h2>Список резюме</h2>
                <a href="resume?action=new" class="btn btn-add">
                    <i class="fa fa-plus"></i>
                    Добавить</a>
            </div>
            <div class="resume-list">
                <table class="table">
                    <thead class="t-head">
                    <tr>
                        <th>Имя</th>
                        <th>Email</th>
                        <th>Изменить</th>
                        <th>Удалить</th>
                    </tr>
                    </thead>
                    <tbody class="t-body">
                    <c:forEach items="${resumes}" var="resume">
                        <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume"/>
                        <c:choose>
                            <c:when test="${resume.fullName=='Григорий Кислин'}">
                                <tr>

                                    <th><a href="resume?uuid=${resume.uuid}&action=viewnoedit">${resume.fullName}</a></th>
                                    <th><%=ContactsType.MAIL.toHtml(resume.getContacts(ContactsType.MAIL))%>
                                    </th>
                                    <th>
                                        <a>
                                            <i class="fa fa-pencil"></i>
                                        </a>
                                    </th>
                                    <th>
                                        <a>
                                            <i class="fa fa-close"></i>
                                        </a>
                                    </th>
                                </tr>
                            </c:when>
                            <c:otherwise>
                        <tr>
                            <%--<jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume"/>--%>
                            <th><a href="resume?uuid=${resume.uuid}&action=view">${resume.fullName}</a></th>
                            <th><%=ContactsType.MAIL.toHtml(resume.getContacts(ContactsType.MAIL))%>
                            </th>
                            <th>
                                <a href="resume?uuid=${resume.uuid}&action=edit">
                                    <i class="fa fa-pencil"></i>
                                </a>
                            </th>
                            <th>
                                <a href="resume?uuid=${resume.uuid}&action=delete">
                                    <i class="fa fa-close"></i>
                                </a>
                            </th>
                        </tr>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </section>
</main>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>