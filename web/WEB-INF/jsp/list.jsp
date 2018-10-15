<%@ page import="ru.javawebinar.basejava.model.ContactsType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <title>Список всех резюме</title>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark sticky-top rounded box-shadow-grey" style="background: white">
    <a href="https://topjava.ru/" class="navbar-brand mx-3"><img src="img/TJ.svg" alt="logo"></a>
    <h3 class="mx-auto my-auto text-secondary">Web-приложение "База данных резюме" курса BaseJava</h3>
    <a href="resume?action=new">
        <button type="button" class="btn ml-auto mx-2 box-shadow-grey round"><h6 class="my-1 mx-2">Добавить резюме</h6>
        </button>
    </a>
</nav>

<div class="container mt-5">

    <h1 class="text-center" style="color: #1CA3E6;"><b>Список резюме</b></h1>

    <div class="row mx-1 mt-5">
        <c:forEach items="${resumes}" var="resume">
            <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume"/>
            <c:choose>
                <c:when test="${resume.fullName=='Григорий Кислин'}">

                    <div class="col-sm-4 my-2">
                        <div class="card box-shadow-lines">
                            <div class="card-body text-center">
                                <a href="resume?uuid=${resume.uuid}&action=viewnoedit" class="text-dark"><h4
                                        class="card-title">${resume.fullName}</h4></a>
                                <p class="card-text"><i class="fa fa-envelope mx-2" aria-hidden="true"
                                                        style="color: #1CA3E6;"></i><%=ContactsType.MAIL.toHtml(resume.getContacts(ContactsType.MAIL))%>
                                </p>
                                <a href="resume?uuid=${resume.uuid}&action=viewnoedit" title="открыть">
                                    <button type="button" class="btn box-shadow-grey round"><i
                                            class="fa fa-address-book-o mx-2"></i></button>
                                </a>
                                <a href="resume?uuid=${resume.uuid}&action=noedit" title="редактировать">
                                    <button type="button" class="btn box-shadow-grey round"><i
                                            class="fa fa-pencil mx-2"></i></button>
                                </a>
                                <a href="#" title="удалить">
                                    <button type="button" class="btn box-shadow-grey round"><i
                                            class="fa fa-close mx-2"></i></button>
                                </a>
                            </div>
                        </div>
                    </div>

                </c:when>
                <c:otherwise>

                    <div class="col-sm-4 my-2">
                        <div class="card box-shadow-lines">
                            <div class="card-body text-center">
                                <a href="resume?uuid=${resume.uuid}&action=view" class="text-dark"><h4
                                        class="card-title">${resume.fullName}</h4></a>
                                <p class="card-text"><i class="fa fa-envelope mx-2" aria-hidden="true"
                                                        style="color: #1CA3E6;"></i><%=ContactsType.MAIL.toHtml(resume.getContacts(ContactsType.MAIL))%>
                                </p>
                                <a href="resume?uuid=${resume.uuid}&action=view" title="открыть">
                                    <button type="button" class="btn box-shadow-grey round"><i
                                            class="fa fa-address-book-o mx-2"></i></button>
                                </a>
                                <a href="resume?uuid=${resume.uuid}&action=edit" title="редактировать">
                                    <button type="button" class="btn box-shadow-grey round"><i
                                            class="fa fa-pencil mx-2"></i></button>
                                </a>
                                <a href="resume?uuid=${resume.uuid}&action=delete" title="удалить">
                                    <button type="button" class="btn box-shadow-grey round"><i
                                            class="fa fa-close mx-2"></i></button>
                                </a>
                            </div>
                        </div>
                    </div>

                </c:otherwise>
            </c:choose>
        </c:forEach>
    </div>
</div>

<div class="my-5"></div>

</body>
</html>