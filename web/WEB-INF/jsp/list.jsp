<%@ page import="ru.javawebinar.basejava.model.ContactsType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="img/favicon2.ico" type="image/x-icon">
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Список всех резюме</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<main>
    <section class="section-margin-top">
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
                        <th>Полное имя</th>
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
                                    <th><a href="resume?uuid=${resume.uuid}&action=viewnoedit"
                                           class="a-table">${resume.fullName}</a></th>
                                    <th>
                                        <a class="a-table"><%=ContactsType.MAIL.toHtml(resume.getContacts(ContactsType.MAIL))%>
                                        </a>
                                    </th>
                                    <th>
                                        <a class="a-table">
                                            <i class="fa fa-pencil"></i>
                                        </a>
                                    </th>
                                    <th>
                                        <a class="a-table">
                                            <i class="fa fa-close"></i>
                                        </a>
                                    </th>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <th><a href="resume?uuid=${resume.uuid}&action=view"
                                           class="a-table">${resume.fullName}</a></th>
                                    <th>
                                        <a class="a-table"><%=ContactsType.MAIL.toHtml(resume.getContacts(ContactsType.MAIL))%>
                                        </a>
                                    </th>
                                    <th>
                                        <a href="resume?uuid=${resume.uuid}&action=edit" class="a-table">
                                            <i class="fa fa-pencil"></i>
                                        </a>
                                    </th>
                                    <th>
                                        <a href="resume?uuid=${resume.uuid}&action=delete" class="a-table">
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