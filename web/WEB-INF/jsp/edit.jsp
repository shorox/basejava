<%@ page import="ru.javawebinar.basejava.model.ContactsType" %>
<%@ page import="ru.javawebinar.basejava.model.SectionType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.*,java.util.Locale" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="css/styles.css">
    <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume" scope="request"/>
    <title>Редактирование ${resume.fullName}</title>
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
                        <input type="hidden" name="uuid" value="${resume.uuid}">
                            <div class="resume-group group-wide">
                                <span>Имя:</span>
                                <input type="text" class="resume-input" name="fullName"
                                       value="${resume.fullName}" placeholder="Имя" required>
                            </div>
                        <div class="resume-section section-contacts">
                            <h3 class="resume-heading">Контакты</h3>
                        </div>
                        <c:forEach var="type" items="<%=ContactsType.values()%>">
                            <div class="resume-group group-wide">
                                <span>${type.title}</span>
                                <input type="text" class="resume-input" name="${type.name()}"
                                       value="${resume.getContacts(type)}">
                            </div>
                        </c:forEach>
                        <c:forEach var="typeSection" items="<%=SectionType.values()%>">
                            <c:if test="${typeSection.name()=='OBJECTIVE'||typeSection.name()=='PERSONAL'}">
                                <div class="resume-section section-postion">
                                    <h3 class="resume-heading">${typeSection.title}</h3>
                                    <div class="resume-group group-center">
                                        <input type="text" class="resume-input" name="${typeSection.name()}"
                                               value="${resume.getSections(typeSection)}">
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${typeSection.name()=='ACHIEVEMENT'||typeSection.name()=='QUALIFICATIONS'}">
                                <div class="resume-section section-postion">
                                    <h3 class="resume-heading-extend">${typeSection.title}</h3>
                                    <div id="${typeSection.name()}" class="resume-heading-extend"
                                         style="margin-top:3px;">

                                        <a href="javascript:add_feed2('<div style=margin-top:3px;><input type=text class=resume-input name=${typeSection.name()}></div>','${typeSection.name()}')">
                                            <img src="img/add.png"> Добавить позицию</a>
                                    </div>
                                    <div style="margin-top:3px;"></div>
                                    <c:forEach var="section" items="${resume.getSections(typeSection).getItems()}">
                                        <div class="resume-group group-center">
                                            <input type="text" class="resume-input"
                                                   name="${typeSection.name()}" value="${section}">
                                        </div>
                                    </c:forEach>
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
                                        <p></p>
                                    </div>
                                    <c:set var="count" value="0" scope="page"/>
                                    <c:forEach var="organization"
                                               items="${resume.getSections(typeSection).getOrganizations()}">
                                        <fieldset id="${typeSection.name()}_organization${count}">
                                        <a id="myLink2" href="#"
                                           onclick="javascript:deleteElement('${typeSection.name()}_organization${count}');return false;"><img
                                                src="img/remove.png"> Удалить организацию</a>
                                        <div class=\"education-block\">
                                            <div class="resume-group group-wide">
                                                <span>Название  ${nameCompany}  :</span>
                                                <input type="text" class="resume-input"
                                                       name="${typeSection.name()}_organization${count}_1name"
                                                       value="${organization.getHomePage().getName()}" required>
                                            </div>
                                        </div>
                                        <div class="resume-group group-wide">
                                            <span>Сайт ${nameCompany} :</span>
                                            <input type="text" class="resume-input"
                                                   name="${typeSection.name()}_organization${count}_2url"
                                                   value="${organization.getHomePage().getUrl()}">
                                        </div>
                                        <br>
                                        <a id="myLink1" href="#"
                                           onclick="javascript:addPosition('${typeSection.name()}_organization${count}', 'fieldset', '${typeSection.name()}_organization0');return false;"><img
                                                src="img/add.png"> Добавить позицию</a>
                                        <p>
                                        <c:set var="countPosition" value="0" scope="page"/>
                                        <c:forEach var="position" items="${organization.getPositions()}">
                                            <fieldset
                                                    id="${typeSection.name()}_organization${count}_position${countPosition}">
                                                <a id="myLink3" href="#"
                                                   onclick="javascript:deleteElement('${typeSection.name()}_organization${count}_position${countPosition}');return false;"><img
                                                        src="img/remove.png"> Удалить позицию</a>
                                            <p>
                                            <div class="resume-group group-wide">
                                                <span> Дата начала: </span>
                                                <input type="date"
                                                       name="${typeSection.name()}_organization${count}_position${countPosition}_2startDate"
                                                       value="${position.getStartDate()}" required>
                                                <span> Дата окончания: </span>
                                                <input type="date"
                                                       name="${typeSection.name()}_organization${count}_position${countPosition}_3endDate"
                                                       value="${position.getEndDate()}">
                                            </div>
                                            <div class="resume-group group-wide">
                                                <span>Должность:</span>
                                                <input type="text" class="resume-input"
                                                       name="${typeSection.name()}_organization${count}_position${countPosition}_1title"
                                                       value="${position.getTitle()}" required>
                                            </div>
                                            <div class="resume-group group-wide">
                                                <span>Описание:</span>
                                                <input type="text" class="resume-input"
                                                       name="${typeSection.name()}_organization${count}_position${countPosition}_4description"
                                                       value="${position.getDescription()}">
                                            </div>
                                            </fieldset>
                                            <c:set var="countPosition" value="${countPosition+1}" scope="page"/>
                                        </c:forEach>
                                        </fieldset>
                                        <c:set var="count" value="${count+1}" scope="page"/>
                                    </c:forEach>
                                </div>
                                <c:if test="${not empty count}">
                                    <input type="hidden" id="organizationCounter" name="organizationCounter"
                                           value="${count}">
                                </c:if>
                                <c:if test="${empty count}">
                                    <input type="hidden" id="organizationCounter" name="organizationCounter" value="0">
                                </c:if>
                                <c:if test="${not empty countPosition}">
                                    <input type="hidden" id="positionCounter" name="positionCounter"
                                           value="${countPosition}">
                                </c:if>
                                <c:if test="${empty countPosition}">
                                    <input type="hidden" id="positionCounter" name="positionCounter" value="0">
                                </c:if>
                            </c:if>
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