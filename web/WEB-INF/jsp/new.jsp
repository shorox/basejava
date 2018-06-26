<%@ page import="ru.javawebinar.basejava.model.ContactsType" %>
<%@ page import="ru.javawebinar.basejava.model.SectionType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.*,java.util.Locale" %>
<%@ page import="java.util.UUID" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="css/styles.css">
    <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume" scope="request"/>
    <title>Создание Резюме</title>
    <script src="js/main.js" type="text/javascript"></script>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <main class="content-wrapper">
        <section class="resume-content">
            <div class="container">
                <div class="resume-form">
                    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
                        <input type="hidden" name="uuid" value="${1}">
                            <div class="resume-group group-wide">
                                <dt>Имя:</dt>
                                <input type="text" class="resume-input" name="fullName" placeholder="Имя"
                                       required>
                            </div>
                        <div class="resume-section section-contacts">
                            <h3 class="resume-heading">Контакты</h3>
                        </div>
                        <c:forEach var="type" items="<%=ContactsType.values()%>">
                            <div class="resume-group group-wide">
                                <span>${type.title}</span>
                                <input type="text" class="resume-input" name="${type.name()}">
                            </div>
                        </c:forEach>
                        <c:forEach var="typeSection" items="<%=SectionType.values()%>">
                            <c:if test="${typeSection.name()=='OBJECTIVE'||typeSection.name()=='PERSONAL'}">
                                <div class="resume-section section-postion">
                                    <h3 class="resume-heading">${typeSection.title}</h3>
                                    <div class="resume-group group-center">
                                        <input type="text" class="resume-input" name="${typeSection.name()}">
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${typeSection.name()=='ACHIEVEMENT'||typeSection.name()=='QUALIFICATIONS'}">
                                <div class="resume-section section-postion">
                                    <h3 class="resume-heading-extend">${typeSection.title}</h3>
                                    <c:forEach var="section" items="${resume.getSections(typeSection).getItems()}">
                                        <div class="resume-group group-center">
                                            <input type="text" class="resume-input" name="${typeSection.name()}">
                                        </div>
                                    </c:forEach>
                                </div>
                                <div id="${typeSection.name()}" style="margin-top:3px;">

                                    <a href="javascript:add_feed2('<div style=margin-top:3px;><input type=text size=57 style=height:40px; name=${typeSection.name()}></div>','${typeSection.name()}')">
                                        <img src="img/add.png"> Добавить позицию</a>

                                </div>
                            </c:if>
                            <c:if test="${typeSection.name()=='EXPERIENCE'||typeSection.name()=='EDUCATION'}">
                                <c:choose>
                                    <c:when test="${typeSection.title=='Опыт работы'}">
                                        <c:set var="nameCompany" value="компании" scope="page"/>
                                        <c:set var="nameCompany1" value="компанию" scope="page"/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:set var="nameCompany" value="учереждения" scope="page"/>
                                        <c:set var="nameCompany1" value="учереждение" scope="page"/>
                                    </c:otherwise>
                                </c:choose>
                                <div class="resume-section section-education">
                                    <h3 class="resume-heading">${typeSection.title}</h3>
                                    <div id="${typeSection.name()}">
                                        <a id="myLink" href="#"
                                           onclick="javascript:addOrganization('${typeSection.name()}', 'fieldset', '${typeSection.name()}','${nameCompany}');return false;"><img
                                                src="img/add.png"> Добавить ${nameCompany1}</a>
                                    </div>
                                </div>
                            </c:if>
                            <input type="hidden" id="organizationCounter" name="organizationCounter" value="0">
                            <input type="hidden" id="positionCounter" name="positionCounter" value="0">
                        </c:forEach>
                        <br>
                        <button type="submit" class="btn btn-send" name="save" value="1">Сохранить</button>
                        <button type="button" onclick="window.history.back()" class="btn btn-send" name="CancelEdit" value="1">
                            Отменить
                        </button>
                    </form>
                </div>
            </div>
        </section>
    </main>
    <jsp:include page="fragments/footer.jsp"/>
</body>
</html>
