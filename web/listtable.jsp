<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>

</head>
<body>
<table border="1">
    <tr>
        <td><center>UUID</center><td>
        <td><center>Full Name</center><td>
    </tr>
    <c:forEach items="${listResumes}" var="current">
        <tr>
            <td><c:out value="${current.getUuid()}" /><td>
            <td><c:out value="${current.getFullName()}" /><td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
