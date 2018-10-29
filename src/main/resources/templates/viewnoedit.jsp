<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="img/favicon2.ico" type="image/x-icon">
    <link rel="stylesheet" href="css/styles.css">
    <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<main class="content-wrapper">
    <section>
        <div class="container">
            <div class="resume-form">
                <form action="resume" method="POST">
                    <input type="hidden" name="uuid" value="${resume.uuid}">
                    <div class="resume-section">
                        <div class="resume-group">
                            <p class="resume-field resume-fullName">${resume.fullName}</p>
                        </div>
                    </div>
                    <div class="resume-section">
                        <c:if test="${not empty resume.getSections()}">
                            <h3 class="resume-heading">Контакты</h3>
                        </c:if>
                        <div class="resume-group">
                            <p class="resume-field">
                                <c:forEach var="contactEntry" items="${resume.contacts}">
                                    <jsp:useBean id="contactEntry"
                                                 type="java.util.Map.Entry<ru.javawebinar.basejava.model.ContactsType, java.lang.String>"/>
                                    <span><%=contactEntry.getKey().getTitle()%></span>
                                    <%=contactEntry.getKey().toHtml(contactEntry.getValue())%><br>
                                </c:forEach>
                            </p>
                        </div>
                    </div>
                    <div style="margin-top: 65px;">
                        <c:forEach var="sectionEntry" items="${resume.sections}">
                            <jsp:useBean id="sectionEntry"
                                         type="java.util.Map.Entry<ru.javawebinar.basejava.model.SectionType, ru.javawebinar.basejava.model.Category>"/>
                            <div style="margin-top: -25px;">
                                <h3 class="resume-heading"><%=sectionEntry.getKey().getTitle()%>
                                </h3>
                            </div>
                            <c:set var="key" scope="session" value="<%=sectionEntry.getKey()%>"/>
                            <c:set var="value" scope="session" value="<%=sectionEntry.getValue()%>"/>
                            <c:choose>
                                <c:when test="${key=='PERSONAL'||key=='OBJECTIVE'}">
                                    <div class="resume-section">
                                        <div class="resume-group group-center">
                                            <p class="">${value.getContent()}</p>
                                        </div>
                                    </div>
                                </c:when>
                                <c:when test="${key=='ACHIEVEMENT'||key=='QUALIFICATIONS'}">
                                    <div class="resume-section">
                                        <div class="resume-group">
                                            <ul class="resume-props">
                                                <c:forEach var="listItems" items="${value.getItems()}">
                                                    <li>
                                                        <p>${listItems}</p>
                                                    </li>
                                                </c:forEach>
                                            </ul>
                                        </div>
                                    </div>
                                </c:when>
                                <c:when test="${key=='EXPERIENCE'||key=='EDUCATION'}">
                                    <div class="resume-section">
                                        <div class="experience-block">
                                            <c:forEach var="listExp" items="${value.getOrganizations()}">
                                                <div style="background-color:#e3f0f4">
                                                    <c:if test="${not empty listExp.getHomePage().getUrl()}">
                                                        <div class="resume-group" style="padding: 5px 5px 5px 15px;">
                                                            <span style="padding: 0 14px 0 0;">Компания:</span>
                                                            <a href="${listExp.getHomePage().getUrl()}"
                                                               class="resume-field"
                                                               style="padding: 0 5px 0 0;">${listExp.getHomePage().getName()}</a>
                                                        </div>
                                                    </c:if>
                                                    <c:if test="${empty listExp.getHomePage().getUrl()}">
                                                        <div class="resume-group" style="padding: 5px 5px 5px 15px;">
                                                            <span style="padding: 0 14px 0 0;">Компания:</span>
                                                            <p class="resume-field"
                                                               style="padding: 0 5px 0 0;">${listExp.getHomePage().getName()}</p>
                                                        </div>
                                                    </c:if>
                                                </div>
                                                <c:forEach var="listPos" items="${listExp.getPositions()}">
                                                    <div style="background-color: #f2f8f9; ">
                                                        <div class="resume-group" style="padding: 5px 15px 5px 15px;">
                                                            <span style="padding: 0 20px 0 0;"> Период:</span>
                                                            <p class="resume-time"> ${listPos.getStartDate().format(DateTimeFormatter.ofPattern("LL/yyyy"))}</p>
                                                            <p style="margin-top: 2px;">&mdash;</p>
                                                            <c:if test="${listPos.getEndDate()!=LocalDate.of(3000, 1, 1)}">
                                                                <p class="resume-time"> ${listPos.getEndDate().format(DateTimeFormatter.ofPattern("LL/yyyy"))}</p>
                                                            </c:if>
                                                            <c:if test="${listPos.getEndDate()==LocalDate.of(3000, 1, 1)}">
                                                                <p class="resume-time">Сегодня</p>
                                                            </c:if>
                                                        </div>
                                                        <div class="resume-group" style="padding: 5px 5px 5px 15px;">
                                                            <span style="margin-top: -10px;padding: 0 5px 0 0;">Должность:</span>
                                                            <p class="resume-field"
                                                               style="margin-top: -10px;padding: 0 25px 0 0;"> ${listPos.getTitle()}</p>
                                                        </div>
                                                        <div class="resume-group" style="padding: 5px 5px 5px 15px;">
                                                            <span style="padding: 0 15px 0 0;">Описание:</span>
                                                            <p class="resume-field"
                                                               style="padding: 0 25px 0 0;">${listPos.getDescription()}</p>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                                <br>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </c:when>
                            </c:choose>
                            <br/>
                        </c:forEach>
                    </div>
                    <a href="resume?uuid=${resume.uuid}&action=noedit" class="btn btn-add">Редактировать</a>
                </form>
            </div>
        </div>
    </section>
</main>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>