<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Резюме</title>
</head>
<body>
<center>

    <table border='1' width='100%'>

        <tr>
            <td colspan='2' height='30' bgcolor="#afeeee">
                <center>РЕЗЮМЕ</center>
            </td>
        </tr>

        <tr align=center>
            <td>
                <table border='0' width='100%'>
                    <tr>
                        <td height='30'>
                            <center>
                                <font size="6">${name}</font>
                            </center>
                        </td>
                    </tr>
                    <tr>
                        <td height='30' BGCOLOR="#98fb98">${objective}</td>
                    </tr>
                    <tr>
                        <td height='30' BGCOLOR="#98fb98">${achievement}</td>
                    </tr>
                    <tr>
                        <td></td>
                    </tr>
                    <tr>
                        <td height='30' BGCOLOR="#98fb98">${qualifications}</td>
                    </tr>
                    <tr>
                        <td></td>
                    </tr>
                </table>
            </td>

            <td>
                <table border='0' width='100%'>
                    <tr height='30'>
                        <td height='300' BGCOLOR="#fff8dc">
                            <center>Фото</center>
                        </td>
                    </tr>
                    <tr>
                        <td>${contacts}</td>
                    </tr>
                    <tr>
                        <td height='30' BGCOLOR="#98fb98">${personal}</td>
                    </tr>
                    <tr>
                        <td></td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</center>
</body>
</html>
