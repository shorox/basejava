<%@ page import="ru.javawebinar.basejava.model.ContactsType" %>
<%@ page import="ru.javawebinar.basejava.model.SectionType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.*,java.util.Locale" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="shortcut icon" href="img/favicon2.ico" type="image/x-icon">
    <link rel="stylesheet" href="css/styles.css">
    <script src="js/main.js" type="text/javascript" charset="UTF-8"></script>
    <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume" scope="request"/>
    <title>Создание Резюме</title>
</head>
<body>

<jsp:include page="fragments/header.jsp"/>

<div class="container mt-5 box-shadow-blue">
    <div class="mx-2">
        <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
            <input type="hidden" name="uuid" value="${1}">

            <p class="nav-href-indent-edit" id="name"></p>
            <div class="row">
                <div class="col">
                    <div class="form-group">
                        <label class="mx-2">Полное имя:</label>
                        <input type="text" class="form-control" name="fullName" required>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col">
                    <div class="form-group">
                        <label>Сохранение фото:</label>
                        <input type="text" class="form-control" name="image1" placeholder="ссылка на фото формата GIF, JPEG, PNG">
                        <input type="file" class="form-control" name="image" placeholder="ссылка на фото формата GIF, JPEG, PNG">
                    </div>
                </div>
            </div>

            <h3 class="text-center text-secondary nav-href-indent-edit" id="contacts"><b>Контакты</b></h3>
            <div class="form-group">
                <c:forEach var="type" items="<%=ContactsType.values()%>">
                    <div class="row">
                        <div class="col">
                            <label class="mx-2 my-1">${type.title}</label>
                            <input type="text" class="form-control" name="${type.name()}">
                        </div>
                    </div>
                </c:forEach>
            </div>

            <c:forEach var="typeSection" items="<%=SectionType.values()%>">
                <c:if test="${typeSection.name()=='OBJECTIVE'||typeSection.name()=='PERSONAL'}">
                    <h3 class="text-center text-secondary nav-href-indent-edit" id="${typeSection.name()}">
                        <b>${typeSection.title}</b>
                    </h3>
                    <div class="input-group">
                    <textarea type="text" class="form-control my-3"
                              name="${typeSection.name()}"></textarea>
                    </div>
                </c:if>
                <c:if test="${typeSection.name()=='ACHIEVEMENT'||typeSection.name()=='QUALIFICATIONS'}">
                    <h3 class="text-center text-secondary nav-href-indent-edit" id="${typeSection.name()}">
                        <b>${typeSection.title}</b>
                    </h3>

                    <button type="button"
                            class="btn my-2 mt-4 btn-outline-primary btn-lg btn-block" style="border-radius: 10px;">
                        <a href="javascript:addSinglePosition('${typeSection.name()}+1','<div class=form-group><div class=input-group><textarea type=text class=form-control my-1 name=${typeSection.name()}></textarea></div></div>')">
                            Добавить позицию</a>
                    </button>

                    <div id="${typeSection.name()}+1"></div>
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

                    <h3 class="text-center text-secondary nav-href-indent-edit" id="${typeSection.name()}">
                        <b>${typeSection.title}</b>
                    </h3>

                    <button type="button"
                            class="btn btn-block mt-4 btn-outline-primary btn-lg"
                            style="border-radius: 10px;">
                        <a id="myLink" href="#"
                           onclick="javascript:addOrganization('${typeSection.name()}+1', 'fieldset', '${typeSection.name()}','${nameCompany}');return false;">
                            Добавить ${nameCompany1}</a>
                    </button>

                    <div id="${typeSection.name()}+1"></div>

                </c:if>
                <input type="hidden" id="organizationCounter" name="organizationCounter" value="0">
                <input type="hidden" id="positionCounter" name="positionCounter" value="0">
            </c:forEach>

            <div class="text-center">
                <button type="submit" class="btn my-4 mx-2 box-shadow-grey round" name="save" value="1"><h5
                        class="mx-2 my-1">
                    Сохранить</h5></button>
                <a href="resume">
                    <button type="button" class="btn my-4 mx-2 box-shadow-grey round"
                            name="CancelEdit" value="1"><h5 class="mx-2 my-1">Отменить</h5></button>
                </a>
            </div>
        </form>
    </div>
</div>

<div class="my-5"></div>

<jsp:include page="fragments/footer.jsp"/>

</body>
</html>